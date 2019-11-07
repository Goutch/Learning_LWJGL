package com.engine.examples.voxelEngine;

import org.joml.Vector3i;

public interface VoxelGenerator {
    Block[][][] generate(Vector3i position);

}
