package com.test;

import com.engine.core.GameLogic;
import com.engine.geometry.Geometry;
import com.engine.rendering.shader.Shaders;

public class DummyGame implements GameLogic {
    Geometry g;
    @Override
    public void init() {
        g=new Geometry(Geometry.Quad.VERTICES,Geometry.Quad.INDEXES, Shaders.COLOR_SHADER);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        g.render();
    }

    @Override
    public void dispose() {

    }
}
