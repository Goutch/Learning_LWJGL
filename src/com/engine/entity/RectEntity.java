package com.engine.entity;

import com.engine.events.WindowResizeListener;
import com.engine.geometry.Geometry;
import com.engine.geometry.Mesh;
import com.engine.rendering.Window;
import org.joml.Quaternionf;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class RectEntity extends Entity  {
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

    protected Mesh mesh = Geometry.QUAD;
    protected Vector2f pivotOffSet = new Vector2f();
    private int rows = 1;
    private int columns = 1;
    protected Vector2f size = new Vector2f();
    protected PivotPoint pivot = PivotPoint.CENTER;
    protected float aspectRatio;
    public RectEntity(Vector3f position, Quaternionf rotation, float scale, Vector2f size, PivotPoint pivot) {
        super(position, rotation, scale);
        setSize(size);
        setPivot(pivot);
    }

    public void setSize(Vector2f size) {
        this.size.set(size);
        this.aspectRatio = size.x / size.y;
    }

    public void setPivot(PivotPoint pivot) {
        this.pivot = pivot;
        updatePivotOffset();
    }

    protected void setRows(int rows) {
        this.rows = rows;
        updateVertices();
    }

    protected void setColumns(int columns) {
        this.columns = columns;
        updateVertices();
    }

    protected int getRows() {
        return rows;
    }

    protected int getColumns() {
        return columns;
    }
    public Vector2f getPivotOffSet(){
        return pivotOffSet;
    }
    protected void updateVertices() {
        if (mesh == Geometry.QUAD) {
            createMesh();
            return;
        }
        float[] vertices = new float[(columns + 1) * (rows + 1) * 3];
        for (int y = 0; y < (rows + 1); y++) {
            for (int x = 0; x < (columns + 1); x++) {
                int index = (y * (columns + 1) + x);
                vertices[index * 3] = x * rows;
                vertices[index * 3 + 1] = (y * columns) * Window.getAspectRatio();
                vertices[index * 3 + 2] = 0;
            }
        }
        mesh.vertices(vertices);
    }

    protected void updateUVS() {
        if (mesh == Geometry.QUAD) {
            createMesh();
            return;
        }
        float[] uvs = new float[(columns + 1) * (rows + 1) * 2];
        for (int y = 0; y < (rows + 1); y++) {
            for (int x = 0; x < (columns + 1); x++) {
                int index = (y * (columns + 1) + x);
                uvs[index * 2] = (float) x / columns;
                uvs[index * 2 + 1] = 1f - ((float) y / rows);
            }
        }
        mesh.uvs(uvs);
    }

    protected void updateIndices() {
        if (mesh == Geometry.QUAD) {
            createMesh();
            return;
        }
        int[] indices = new int[columns * rows * 6];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                int index = (y * columns + x);
                indices[index * 6] = index + y + columns + 1;
                indices[index * 6 + 1] = index + y + 1;
                indices[index * 6 + 2] = index + y;
                indices[index * 6 + 3] = index + y + columns + 2;
                indices[index * 6 + 4] = index + y + 1;
                indices[index * 6 + 5] = index + y + columns + 1;
            }
        }
        mesh.indices(indices);
    }

    protected void createMesh() {
        mesh = new Mesh();
        updateVertices();
        updateIndices();
        updateUVS();
    }

    public Mesh getMesh() {
        return mesh;
    }

    private void updatePivotOffset() {
        switch (pivot) {
            case TOP_RIGHT:
                this.pivotOffSet.x = -0.5f;
                this.pivotOffSet.y = -0.5f;
                break;
            case TOP_CENTER:
                this.pivotOffSet.x = 0;
                this.pivotOffSet.y = -0.5f;
                break;
            case TOP_LEFT:
                this.pivotOffSet.x = 0.5f;
                this.pivotOffSet.y = -0.5f;
                break;
            case BOTTOM_RIGHT:
                this.pivotOffSet.x = -0.5f;
                this.pivotOffSet.y = 0.5f;
                break;
            case BOTTOM_CENTER:
                this.pivotOffSet.x = 0;
                this.pivotOffSet.y = 0.5f;
                break;
            case BOTTOM_LEFT:
                this.pivotOffSet.x = 0.5f;
                this.pivotOffSet.y = 0.5f;
                break;
            case CENTER_RIGHT:
                this.pivotOffSet.x = -0.5f;
                this.pivotOffSet.y = 0;
                break;
            case CENTER:
                this.pivotOffSet.x = 0;
                this.pivotOffSet.y = 0;
                break;
            case CENTER_LEFT:
                this.pivotOffSet.x = 0.5f;
                this.pivotOffSet.y = 0;
                break;
        }
    }

}
