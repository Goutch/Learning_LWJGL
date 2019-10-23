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
    public Mesh(Mesh mesh)
    {
        EventManager.subscribeDispose(this);
        vao=new VAO();
        vertices(mesh.getVertices());
        indices(mesh.getIndices());
        uvs(mesh.getUVS());
        normals(mesh.getNormals());
        colors(mesh.getColors());
    }
    public Mesh vertices(float[] vertices)
    {
        this.vertices=vertices;
        vao.put(vertices,VBO.VERTICES_ATTRIBUTE_ID,VBO.VERTICES_ATTRIBUTE_SIZE);
        return this;
    }
    public Mesh indices(int[] indices)
    {
        this.indices= indices;
        vao.setIndices(indices);
        return this;
    }
    public Mesh normals(float[] normals)
    {
        this.normals=normals;
        vao.put(normals,VBO.NORMALS_ATTRIBUTE_ID,VBO.NORMALS_ATTRIBUTE_SIZE);
        return this;
    }

    public Mesh uvs(float[] uvs)
    {
        this.uvs=uvs;
        vao.put(uvs,VBO.UVS_ATTRIBUTE_ID,VBO.UVS_ATTRIBUTE_SIZE);
        return this;
    }

    public Mesh colors(Color[] colors)
    {
        setColors(colors);
        vao.put(this.colors,VBO.COLORS_ATTRIBUTE_ID,VBO.COLORS_ATTRIBUTE_SIZE);
        return this;
    }
    public Mesh colors(float[] colors)
    {
        setColors(colors);
        vao.put(colors,VBO.COLORS_ATTRIBUTE_ID,VBO.COLORS_ATTRIBUTE_SIZE);
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


    public int getVertexCount() {
        if (indices != null) {
            return indices.length * 3;
        } else {
            return vertices.length;
        }
    }
    public float[] getColors()
    {
        return colors;
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

    public float[] getUVS()
    {
        return uvs;
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
