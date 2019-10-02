package com.engine.geometry;


import com.engine.events.DisposeListener;
import com.engine.events.EventManager;
import com.engine.util.Color;


import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

/**
 * Represent a mesh
 */
public class Mesh implements DisposeListener {
    protected int vaoID = -1;
    protected int[] vbos = null;
    protected float[] vertices = null;
    protected int[] indices = null;
    protected float[] normals = null;
    protected float[] colors = null;
    protected float[] uvs = null;

    public void init() {
        EventManager.subscribeDispose(this);
        this.vaoID = MeshLoader.getVAO();
    }

    public Mesh() {
        init();
    }


    public Mesh(float[] vertices, int[] indices) {
        init();
        this.vertices = vertices;
        this.indices = indices;
        build();
    }

    public Mesh(float[] vertices, int[] indices, float[] normals) {
        init();
        this.normals = normals;
        this.vertices = vertices;
        this.indices = indices;
        build();
    }
    public Mesh(float[] vertices, int[] indices,Color[] colors) {
        init();
        this.vertices = vertices;
        this.indices = indices;
        setColors(colors);
        build();
    }
    public Mesh(float[] vertices, int[] indices,Color color) {
        init();
        this.vertices = vertices;
        this.indices = indices;
        setColors(color);
        build();
    }


    public Mesh(float[] vertices, int[] indices, float[] normals, Color[] colors) {
        init();
        this.vertices = vertices;
        this.indices = indices;
        this.normals = normals;
        setColors(colors);
        build();
    }

    public Mesh(float[] vertices, int[] indices, float[] normals, Color color) {
        init();
        this.vertices = vertices;
        this.indices = indices;
        this.normals = normals;
        setColors(color);
        build();
    }

    public Mesh(float[] vertices, int[] indexes, float[] normals, float[] uvs) {
        init();
        this.vertices = vertices;
        this.indices = indexes;
        this.normals = normals;
        this.uvs = uvs;
        build();
    }


    /*
     * bind the vertices,indices,normals to the vao
     */
    public void build() {
        if (vertices != null && indices != null) {
            if (vbos != null) {
                for (int vbo : vbos) {
                    glDeleteBuffers(vbo);
                }
            }
            this.vbos = MeshLoader.loadToVAO(vaoID, vertices, indices, normals, uvs, colors);

        } else {
            System.err.println("Cant build mesh, the minimum requirement is a vertices and indices array");
        }

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

    public void setColors(float[] colors) {
        this.colors = colors;
    }

    public void setColors(Color color) {
        if (color != null) {
            colors = new float[(getVertexCount() / 3) * 4];
            for (int i = 0; i < (colors.length / 4); i++) {
                this.colors[i * 4] = color.r;
                this.colors[i * 4 + 1] = color.g;
                this.colors[i * 4 + 2] = color.b;
                this.colors[i * 4 + 3] = color.a;
            }
        } else this.colors = null;
    }

    public void setColors(Color[] colors) {
        if (colors != null) {
            this.colors = new float[colors.length * 4];
            for (int i = 0; i < colors.length; i++) {
                this.colors[i * 4] = colors[i].r;
                this.colors[i * 4 + 1] = colors[i].g;
                this.colors[i * 4 + 2] = colors[i].b;
                this.colors[i * 4 + 3] = colors[i].a;
            }
        } else this.colors = null;
    }

    @Override
    public void dispose() {
        if (vbos != null)
        {
            for (int vbo : vbos) {
                if (vbo != -1)
                    glDeleteBuffers(vbo);
            }
            vbos=null;
        }
        if(vaoID!=-1)
        {
            glDeleteVertexArrays(vaoID);
            vaoID=-1;
        }

    }

}
