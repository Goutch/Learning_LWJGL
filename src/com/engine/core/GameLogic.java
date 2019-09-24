package com.engine.core;

public interface GameLogic {
    void init();
    void update(float delta);
    void render();
    void dispose();
}
