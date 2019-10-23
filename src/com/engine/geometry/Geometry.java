package com.engine.geometry;

public class Geometry {
    public static final Mesh QUAD = Geometry.getWall(1, 1);

    public static Mesh getPlane(int sizeX, int sizeZ) {
        float[] vertices = new float[(sizeX + 1) * (sizeZ + 1) * 3];
        int[] indices = new int[sizeX * sizeZ * 6];
        float[] normals = new float[(sizeX + 1) * (sizeZ + 1) * 3];
        for (int z = 0; z < sizeZ + 1; z++) {
            for (int x = 0; x < sizeX + 1; x++) {
                int index = (x * (sizeZ + 1)) + z;
                vertices[index * 3] = x - (float) sizeX / 2;
                vertices[index * 3 + 1] = 0;
                vertices[index * 3 + 2] = z - (float) sizeZ / 2;
            }
        }

        for (int x = 0; x < sizeX; x++) {
            for (int z = 0; z < sizeZ; z++) {
                int index = (x * (sizeZ) + z);
                indices[index * 6] = index + x;
                indices[index * 6 + 1] = index + x + 1;
                indices[index * 6 + 2] = index + x + sizeZ + 1;
                indices[index * 6 + 3] = index + x + sizeZ + 1;
                indices[index * 6 + 4] = index + x + 1;
                indices[index * 6 + 5] = index + x + sizeZ + 2;
            }
        }
        for (int i = 0; i < (sizeX + 1) * (sizeZ + 1); i++) {
            normals[i * 3] = 0;
            normals[i * 3 + 1] = 1;
            normals[i * 3 + 2] = 0;
        }
        return new Mesh().vertices(vertices).indices(indices).normals(normals);
    }

    public static Mesh getWall(int sizeX, int sizeY) {
        float[] vertices = new float[(sizeX + 1) * (sizeY + 1) * 3];
        int[] indices = new int[sizeX * sizeY * 6];
        float[] normals = new float[(sizeX + 1) * (sizeY + 1) * 3];
        float[] uvs = new float[(sizeX + 1) * (sizeY + 1) * 2];
        for (int x = 0; x < sizeX + 1; x++) {
            for (int y = 0; y < sizeY + 1; y++) {
                int index = (x * (sizeY + 1)) + y;
                vertices[index * 3] = x - (float) sizeX / 2;
                vertices[index * 3 + 1] = y - (float) sizeY / 2;
                vertices[index * 3 + 2] = 0;
                uvs[index * 2] = (float) x / sizeX;
                uvs[(index * 2) + 1] = 1 - (float) y / sizeY;
            }
        }

        for (int x = 0; x < sizeX; x++) {
            for (int z = 0; z < sizeY; z++) {
                int index = (x * (sizeY) + z);
                indices[index * 6] = index + x + sizeY + 1;
                indices[index * 6 + 1] = index + x + 1;
                indices[index * 6 + 2] = index + x;
                indices[index * 6 + 3] = index + x + sizeY + 2;
                indices[index * 6 + 4] = index + x + 1;
                indices[index * 6 + 5] = index + x + sizeY + 1;

            }
        }
        for (int i = 0; i < (sizeX + 1) * (sizeY + 1); i++) {
            normals[i * 3] = 0;
            normals[i * 3 + 1] = 0;
            normals[i * 3 + 2] = 1;
        }

        return new Mesh().vertices(vertices).indices(indices).normals(normals).uvs(uvs);
    }
}
