package com.test;

import com.engine.core.GameLoop;

public class Main {
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;

    public static void main(String[] args) {
        GameLoop.start(new DummyGame());
    }
}

