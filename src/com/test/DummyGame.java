package com.test;

import com.engine.core.GameLogic;
import com.engine.geometry.Geometry;
import com.engine.geometry.TexturedGeometry;
import com.engine.rendering.shader.Shaders;
import com.engine.util.Color;
import com.engine.util.Texture;

public class DummyGame implements GameLogic {
    Geometry g;
    TexturedGeometry tg;
    Geometry cg;
    @Override
    public void init() {
        //tg=new TexturedGeometry(new Texture("res/textures/smiley.png"),Geometry.Quad.VERTICES,Geometry.Quad.INDEXES,Geometry.Quad.UVS,Shaders.TEXTURE_SHADER);
        //g=new Geometry(Geometry.Quad.VERTICES,Geometry.Quad.INDEXES, Shaders.STATIC_SHADER);
        Color[] colors=new Color[4];

        colors[0]=Color.RED;
        colors[1]=Color.BLUE;
        colors[2]=Color.GREEN;
        colors[3]=Color.WHITE;
        cg=new Geometry(Geometry.Quad.VERTICES,Geometry.Quad.INDEXES,colors,Shaders.VERTEX_COLOR_SHADER);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        cg.render();
        //tg.render();
        //g.render();
    }

    @Override
    public void dispose() {

    }
}
