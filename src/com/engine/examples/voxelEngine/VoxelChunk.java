package com.engine.examples.voxelEngine;

import com.engine.entity.Transform;
import com.engine.examples.voxelEngine.voxelShader.VoxelShader;
import com.engine.geometry.Mesh;
import com.engine.geometry.VBO;
import com.engine.util.Color;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.util.LinkedList;
import java.util.List;

public class VoxelChunk extends Mesh {

    private static final Vector3f BOTTOM_LEFT_FRONT = new Vector3f(-0.5f, -0.5f, 0.5f);
    private static final Vector3f BOTTOM_LEFT_BACK = new Vector3f(-0.5f, -0.5f, -0.5f);
    private static final Vector3f BOTTOM_RIGHT_FRONT = new Vector3f(0.5f, -0.5f, 0.5f);
    private static final Vector3f BOTTOM_RIGHT_BACK = new Vector3f(0.5f, -0.5f, -0.5f);
    private static final Vector3f TOP_LEFT_FRONT = new Vector3f(-0.5f, 0.5f, 0.5f);
    private static final Vector3f TOP_LEFT_BACK = new Vector3f(-0.5f, 0.5f, -0.5f);
    private static final Vector3f TOP_RIGHT_FRONT = new Vector3f(0.5f, 0.5f, 0.5f);
    private static final Vector3f TOP_RIGHT_BACK = new Vector3f(0.5f, 0.5f, -0.5f);
    private static final Vector2f uv1=new Vector2f(0,0);
    private static final Vector2f uv2=new Vector2f(0,1);
    private static final Vector2f uv3=new Vector2f(1,0);
    private static final Vector2f uv4=new Vector2f(1,1);

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
        LinkedList<Vector2f> uvs=new LinkedList<>();
        LinkedList<Float> ambientOcclusion = new LinkedList<>();
        for (int y = 0; y < VoxelWorld.CHUNK_SIZE.y; y++) {
            for (int x = 0; x < VoxelWorld.CHUNK_SIZE.x; x++) {
                for (int z = 0; z < VoxelWorld.CHUNK_SIZE.z; z++) {
                    if (data[y][x][z] != null) {
                        makeCube(vertices, indices, normals,uvs, colors, ambientOcclusion, x, y, z, data, data[y][x][z].color);
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
        float[] uvsArray=new float[uvs.size()*2];
        count=0;
        for (Vector2f uv:
             uvs) {
            uvsArray[count]=uv.x;
            count++;
            uvsArray[count]=uv.y;
            count++;
        }
        float[] ambienOcclusionArray = new float[ambientOcclusion.size()];
        count = 0;
        for (Float ao : ambientOcclusion) {
            ambienOcclusionArray[count] = ao;
            count++;
        }
        ambientOcclusion(ambienOcclusionArray).vertices(vertArray).indices(indicesArray).colors(colorArray).normals(normalsArray).uvs(uvsArray);
    }

    private void makeCube(LinkedList<Float> vertices,
                          LinkedList<Integer> indices,
                          LinkedList<Vector3f> normals,
                          LinkedList<Vector2f> uvs,
                          LinkedList<Color> colors,
                          LinkedList<Float> ambientOcclusion,
                          int x, int y, int z,
                          Block[][][] data,
                          Color color) {
        Vector3i offSet = new Vector3i(x, y, z);
        boolean[][][] neighbours = new boolean[3][3][3];
        boolean xNegativeOutOfBound = x == 0;
        boolean xPositiveOutOfBound = x + 1 == VoxelWorld.CHUNK_SIZE.x;
        boolean yNegativeOutOfBound = y == 0;
        boolean yPositiveOutOfBound = y + 1 == VoxelWorld.CHUNK_SIZE.y;
        boolean zNegativeOutOfBound = z == 0;
        boolean zPositiveOutOfBound = z + 1 == VoxelWorld.CHUNK_SIZE.z;
        for (int yi = -1; yi < 2; yi++) {
            for (int xi = -1; xi < 2; xi++) {
                for (int zi = -1; zi < 2; zi++) {
                    if (yNegativeOutOfBound || xNegativeOutOfBound || zNegativeOutOfBound || yPositiveOutOfBound || xPositiveOutOfBound || zPositiveOutOfBound) {
                        neighbours[yi + 1][xi + 1][zi + 1] = world.getVoxel(new Vector3i(x + xi, y + yi, z + zi).add(chunkOffset)) != null;
                    } else {
                        neighbours[yi + 1][xi + 1][zi + 1] = data[y + yi][x + xi][z + zi] != null;
                    }
                }
            }
        }

        //front
        if (!neighbours[1][1][2]) {
            makeQuad(vertices,
                    indices,
                    normals,
                    uvs,
                    colors,
                    ambientOcclusion,
                    BOTTOM_LEFT_FRONT,
                    TOP_LEFT_FRONT,
                    BOTTOM_RIGHT_FRONT,
                    TOP_RIGHT_FRONT,
                    Transform.Directions.FOWARD,
                    offSet,
                    color,
                    neighbours);

        }
        //back
        if (!neighbours[1][1][0]) {
            makeQuad(vertices,
                    indices,
                    normals,
                    uvs,
                    colors,
                    ambientOcclusion,
                    BOTTOM_RIGHT_BACK,
                    TOP_RIGHT_BACK,
                    BOTTOM_LEFT_BACK,
                    TOP_LEFT_BACK,
                    Transform.Directions.BACKWARD,
                    offSet,
                    color,
                    neighbours);
        }
        //left
        if (!neighbours[1][0][1]) {
            makeQuad(vertices,
                    indices,
                    normals,
                    uvs,
                    colors,
                    ambientOcclusion,
                    BOTTOM_LEFT_BACK,
                    TOP_LEFT_BACK,
                    BOTTOM_LEFT_FRONT,
                    TOP_LEFT_FRONT,
                    Transform.Directions.LEFT,
                    offSet,
                    color,
                    neighbours);
        }
        //right
        if (!neighbours[1][2][1]) {
            makeQuad(vertices,
                    indices,
                    normals,
                    uvs,
                    colors,
                    ambientOcclusion,
                    BOTTOM_RIGHT_FRONT,
                    TOP_RIGHT_FRONT,
                    BOTTOM_RIGHT_BACK,
                    TOP_RIGHT_BACK,
                    Transform.Directions.RIGHT,
                    offSet,
                    color,
                    neighbours);
        }
        //top
        if (yPositiveOutOfBound || !neighbours[2][1][1]) {
            makeQuad(vertices,
                    indices,
                    normals,
                    uvs,
                    colors,
                    ambientOcclusion,
                    TOP_LEFT_FRONT,
                    TOP_LEFT_BACK,
                    TOP_RIGHT_FRONT,
                    TOP_RIGHT_BACK,
                    Transform.Directions.UP,
                    offSet,
                    color,
                    neighbours);
        }
        //bottom
        if (!neighbours[0][1][1]) {
            makeQuad(vertices,
                    indices,
                    normals,
                    uvs,
                    colors,
                    ambientOcclusion,
                    BOTTOM_LEFT_BACK,
                    BOTTOM_LEFT_FRONT,
                    BOTTOM_RIGHT_BACK,
                    BOTTOM_RIGHT_FRONT,
                    Transform.Directions.DOWN,
                    offSet,
                    color,
                    neighbours);
        }
    }

    private static void makeQuad(LinkedList<Float> vertices,
                                 LinkedList<Integer> indices,
                                 LinkedList<Vector3f> normals,
                                 LinkedList<Vector2f> uvs,
                                 LinkedList<Color> colors,
                                 LinkedList<Float> ambientOcclusion,
                                 Vector3f v1,
                                 Vector3f v2,
                                 Vector3f v3,
                                 Vector3f v4,
                                 Transform.Directions dir,
                                 Vector3i offSet,
                                 Color color,
                                 boolean[][][] neigboors) {

        //2---4
        //| / |
        //1---3

        int i = vertices.size() / 3;
        //1
        vertices.add(v1.x + offSet.x);
        vertices.add(v1.y + offSet.y);
        vertices.add(v1.z + offSet.z);
        uvs.add(uv1);
        colors.add(color);

        //2
        vertices.add(v2.x + offSet.x);
        vertices.add(v2.y + offSet.y);
        vertices.add(v2.z + offSet.z);
        uvs.add(uv2);
        colors.add(color);

        //3
        vertices.add(v3.x + offSet.x);
        vertices.add(v3.y + offSet.y);
        vertices.add(v3.z + offSet.z);
        uvs.add(uv3);
        colors.add(color);

        //4
        vertices.add(v4.x + offSet.x);
        vertices.add(v4.y + offSet.y);
        vertices.add(v4.z + offSet.z);
        uvs.add(uv4);
        colors.add(color);

        float ao1x = 1f,ao1y=1f,ao1xy=1f, ao2x = 1f,ao2y=1f,ao2xy=1f, ao3x = 1f,ao3y=1f,ao3xy=1f, ao4x = 1f,ao4y=1f,ao4xy=1f;
        float aoFactor = 0.5f;
        switch (dir) {
            case UP:
                normals.add(Transform.UP);
                normals.add(Transform.UP);
                normals.add(Transform.UP);
                normals.add(Transform.UP);

                ao1y -= neigboors[2][1][2] ? aoFactor : 0f;
                ao1x -= neigboors[2][0][1] ? aoFactor : 0f;
                ao1xy -= neigboors[2][0][2] ? aoFactor : 0f;
                ao2x -= neigboors[2][0][1] ? aoFactor : 0f;
                ao2y -= neigboors[2][1][0] ? aoFactor : 0f;
                ao2xy -= neigboors[2][0][0] ? aoFactor : 0f;
                ao3y -= neigboors[2][1][2] ? aoFactor : 0f;
                ao3x -= neigboors[2][2][1] ? aoFactor : 0f;
                ao3xy -= neigboors[2][2][2] ? aoFactor : 0f;
                ao4y -= neigboors[2][1][0] ? aoFactor : 0f;
                ao4x -= neigboors[2][2][1] ? aoFactor : 0f;
                ao4xy -= neigboors[2][2][0] ? aoFactor : 0f;

                break;
            case DOWN:
                for (int j = 0; j < 4; j++) {
                    normals.add(Transform.DOWN);
                }

                ao1y  -= neigboors[0][0][1] ? aoFactor : 0f;
                ao1x  -= neigboors[0][1][0] ? aoFactor : 0f;
                ao1xy -= neigboors[0][0][0] ? aoFactor : 0f;
                ao2x  -= neigboors[0][1][2] ? aoFactor : 0f;
                ao2y  -= neigboors[0][0][1] ? aoFactor : 0f;
                ao2xy -= neigboors[0][0][2] ? aoFactor : 0f;
                ao3y  -= neigboors[0][1][0] ? aoFactor : 0f;
                ao3x  -= neigboors[0][2][1] ? aoFactor : 0f;
                ao3xy -= neigboors[0][2][0] ? aoFactor : 0f;
                ao4y  -= neigboors[0][1][2] ? aoFactor : 0f;
                ao4x  -= neigboors[0][2][1] ? aoFactor : 0f;
                ao4xy -= neigboors[0][2][2] ? aoFactor : 0f;
                break;
            case BACKWARD:
                for (int j = 0; j < 4; j++) {
                    normals.add(Transform.BACKWARD);
                }
                ao1y  -= neigboors[1][0][0] ? aoFactor : 0f;
                ao1x  -= neigboors[0][1][0] ? aoFactor : 0f;
                ao1xy -= neigboors[0][0][0] ? aoFactor : 0f;
                ao2x  -= neigboors[2][1][0] ? aoFactor : 0f;
                ao2y  -= neigboors[1][0][0] ? aoFactor : 0f;
                ao2xy -= neigboors[2][0][0] ? aoFactor : 0f;
                ao3y  -= neigboors[0][1][0] ? aoFactor : 0f;
                ao3x  -= neigboors[1][2][0] ? aoFactor : 0f;
                ao3xy -= neigboors[0][2][0] ? aoFactor : 0f;
                ao4y  -= neigboors[2][1][0] ? aoFactor : 0f;
                ao4x  -= neigboors[1][2][0] ? aoFactor : 0f;
                ao4xy -= neigboors[2][2][0] ? aoFactor : 0f;
                break;
            case FOWARD:
                for (int j = 0; j < 4; j++) {
                    normals.add(Transform.FOWARD);
                }
                ao1y  -= neigboors[1][0][2] ? aoFactor : 0f;
                ao1x  -= neigboors[0][1][2] ? aoFactor : 0f;
                ao1xy -= neigboors[0][0][2] ? aoFactor : 0f;
                ao2x  -= neigboors[2][1][2] ? aoFactor : 0f;
                ao2y  -= neigboors[1][0][2] ? aoFactor : 0f;
                ao2xy -= neigboors[2][0][2] ? aoFactor : 0f;
                ao3y  -= neigboors[0][1][2] ? aoFactor : 0f;
                ao3x  -= neigboors[1][2][2] ? aoFactor : 0f;
                ao3xy -= neigboors[0][2][2] ? aoFactor : 0f;
                ao4y  -= neigboors[2][1][2] ? aoFactor : 0f;
                ao4x  -= neigboors[1][2][2] ? aoFactor : 0f;
                ao4xy -= neigboors[2][2][2] ? aoFactor : 0f;

                break;
            case RIGHT:
                for (int j = 0; j < 4; j++) {
                    normals.add(Transform.RIGHT);
                }
                ao1y  -= neigboors[1][2][0] ? aoFactor : 0f;
                ao1x  -= neigboors[0][2][1] ? aoFactor : 0f;
                ao1xy -= neigboors[0][2][0] ? aoFactor : 0f;
                ao2x  -= neigboors[2][2][1] ? aoFactor : 0f;
                ao2y  -= neigboors[1][2][0] ? aoFactor : 0f;
                ao2xy -= neigboors[2][2][0] ? aoFactor : 0f;
                ao3y  -= neigboors[0][2][1] ? aoFactor : 0f;
                ao3x  -= neigboors[1][2][2] ? aoFactor : 0f;
                ao3xy -= neigboors[0][2][2] ? aoFactor : 0f;
                ao4y  -= neigboors[2][2][1] ? aoFactor : 0f;
                ao4x  -= neigboors[1][2][2] ? aoFactor : 0f;
                ao4xy -= neigboors[2][2][2] ? aoFactor : 0f;
                break;
            case LEFT:
                for (int j = 0; j < 4; j++) {
                    normals.add(Transform.LEFT);
                }
                ao1y  -= neigboors[1][0][0] ? aoFactor : 0f;
                ao1x  -= neigboors[0][0][1] ? aoFactor : 0f;
                ao1xy -= neigboors[0][0][0] ? aoFactor : 0f;
                ao2x  -= neigboors[2][0][1] ? aoFactor : 0f;
                ao2y  -= neigboors[1][0][0] ? aoFactor : 0f;
                ao2xy -= neigboors[2][0][0] ? aoFactor : 0f;
                ao3y  -= neigboors[0][0][1] ? aoFactor : 0f;
                ao3x  -= neigboors[1][0][2] ? aoFactor : 0f;
                ao3xy -= neigboors[0][0][2] ? aoFactor : 0f;
                ao4y  -= neigboors[2][0][1] ? aoFactor : 0f;
                ao4x  -= neigboors[1][0][2] ? aoFactor : 0f;
                ao4xy -= neigboors[2][0][2] ? aoFactor : 0f;
                break;

        }
        ambientOcclusion.add(Math.max(0,ao1x));
        ambientOcclusion.add(Math.max(0,ao1y));
        ambientOcclusion.add(Math.max(0,ao1xy));
        ambientOcclusion.add(Math.max(0,ao2x));
        ambientOcclusion.add(Math.max(0,ao2y));
        ambientOcclusion.add(Math.max(0,ao2xy));
        ambientOcclusion.add(Math.max(0,ao3x));
        ambientOcclusion.add(Math.max(0,ao3y));
        ambientOcclusion.add(Math.max(0,ao3xy));
        ambientOcclusion.add(Math.max(0,ao4x));
        ambientOcclusion.add(Math.max(0,ao4y));
        ambientOcclusion.add(Math.max(0,ao4xy));
        //indices.add(i+2);
        //indices.add(i+3);
        //indices.add(i+1);
        //indices.add(i);
        indices.add(i + 3);
        indices.add(i + 1);
        indices.add(i);

        indices.add(i);
        indices.add(i + 2);
        indices.add(i + 3);

    }

    public Mesh ambientOcclusion(float[] ao) {
        vao.put(ao, VoxelShader.AMBIENT_OCCLUSION_ID, VoxelShader.AMBIENT_UCCLUSION_SIZE);
        return this;
    }
}
