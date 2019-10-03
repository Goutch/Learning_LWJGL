package com.engine.geometry;

public class Geometry {
    // constants
    private final static float PI = 3.1415926f;
    private final static float H_ANGLE = PI / 180 * 72;    // 72 degree = 360 / 5
    private final static float V_ANGLE = (float) Math.atan(1.0f / 2f);  // elevation = 26.565 degree

    public static Mesh getPlane(int sizeX, int sizeZ) {
        float[] vertices = new float[(sizeX + 1) * (sizeZ + 1) * 3];
        int[] indices = new int[sizeX * sizeZ * 6];
        float[] normals = new float[(sizeX + 1) * (sizeZ + 1) * 3];
        for (int x = 0; x < sizeX + 1; x++) {
            for (int z = 0; z < sizeZ + 1; z++) {
                int index = (x * (sizeZ + 1)) + z;
                vertices[index * 3] = z - (float) sizeZ / 2;
                vertices[index * 3 + 1] = 0;
                vertices[index * 3 + 2] = x - (float) sizeX / 2;
            }
        }


        for (int x = 0; x < sizeX; x++) {
            for (int z = 0; z < sizeZ; z++) {
                int index = (x * (sizeZ) + z);
                indices[index * 6 ] = index + x + sizeZ + 1;
                indices[index * 6 + 1] = index + x + 1;
                indices[index * 6+2] = index + x;
                indices[index * 6 + 3] = index + x + sizeZ + 2;
                indices[index * 6 + 4] = index + x + 1;
                indices[index * 6 + 5] = index + x + sizeZ + 1;

            }
        }
        for (int i = 0; i < (sizeX + 1) * (sizeZ + 1); i++) {
            normals[i * 3] = 0;
            normals[i * 3 + 1] = 1;
            normals[i * 3 + 2] = 0;
        }
        return new Mesh(vertices, indices, normals);
    }

}
