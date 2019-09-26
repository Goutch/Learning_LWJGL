package com.test;

import com.engine.core.GameLogic;
import com.engine.entities.GameEntity;
import com.engine.geometry.Geometry;
import com.engine.rendering.shader.Shaders;
import com.engine.util.Color;
import com.engine.util.Texture;
import org.joml.Vector3f;


public class DummyGame implements GameLogic {
    Geometry g;
    Geometry cg;
    GameEntity e;
    @Override
    public void init() {
        //tg=new TexturedGeometry(new Texture("res/textures/smiley.png"),Geometry.Quad.VERTICES,Geometry.Quad.INDEXES,Geometry.Quad.UVS,Shaders.TEXTURE_SHADER);
        //g=new Geometry(Geometry.Quad.VERTICES,Geometry.Quad.INDEXES, Shaders.STATIC_SHADER);
        Color[] colors=new Color[4];

        colors[0]=Color.RED;
        colors[1]=Color.BLUE;
        colors[2]=Color.GREEN;
        colors[3]=Color.WHITE;
        cg=new Geometry(Geometry.Quad.VERTICES,Geometry.Quad.INDEXES,Shaders.STATIC_SHADER);
        e=new GameEntity(cg,new Vector3f(-1,0,0),new Vector3f(0,0,0),1f);
    }

    @Override
    public void update(float delta) {
        e.transform.rotation.z+=360*delta;
        e.transform.position.x+=0.1*delta;
        e.transform.scale-=0.001f;
    }

    @Override
    public void render() {
        e.render();
        //cg.render();
        //tg.render();
        //g.render();
    }

    @Override
    public void dispose() {

    }
}
