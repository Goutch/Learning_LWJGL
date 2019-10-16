package com.engine.entity.gui;

import com.engine.entity.Entity;
import com.engine.events.EventManager;
import com.engine.events.WindowResizeListener;
import com.engine.geometry.Mesh;
import com.engine.materials.GUIMaterial;
import com.engine.rendering.GUIRenderer;
import com.engine.rendering.Window;
import com.engine.util.Texture;
import org.joml.Quaternionf;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Panel extends Entity implements WindowResizeListener {

    public enum PivotPoint {
        NONE,

        TOP_RIGHT,
        TOP_CENTER,
        TOP_LEFT,

        BOTTOM_RIGHT,
        BOTTOM_CENTER,
        BOTTOM_LEFT,

        CENTER_RIGHT,
        CENTER,
        CENTER_LEFT,
    }

    protected PivotPoint pivot = PivotPoint.CENTER;
    protected Vector3f pivotOffSet=new Vector3f();
    protected Vector2f size=new Vector2f();
    protected GUIMaterial material;
    protected Mesh mesh;
    protected float aspectRatio;
    protected int numRows=1;
    protected int numColumns=1;

    protected void init() {
        createMesh(numRows,numColumns);
    }

    public Panel(Vector3f position, Vector2f size, PivotPoint pivotPoint, GUIMaterial material) {
        super(position, new Quaternionf(), 1f);
        EventManager.subcribeWindowResize(this);
        this.size.set(size);
        this.aspectRatio = size.x / size.y;
        setPivot(pivotPoint);
        this.material = material;
        init();
    }

    public Panel(Vector3f position, Vector2f size, PivotPoint pivotPoint) {
        this(position, size, pivotPoint, GUIMaterial.DEFAULT);
    }
    public Panel(Vector3f position, Vector2f size, GUIMaterial material) {
        this(position, size, PivotPoint.CENTER, material);
    }

    public Panel(Vector3f position, Vector2f size) {
        this(position, size, PivotPoint.CENTER, GUIMaterial.DEFAULT);
    }

    public Vector2f getSize() {
        return new Vector2f(size);
    }

    public void setSize(Vector2f size) {
        this.size.set(size);
        setPivot(pivot);
        this.aspectRatio = size.x / size.y;
        updateVertices(numRows,numColumns);
    }

    public Panel fitTexture() {
        if (material.hasTexture()) {
            Texture texture = material.texture();
            this.setSize(new Vector2f(size.x, size.x * ((float) texture.height() / texture.width())));
        }
        return this;
    }

    public GUIMaterial getMaterial() {
        return material;
    }

    public void setMaterial(GUIMaterial material) {
        this.material = material;
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    protected void updateVertices(int row,int column) {
        float[] vertices = new float[(column + 1) * (row + 1) * 3];
        for (int y = 0; y < (row + 1); y++) {
            for (int x = 0; x < (column + 1); x++) {
                int index = (y * (column + 1) + x);
                vertices[index * 3] = x * (size.x/numColumns) + pivotOffSet.x;
                vertices[index * 3 + 1] = (y * (size.y/numRows) + pivotOffSet.y) * Window.getAspectRatio();
                vertices[index * 3 + 2] = 0;
            }
        }
        mesh.vertices(vertices);
    }
    protected void updateUVS(int row,int column) {
        float[] uvs = new float[(column + 1) * (row + 1) * 2];
        for (int y = 0; y < (row + 1); y++) {
            for (int x = 0; x < (column + 1); x++) {
                int index = (y * (column + 1) + x);
                uvs[index * 2] = (float) x /column;
                uvs[index * 2 + 1] = 1f - ((float)y/row);
            }
        }
        mesh.uvs(uvs);
    }
    protected void updateIndices(int row, int column)
    {
        int[] indices = new int[column * row * 6];
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < column; x++) {
                int index = (y * column + x);
                indices[index * 6] = index + y + column + 1;
                indices[index * 6 + 1] = index + y + 1;
                indices[index * 6 + 2] = index + y;
                indices[index * 6 + 3] = index + y + column + 2;
                indices[index * 6 + 4] = index + y + 1;
                indices[index * 6 + 5] = index + y + column + 1;
            }
        }
        mesh.indices(indices);
    }
    protected void createMesh(int row, int column) {
        mesh=new Mesh();
        updateVertices(row,column);
        updateIndices(row,column);
        updateUVS(row,column);

    }

    public Mesh getMesh() {
        return mesh;
    }

    @Override
    public void render() {
        super.render();
        GUIRenderer.addToRenderQueue(this);
    }

    private void setPivot(PivotPoint pivot) {
        this.pivot = pivot;
        pivotOffSet = new Vector3f(0, 0, 0);
        switch (pivot) {
            case TOP_RIGHT:
                this.pivotOffSet.y -= size.y;
                this.pivotOffSet.x -= size.x;
                break;
            case TOP_CENTER:
                this.pivotOffSet.x -= size.x * 0.5;
                this.pivotOffSet.y -= size.y;
                break;
            case TOP_LEFT:
                this.pivotOffSet.y -= size.y;
                break;
            case BOTTOM_RIGHT:
                this.pivotOffSet.x -= size.x;
                break;
            case BOTTOM_CENTER:
                this.pivotOffSet.x -= size.x * 0.5f;
                break;
            case BOTTOM_LEFT:
                break;
            case CENTER_RIGHT:
                this.pivotOffSet.x -= size.x;
                this.pivotOffSet.y -= size.y * .5;
                break;
            case CENTER:
                this.pivotOffSet.x -= size.x * .5;
                this.pivotOffSet.y -= size.y * .5;
                break;
            case CENTER_LEFT:
                this.pivotOffSet.y -= size.y * .5;
                break;
        }
    }

    @Override
    public void onWindowResize(int width, int height) {
        updateVertices(1,1);
    }
}
