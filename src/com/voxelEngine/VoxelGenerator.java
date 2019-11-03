package com.voxelEngine;

import org.joml.Vector3i;

public interface VoxelGenerator {
    boolean[][][] generate(Vector3i position);

}
