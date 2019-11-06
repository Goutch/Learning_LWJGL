package com.engine.examples.voxelEngine;

import com.engine.util.noise.OpenSimplexNoise;
import org.joml.Vector3i;

import java.util.Random;

public class TestGenerator implements VoxelGenerator {

    private OpenSimplexNoise noise;
    private float scale=0.05f;
    public TestGenerator()
    {
        Random rnd=new Random();
        noise=new OpenSimplexNoise(rnd.nextInt());
    }
    @Override
    public boolean[][][] generate(Vector3i position) {
        Vector3i pos=new Vector3i(position).mul(VoxelWorld.CHUNK_SIZE);
        boolean[][][] data =new boolean[VoxelWorld.CHUNK_SIZE.y][VoxelWorld.CHUNK_SIZE.x][VoxelWorld.CHUNK_SIZE.z];
        for (int y = 0; y < VoxelWorld.CHUNK_SIZE.y; y++) {
            for (int x = 0; x <VoxelWorld.CHUNK_SIZE.x ; x++) {
                for (int z = 0; z <VoxelWorld.CHUNK_SIZE.z ; z++) {
                    data[y][x][z]=noise.eval((pos.x+x)*scale,(pos.y+y)*scale,(pos.z+z)*scale)>=0f;
                }
            }
        }
        return data;
    }
}
