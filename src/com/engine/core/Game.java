package com.engine.core;

import com.engine.rendering.Display;

public class Game {

    public Game()
    {
        start();
    }
    public void start()
    {
        GameLoop.start();
    }
}
