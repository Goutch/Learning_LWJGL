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
    protected VAO vao;
    protected float[] vertices = null;
    protected int[] indices = null;
    protected float[] normals = null;
    protected float[] colors = null;
    protected float[] uvs = null;

    public Mesh() {
        EventManager.subscribeDispose(this);
        vao=new VAO();
    }
    public Mesh vertices(float[] vertices)
    {
        this.vertices=vertices;
        return this;
    }
    public Mesh indices(int[] indices)
    {
        this.indices= indices;
        return this;
    }
    public Mesh normals(float[] normals)
    {
        this.normals=normals;
        return this;
    }

    public Mesh uvs(float[] uvs)
    {
        this.uvs=uvs;
        return this;
    }

    public Mesh colors(Color[] colors)
    {
        setColors(colors);
        return this;
    }
    public Mesh colors(float[] colors)
    {
        setColors(colors);
        return this;
    }

    public void bind()
    {
        vao.bind();
    }
    public void unBind()
    {
        vao.unbind();
    }
    public void buildVertices()
    {
        vao.put(vertices,VBO.VERTICES_ATTRIBUTE_ID,VBO.VERTICES_ATTRIBUTE_SIZE);
    }

    /*
     * bind the vertices,indices,normals,etc to the vao
     */
    public Mesh build() {
        if (vao!=null&&vertices != null && indices != null) {
            vao.setIndices(indices);
            vao.put(vertices,VBO.VERTICES_ATTRIBUTE_ID,VBO.VERTICES_ATTRIBUTE_SIZE);
            if(normals!=null)vao.put(normals,VBO.NORMALS_ATTRIBUTE_ID,VBO.NORMALS_ATTRIBUTE_SIZE);
            if(uvs!=null)vao.put(uvs,VBO.UVS_ATTRIBUTE_ID,VBO.UVS_ATTRIBUTE_SIZE);
            if(colors!=null)vao.put(colors,VBO.COLORS_ATTRIBUTE_ID,VBO.COLORS_ATTRIBUTE_SIZE);
        } else {
            System.err.println("Cant build mesh,you must initialize the mesh and the minimum requirement is a vertices and indices array");
        }
        return this;
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

    private void setColors(float[] colors) {
        this.colors = colors;
    }

    private void setColors(Color color) {
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

    private void setColors(Color[] colors) {
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
        vao.dispose();
    }

}
