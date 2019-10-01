package com.engine.geometry;


import com.engine.util.Color;

public class ColoredMesh extends Mesh{

    protected float[] colors;
    public ColoredMesh(float[] vertices, int[] indexes,float[] normals,Color[] colors) {
        init();
        this.vertices = vertices;
        this.indexes = indexes;
        this.normals=normals;
        setColors(colors);
        this.vbos = MeshLoader.loadToVAO(vaoID, vertices, indexes, this.colors, 3);
    }
    public ColoredMesh(float[] vertices, int[] indexes,float[] normals,Color color) {
        init();
        this.vertices = vertices;
        this.indexes = indexes;
        this.normals=normals;
        setColors(color);
        this.vbos = MeshLoader.loadToVAO(vaoID, vertices, indexes, this.colors, 3);
    }
    public ColoredMesh(Mesh mesh,Color[] colors)
    {
        this(mesh.vertices,mesh.indexes,mesh.normals,colors);
    }
    public ColoredMesh(Mesh mesh,Color color)
    {
        this(mesh.vertices,mesh.indexes,mesh.normals,color);
    }
    public void setColors(float[] colors) {
        this.colors = colors;
    }
    public void setColors(Color color) {
        colors=new float[vertices.length];
        for (int i = 0; i <(colors.length/3) ; i++) {
            this.colors[i * 3] = color.r;
            this.colors[i * 3 + 1] = color.g;
            this.colors[i * 3 + 2] = color.b;
        }
    }

    public void setColors(Color[] colors) {
        this.colors = new float[colors.length * 3];
        for (int i = 0; i < colors.length; i++) {
            this.colors[i * 3] = colors[i].r;
            this.colors[i * 3 + 1] = colors[i].g;
            this.colors[i * 3 + 2] = colors[i].b;
        }
    }

}
