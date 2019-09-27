package com.test;

import com.engine.core.GameLogic;
import com.engine.entities.Entity;
import com.engine.geometry.Geometry;
import com.engine.geometry.Mesh;
import com.engine.rendering.shader.Shaders;
import com.engine.util.Color;
import com.engine.util.Texture;
import org.joml.Vector3f;


public class DummyGame implements GameLogic {
    Mesh sprite;
    Mesh cg;
    Entity e;
    @Override
    public void init() {
        //tg=new TexturedGeometry(,Geometry.Quad.VERTICES,Geometry.Quad.INDEXES,Geometry.Quad.UVS,Shaders.TEXTURE_SHADER);
        //g=new Geometry(Geometry.Quad.VERTICES,Geometry.Quad.INDEXES, Shaders.STATIC_SHADER);
        Color[] colors=new Color[4];

        colors[0]=Color.RED;
        colors[1]=Color.BLUE;
        colors[2]=Color.GREEN;
        colors[3]=Color.WHITE;
        sprite=new Mesh(Geometry.Quad.VERTICES,Geometry.Quad.INDEXES,Geometry.Quad.UVS,new Texture("res/textures/Untitled.png"),Shaders.TEXTURE_SHADER);
        e=new Entity(sprite,new Vector3f(0,0,0),new Vector3f(0,0,0),1f);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        e.render();
    }

    @Override
    public void dispose() {

    }
}
