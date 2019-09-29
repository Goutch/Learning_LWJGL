package com.engine.geometry;


import com.engine.rendering.Renderer;

public class TexturedMesh extends Mesh{
    protected float[] uvs;

    public TexturedMesh(float[] vertices, int[] indexes, float[] uvs) {
        init();
        this.vertices = vertices;
        this.indexes = indexes;
        this.uvs = uvs;
        this.vbos = MeshLoader.loadToVAO(vaoID, vertices, indexes, uvs);

    }
    public TexturedMesh(Mesh mesh,float[] uvs)
    {
        this(mesh.vertices,mesh.indexes,uvs);
    }

    public void setUvs(float[] uvs) {
        this.uvs = uvs;
    }

    public float[] getUvs() {
        return uvs;
    }

}
