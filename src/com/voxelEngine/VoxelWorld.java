package com.voxelEngine;

import com.engine.core.GameLogic;
import com.engine.entity.MeshRenderer;
import com.engine.materials.Material;
import com.engine.rendering.shaders.Shaders;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.HashMap;

public class VoxelWorld implements GameLogic {
        public static final Vector3i CHUNK_SIZE=new Vector3i(16,16,16);
    private HashMap<Vector3i, boolean[][][]> chunksData = new HashMap<>();
    private ArrayList<MeshRenderer> meshRenderersPool = new ArrayList<>();

    public VoxelWorld(VoxelGenerator generator) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Vector3i pos = new Vector3i(i, 0, j);
                chunksData.put(pos, generator.generate(pos));
                meshRenderersPool.add(new MeshRenderer(new Vector3f(pos.x*CHUNK_SIZE.x,pos.y*CHUNK_SIZE.y,pos.z*CHUNK_SIZE.z),
                        new Quaternionf(),
                        1,
                        new VoxelChunk(chunksData.get(pos)),
                        new Material()
                                .shader(Shaders.VOXEL_SHADER)));
            }
        }
    }

        @Override
        public void init() {

        }

        @Override
        public void update(float delta) {

        }

        @Override
        public void render() {
                for (MeshRenderer mr:
                     meshRenderersPool) {
                        mr.render();
                }
        }

        @Override
        public void dispose() {

        }
}
