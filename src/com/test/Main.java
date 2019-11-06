package com.test;

import com.engine.core.GameLoop;
import com.engine.examples.voxelEngine.VoxelTest;


public class Main {
    public static void main(String[] args) {
        GameLoop.start(new VoxelTest());
    }
}

