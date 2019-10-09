package com.engine.entity.gui;

import com.engine.entity.Entity;
import com.engine.entity.Transform;
import com.engine.events.EventManager;
import com.engine.events.WindowResizeListener;
import com.engine.geometry.Mesh;
import com.engine.gui.GUIMaterial;
import com.engine.rendering.GUIRenderer;
import com.engine.rendering.Window;
import com.engine.util.Color;
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

    private PivotPoint pivot = PivotPoint.CENTER;
    private Vector3f pivotOffSet;
    private Vector2f size;
    private GUIMaterial material;
    protected Mesh mesh;
    protected float aspectRatio;

    public Panel(Vector3f position, Vector2f size, PivotPoint pivotPoint, GUIMaterial material) {
        super(position, new Quaternionf(), 1f);
        EventManager.subcribeWindowResize(this);
        this.size = new Vector2f(size);
        this.aspectRatio = size.x / size.y;
        this.material = material;
        setPivot(pivotPoint);
        createMesh();
    }
    public Panel(Vector3f position, Vector2f size, PivotPoint pivotPoint) {
        this(position,size,pivotPoint,GUIMaterial.DEFAULT);
    }
    public Panel(Vector3f position, Vector2f size) {
        this(position,size,PivotPoint.CENTER,GUIMaterial.DEFAULT);
    }

    public Vector2f getSize() {
        return new Vector2f(size);
    }

    public void setSize(Vector2f size) {
        this.size.set(size);
        this.aspectRatio = size.x / size.y;
        setPivot(pivot);
        updateMesh();
    }
    public void fitTexture()
    {
        if(material.hasTexture())
        {
            Texture texture=material.texture();
            this.setSize(new Vector2f(size.x,size.x*((float)texture.height()/texture.width())));
        }
    }
    public GUIMaterial getMaterial() {
        return material;
    }
    public void setMaterial(GUIMaterial material)
    {
        this.material=material;
    }

    private void updateMesh() {
        int sizeX = 1;
        int sizeY = 1;
        float[] vertices = new float[(sizeY + 1) * (sizeX + 1) * 3];
        for (int x = 0; x < (sizeX + 1); x++) {
            for (int y = 0; y < (sizeY + 1); y++) {
                int index = (x * (sizeY + 1) + y);
                vertices[index * 3] = x * size.x + pivotOffSet.x;
                vertices[index * 3 + 1] = (y * size.y + pivotOffSet.y) * Window.getAspectRatio();
                vertices[index * 3 + 2] = 0;
            }
        }
        mesh.vertices(vertices);
        mesh.buildVertices();
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    private void createMesh() {
        int quadsPerRow = 1;
        int quadsPerColumn = 1;

        float[] vertices = new float[(quadsPerColumn + 1) * (quadsPerRow + 1) * 3];
        float[] uvs=new float[(quadsPerColumn + 1) * (quadsPerRow + 1)*2];
        for (int x = 0; x < (quadsPerRow + 1); x++) {
            for (int y = 0; y < (quadsPerColumn + 1); y++) {
                int index = (x * (quadsPerColumn + 1) + y);
                vertices[index * 3] = x * size.x + pivotOffSet.x;
                uvs[index*2]=(float) x/quadsPerRow;
                vertices[index * 3 + 1] = (y * size.y + pivotOffSet.y) * Window.getAspectRatio();
                uvs[index*2+1]=1f-((float) y/quadsPerColumn);
                vertices[index * 3 + 2] = 0;
            }
        }
        int[] indices = new int[quadsPerColumn * quadsPerRow * 6];
        for (int x = 0; x < quadsPerRow; x++) {
            for (int y = 0; y < quadsPerColumn; y++) {
                int index = (x * quadsPerColumn + y);
                indices[index * 6] = index + x + quadsPerColumn + 1;
                indices[index * 6 + 1] = index + x + 1;
                indices[index * 6 + 2] = index + x;
                indices[index * 6 + 3] = index + x + quadsPerColumn + 2;
                indices[index * 6 + 4] = index + x + 1;
                indices[index * 6 + 5] = index + x + quadsPerColumn + 1;
            }
        }

        mesh = new Mesh().vertices(vertices).indices(indices).uvs(uvs);
        mesh.build();
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
        updateMesh();
    }
}
