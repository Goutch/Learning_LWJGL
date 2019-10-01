package com.engine.geometry;


import com.engine.events.DisposeListener;
import com.engine.events.EventManager;


import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

/**
 * Represent a mesh in memory
 */
public class Mesh implements DisposeListener {
    protected float[] vertices;
    protected int[] indices;
    protected float[] normals;
    protected float[] colors;
    protected float[] uvs;
    protected int vaoID;
    protected int[] vbos;

    public void init() {
        EventManager.subscribeDispose(this);
        this.vaoID = MeshLoader.getVAO();
    }

    public Mesh() {
        init();
    }

    public Mesh(float[] vertices, int[] indices, float[] normals) {
        init();
        this.normals = normals;
        this.vertices = vertices;
        this.indices = indices;
        this.vbos = MeshLoader.loadToVAO(vaoID, vertices, indices);
    }

    /**
     * @return vertex array buffer
     */
    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        if (indices != null) {
            return indices.length * 3;
        } else {
            return vertices.length;
        }

    }

    public float[] getNormals() {
        return normals;
    }

    public float[] getVertices() {
        return vertices;
    }

    public int[] getIndices() {
        return indices;
    }

    /*
     * bind the vertices,indices,normals to the vao
     */
    public void build() {
        if (vertices != null) {

            if (indices == null) {
                if (normals != null && colors != null && uvs != null) {
                    this.vbos = MeshLoader.loadToVAO(vaoID, vertices,  normals, 3, uvs, 2, colors, 4);
                } else if (normals != null && colors != null) {
                    this.vbos = MeshLoader.loadToVAO(vaoID, vertices,  colors, 4);
                } else if (normals != null && uvs != null) {
                    this.vbos = MeshLoader.loadToVAO(vaoID, vertices, uvs, 2);
                } else if (normals != null) {
                    this.vbos = MeshLoader.loadToVAO(vaoID, vertices,  normals, 3);
                } else {
                    this.vbos = MeshLoader.loadToVAO(vaoID, vertices);
                }
            } else {
                if (normals != null && colors != null && uvs != null) {
                    this.vbos = MeshLoader.loadToVAO(vaoID, vertices, indices, normals, 3, uvs, 2, colors, 4);
                } else if (normals != null && colors != null) {
                    this.vbos = MeshLoader.loadToVAO(vaoID, vertices, indices, colors, 4);
                } else if (normals != null && uvs != null) {
                    this.vbos = MeshLoader.loadToVAO(vaoID, vertices, indices, uvs, 2);
                } else if (normals != null) {
                    this.vbos = MeshLoader.loadToVAO(vaoID, vertices, indices, normals, 3);
                } else {
                    this.vbos = MeshLoader.loadToVAO(vaoID, vertices, indices);
                }
            }

        } else {
            System.err.println("Cant build mesh, the minimum requirement is a vertices array");
        }

    }

    @Override
    public void dispose() {
        for (int vbo : vbos) {
            glDeleteBuffers(vbo);
        }
        glDeleteVertexArrays(vaoID);
    }

}
