package com.engine.geometry;


import com.engine.events.DisposeListener;
import com.engine.events.EventManager;


import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

/**
 * Represent a mesh
 */
public class Mesh implements DisposeListener{
    protected float[] vertices;

    protected int[] indexes;


    protected float[] normals;
    protected int vaoID;
    protected int[] vbos;

    public void init()
    {
        EventManager.subscribeDispose(this);
        this.vaoID = MeshLoader.getVAO();
    }

    public Mesh() {
        init();
    }


    public Mesh( float[] vertices, int[] indexes) {
        init();
        this.vertices = vertices;
        this.indexes = indexes;
        this.vbos = MeshLoader.loadToVAO(vaoID, vertices, indexes);
    }
    public Mesh( float[] vertices, int[] indexes,float[] normals) {
        init();
        this.normals=normals;
        this.vertices = vertices;
        this.indexes = indexes;
        this.vbos = MeshLoader.loadToVAO(vaoID, vertices, indexes);
    }

    /**
     * @return vertex array buffer
     */
    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        if(indexes!=null)
        {
            return indexes.length*3;
        }
        else {
            return vertices.length;
        }

    }

    public float[] getNormals() {
        return normals;
    }

    public float[] getVertices() {
        return vertices;
    }

    public int[] getIndexes() {
        return indexes;
    }

    public void setNormals(float[] normals) {
        this.normals = normals;
    }
    public void setVertices(float[] vertices,int[] indexes) {
        this.vertices = vertices;
        this.indexes = indexes;
        for (int vbo : vbos) {
            glDeleteBuffers(vbo);
        }
        this.vbos = MeshLoader.loadToVAO(vaoID, vertices, indexes);
    }

    @Override
    public void dispose() {
        for (int vbo : vbos) {
            glDeleteBuffers(vbo);
        }
        glDeleteVertexArrays(vaoID);
    }

}
