package com.engine.examples.voxelEngine;

import com.engine.entity.Transform;
import com.engine.geometry.Mesh;
import com.engine.util.Color;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.util.LinkedList;

public class VoxelChunk extends Mesh {

    private static final Vector3f BOTTOM_LEFT_FRONT = new Vector3f(-0.5f, -0.5f, 0.5f);
    private static final Vector3f BOTTOM_LEFT_BACK = new Vector3f(-0.5f, -0.5f, -0.5f);
    private static final Vector3f BOTTOM_RIGHT_FRONT = new Vector3f(0.5f, -0.5f, 0.5f);
    private static final Vector3f BOTTOM_RIGHT_BACK = new Vector3f(0.5f, -0.5f, -0.5f);
    private static final Vector3f TOP_LEFT_FRONT = new Vector3f(-0.5f, 0.5f, 0.5f);
    private static final Vector3f TOP_LEFT_BACK = new Vector3f(-0.5f, 0.5f, -0.5f);
    private static final Vector3f TOP_RIGHT_FRONT = new Vector3f(0.5f, 0.5f, 0.5f);
    private static final Vector3f TOP_RIGHT_BACK = new Vector3f(0.5f, 0.5f, -0.5f);

    private VoxelWorld world;
    private Vector3i chunkOffset;

    public VoxelChunk(VoxelWorld world, Vector3i chunkPos) {
        this.world = world;
        chunkOffset = new Vector3i(chunkPos).mul(VoxelWorld.CHUNK_SIZE);
        setData(chunkPos);
    }

    public void setData(Vector3i chunkPos) {
        Block[][][] data = world.getChunk(chunkPos);
        LinkedList<Float> vertices = new LinkedList<>();
        LinkedList<Integer> indices = new LinkedList<>();
        LinkedList<Color> colors = new LinkedList<>();
        LinkedList<Vector3f> normals = new LinkedList<>();

        for (int y = 0; y < VoxelWorld.CHUNK_SIZE.y; y++) {
            for (int x = 0; x < VoxelWorld.CHUNK_SIZE.x; x++) {
                for (int z = 0; z < VoxelWorld.CHUNK_SIZE.z; z++) {
                    if (data[y][x][z]!=null) {
                        makeCube(vertices, indices, normals, colors, x, y, z, data,data[y][x][z].color);
                    }
                }
            }
        }
        float[] vertArray = new float[vertices.size()];
        int count = 0;
        for (Float f :
                vertices) {
            vertArray[count] = f;
            count++;
        }
        int[] indicesArray = new int[indices.size()];
        count = 0;
        for (Integer i : indices) {
            indicesArray[count] = i;
            count++;
        }
        count = 0;
        float[] normalsArray = new float[normals.size() * 3];
        for (Vector3f n : normals) {
            normalsArray[count] = n.x;
            count++;
            normalsArray[count] = n.y;
            count++;
            normalsArray[count] = n.z;
            count++;
        }
        Color[] colorArray = new Color[colors.size()];
        count = 0;
        for (Color c : colors) {
            colorArray[count] = c;
            count++;
        }
        vertices(vertArray).indices(indicesArray).colors(colorArray).normals(normalsArray);
    }

    private void makeCube(LinkedList<Float> vertices, LinkedList<Integer> indices, LinkedList<Vector3f> normals, LinkedList<Color> colors, int x, int y, int z, Block[][][] data,Color color) {
        Vector3i offSet = new Vector3i(x, y, z);

        //front
        if ((z + 1 >= VoxelWorld.CHUNK_SIZE.z
                && world.getVoxel(new Vector3i(x, y, z + 1).add(chunkOffset))==null
        ) ||
                (z + 1 < VoxelWorld.CHUNK_SIZE.z && data[y][x][z + 1]==null)) {
            makeQuad(vertices, indices, normals, colors, BOTTOM_LEFT_FRONT, TOP_LEFT_FRONT, BOTTOM_RIGHT_FRONT, TOP_RIGHT_FRONT, offSet, Transform.FOWARD, color);
        }
        //back
        if ((z - 1 < 0
                && world.getVoxel(new Vector3i(x, y, z - 1).add(chunkOffset))==null
        ) ||
                (z - 1 >= 0 && data[y][x][z - 1]==null)) {
            makeQuad(vertices, indices, normals, colors, BOTTOM_RIGHT_BACK, TOP_RIGHT_BACK, BOTTOM_LEFT_BACK, TOP_LEFT_BACK, offSet, Transform.BACKWARD,color);
        }
        //left
        if ((x - 1 < 0
                && world.getVoxel(new Vector3i(x - 1, y, z).add(chunkOffset))==null
        ) ||
                (x - 1 >= 0 && data[y][x - 1][z]==null)) {
            makeQuad(vertices, indices, normals, colors, BOTTOM_LEFT_BACK, TOP_LEFT_BACK, BOTTOM_LEFT_FRONT, TOP_LEFT_FRONT, offSet, Transform.LEFT, color);
        }
        //right
        if ((x + 1 >= VoxelWorld.CHUNK_SIZE.x
                && world.getVoxel(new Vector3i(x + 1, y, z).add(chunkOffset))==null
        ) ||
                (x + 1 < VoxelWorld.CHUNK_SIZE.x && data[y][x + 1][z]==null)) {
            makeQuad(vertices, indices, normals, colors, BOTTOM_RIGHT_FRONT, TOP_RIGHT_FRONT, BOTTOM_RIGHT_BACK, TOP_RIGHT_BACK, offSet, Transform.RIGHT, color);
        }
        //top
        if ((y + 1 >= VoxelWorld.CHUNK_SIZE.y
                // && world.getVoxel(new Vector3i(x, y + 1, z).add(chunkOffset))==null
        ) ||
                (y + 1 < VoxelWorld.CHUNK_SIZE.y && data[y + 1][x][z]==null)) {
            makeQuad(vertices, indices, normals, colors, TOP_LEFT_FRONT, TOP_LEFT_BACK, TOP_RIGHT_FRONT, TOP_RIGHT_BACK, offSet, Transform.UP, color);
        }
        //bottom
        if ((y - 1 < 0
                && world.getVoxel(new Vector3i(x, y - 1, z).add(chunkOffset))==null
        ) ||
                (y - 1 >= 0 && data[y - 1][x][z]==null)) {
            makeQuad(vertices, indices, normals, colors, BOTTOM_LEFT_BACK, BOTTOM_LEFT_FRONT, BOTTOM_RIGHT_BACK, BOTTOM_RIGHT_FRONT, offSet, Transform.DOWN, color);
        }
    }

    private static void makeQuad(LinkedList<Float> vertices, LinkedList<Integer> indices, LinkedList<Vector3f> normals, LinkedList<Color> colors, Vector3f v1, Vector3f v2, Vector3f v3, Vector3f v4, Vector3i offSet, Vector3f normal, Color color) {
        //2---4
        //| / |
        //1---3
        int i = vertices.size() / 3;
        vertices.add(v1.x + offSet.x);
        vertices.add(v1.y + offSet.y);
        vertices.add(v1.z + offSet.z);
        colors.add(color);
        normals.add(normal);
        vertices.add(v2.x + offSet.x);
        vertices.add(v2.y + offSet.y);
        vertices.add(v2.z + offSet.z);
        colors.add(color);
        normals.add(normal);
        vertices.add(v3.x + offSet.x);
        vertices.add(v3.y + offSet.y);
        vertices.add(v3.z + offSet.z);
        colors.add(color);
        normals.add(normal);
        vertices.add(v4.x + offSet.x);
        vertices.add(v4.y + offSet.y);
        vertices.add(v4.z + offSet.z);
        colors.add(color);
        normals.add(normal);

        indices.add(i + 3);
        indices.add(i + 1);
        indices.add(i);

        indices.add(i);
        indices.add(i + 2);
        indices.add(i + 3);

    }
}
