package com.engine.geometry;

public class Plane {
    /**
     *
     * @param sizeX
     * @param sizeZ
     * @return the mesh instance of a plane
     */
    public static Mesh get(int sizeX,int sizeZ)
    {
        ////terrain

        float[] vertices=new float[(sizeX+1)*(sizeZ+1)*3];
        int[] indices=new int[sizeX*sizeZ*6];

        for (int x = 0; x < sizeX+1; x++) {
            for (int z = 0; z < sizeZ+1; z++) {
                int index=(x*(sizeZ+1))+z;
                vertices[index * 3] = z-(float)sizeZ/2;
                vertices[index * 3 + 1] = 0;
                vertices[index * 3 + 2] = x-(float)sizeZ/2;
            }
        }


        for (int x = 0; x < sizeX; x++) {
            for (int z = 0; z < sizeZ; z++) {
                int index=(x*(sizeZ)+z);
                indices[index*6]=index+x;
                indices[index*6+1]=index+x+1;
                indices[index*6+2]=index+x+sizeZ+1;
                indices[index*6+3]=index+x+sizeZ+1;
                indices[index*6+4]=index+x+1;
                indices[index*6+5]=index+x+sizeZ+2;

            }
        }
        return new Mesh(vertices,indices);
    }
}
