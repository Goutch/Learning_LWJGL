package com.engine.geometry;


public class TexturedMesh extends Mesh{
    protected float[] uvs;

    public TexturedMesh(float[] vertices, int[] indexes,float[] normals, float[] uvs) {
        init();
        this.vertices = vertices;
        this.indices = indexes;
        this.normals=normals;
        this.uvs = uvs;
        this.vbos = MeshLoader.loadToVAO(vaoID, vertices, indexes, uvs);

    }
    public TexturedMesh(Mesh mesh,float[] uvs)
    {
        this(mesh.vertices,mesh.indices,mesh.normals,uvs);
    }

    public void setUvs(float[] uvs) {
        this.uvs = uvs;
    }

    public float[] getUvs() {
        return uvs;
    }

}
