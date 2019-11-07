package com.engine.examples.voxelEngine;

import com.engine.util.noise.OpenSimplexNoise;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.util.Random;

public class TestGenerator implements VoxelGenerator {

    private OpenSimplexNoise noise;
    private float scale1 = 0.005f;
    private Vector2f offset1;

    private float scale2 = 0.1f;
    private float weight2 = 0.02f;
    private Vector2f offset2;

    private float caveScale = 0.05f;
    private Vector3f caveOffset;

    public TestGenerator() {
        Random rnd = new Random();
        noise = new OpenSimplexNoise(rnd.nextInt());
        offset1 = new Vector2f(rnd.nextFloat(), rnd.nextFloat());
        offset2 = new Vector2f(rnd.nextFloat(), rnd.nextFloat());
        caveOffset = new Vector3f(rnd.nextFloat(), rnd.nextFloat(), rnd.nextFloat());
    }

    @Override
    public Block[][][] generate(Vector3i position) {
        Vector3i pos = new Vector3i(position).mul(VoxelWorld.CHUNK_SIZE);
        Block[][][] data = new Block[VoxelWorld.CHUNK_SIZE.y][VoxelWorld.CHUNK_SIZE.x][VoxelWorld.CHUNK_SIZE.z];
        int[][] heightMap = getHeightMap(pos);
        for (int x = 0; x < VoxelWorld.CHUNK_SIZE.x; x++) {
            for (int z = 0; z < VoxelWorld.CHUNK_SIZE.z; z++) {
                for (int y = 0; y < VoxelWorld.CHUNK_SIZE.y; y++) {
                    if (y > heightMap[x][z]) {
                        break;
                    }
                    else{
                        boolean isHole=noise.eval((pos.x + caveOffset.x + x) * caveScale,(pos.y + caveOffset.y + y) * caveScale, (pos.z + caveOffset.z + z) * caveScale)<=-0.3f;
                        if(!isHole)
                        {
                            if (y < heightMap[x][z] - 3) {
                                data[y][x][z] =Block.STONE;
                            }
                            else if(y < heightMap[x][z])
                            {
                                data[y][x][z] =Block.DIRT;
                            }
                            else if (y == heightMap[x][z])
                            {
                                data[y][x][z] = Block.GRASS;
                            }
                        }
                    }

                }
            }
        }
        return data;
    }

    public int[][] getHeightMap(Vector3i pos) {
        int[][] heightMap = new int[VoxelWorld.CHUNK_SIZE.x][VoxelWorld.CHUNK_SIZE.z];
        for (int x = 0; x < VoxelWorld.CHUNK_SIZE.x; x++) {
            for (int z = 0; z < VoxelWorld.CHUNK_SIZE.z; z++) {
                double value1 = ((noise.eval((pos.x + offset1.x + x) * scale1, (pos.z + offset1.y + z) * scale1)));
                double value2 = weight2 * ((noise.eval((pos.x + offset2.x + x) * scale2, (pos.z + offset2.y + z) * scale2)));
                heightMap[x][z] = (int) ((0.5f + value1 + value2) * VoxelWorld.CHUNK_SIZE.y);
            }
        }
        return heightMap;
    }
}
