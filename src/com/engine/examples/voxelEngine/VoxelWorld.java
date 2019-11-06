package com.engine.examples.voxelEngine;

import com.engine.entity.Entity;
import com.engine.entity.MeshRenderer;
import com.engine.events.DisposeListener;
import com.engine.events.RenderListener;
import com.engine.events.UpdateListener;
import com.engine.inputs.Input;
import com.engine.materials.Material;
import com.engine.rendering.shaders.Shaders;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector3i;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;

public class VoxelWorld implements UpdateListener, RenderListener, DisposeListener {
    public static final Vector3i CHUNK_SIZE = new Vector3i(16, 32, 16);
    private HashMap<Vector3i, boolean[][][]> chunksData = new HashMap<>();
    private HashMap<Vector3i, MeshRenderer> meshRenderersPool = new HashMap<>();
    public Entity chunkLoader;
    private int range = 7;
    private VoxelGenerator generator;

    public VoxelWorld(VoxelGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void update(float delta) {
        Vector3i chunkPos = getChunkPosition(chunkLoader.transform.getGlobalPosition());
        outerLoop:
        for (int i = -range; i <= range; i++) {
            for (int j = -range; j <= range; j++) {
                int x = chunkPos.x + i;
                int z = chunkPos.z + j;
                Vector3i cp = new Vector3i(x, 0, z);
                if (!meshRenderersPool.containsKey(cp)) {
                    createChunkMesh(cp);
                    break outerLoop;
                }
            }
        }
    }

    @Override
    public void render() {
        for (MeshRenderer mr :
                meshRenderersPool.values()) {
            mr.render();
        }
    }

    private void createChunkMesh(Vector3i chunkPos) {
        meshRenderersPool.put(chunkPos, new MeshRenderer(new Vector3f(chunkPos.x * CHUNK_SIZE.x, chunkPos.y * CHUNK_SIZE.y, chunkPos.z * CHUNK_SIZE.z),
                new Quaternionf(),
                1,
                new VoxelChunk(this, chunkPos),
                new Material()
                        .shader(Shaders.VERTEX_COLOR_SHADER)));
    }

    public Vector3i getChunkPosition(Vector3f pos) {
        Vector3i chunkPos = new Vector3i();
        chunkPos.x = Math.floorDiv((int) pos.x, CHUNK_SIZE.x);
        chunkPos.y = Math.floorDiv((int) pos.y, CHUNK_SIZE.y);
        chunkPos.z = Math.floorDiv((int) pos.z, CHUNK_SIZE.z);
        return chunkPos;
    }

    public Vector3i getChunkPosition(Vector3i pos) {
        Vector3i chunkPos = new Vector3i(pos);
        chunkPos.x = Math.floorDiv(pos.x, CHUNK_SIZE.x);
        chunkPos.y = Math.floorDiv(pos.y, CHUNK_SIZE.y);
        chunkPos.z = Math.floorDiv(pos.z, CHUNK_SIZE.z);
        return chunkPos;
    }

    public boolean[][][] getChunk(Vector3i chunkPos) {

        if (chunksData.containsKey(chunkPos)) {
            return chunksData.get(chunkPos);
        } else {
            boolean[][][] data = generator.generate(chunkPos);
            chunksData.put(chunkPos, data);
            return data;
        }
    }

    public boolean getVoxel(Vector3i pos) {
        Vector3i chunkPos = getChunkPosition(pos);
        Vector3i chunkOffSet = new Vector3i().set(chunkPos).mul(CHUNK_SIZE);
        if (chunksData.containsKey(chunkPos)) {
            return chunksData.get(chunkPos)[pos.y - chunkOffSet.y][pos.x - chunkOffSet.x][pos.z - chunkOffSet.z];
        } else {
            boolean[][][] data = generator.generate(chunkPos);
            chunksData.put(chunkPos, data);
            int x, y, z;
            y = pos.y - chunkOffSet.y;
            x = pos.x - chunkOffSet.x;
            z = pos.z - chunkOffSet.z;

            return data[y][x][z];
        }
    }

    @Override
    public void dispose() {
        for (MeshRenderer mr :
                meshRenderersPool.values()) {
            mr.getMesh().dispose();
        }
    }
}
