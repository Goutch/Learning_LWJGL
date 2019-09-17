package com.test;

import com.engine.graphics.DisplayManager;

public class Main {
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;

    public static void main(String[] args) {
        DisplayManager d = new DisplayManager(WINDOW_WIDTH, WINDOW_HEIGHT);
    }
}
