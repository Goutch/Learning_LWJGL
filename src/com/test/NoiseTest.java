package com.test;

import com.engine.core.GameLogic;
import com.engine.core.GameOptions;
import com.engine.entity.gui.Panel;
import com.engine.materials.GUIMaterial;
import com.engine.inputs.Input;
import com.engine.rendering.Window;
import com.engine.util.Color;
import com.engine.util.noise.OpenSimplexNoise;
import com.engine.util.Texture;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class NoiseTest implements GameLogic {
    private final int WIDTH=400;
    private final int HEIGHT=300;
    OpenSimplexNoise noise;
    private int seed=10;
    private final float speed=100;
    private float scale=4f;
    private Vector3f offset =new Vector3f();
    Panel display;
    @Override
    public void init() {
        noise=new OpenSimplexNoise(seed);
        GameOptions.PRINT_FPS=true;
    }
    @Override
    public void update(float delta) {
        offset.z+=delta*0.1;
        scale =0.0001f+((float) Input.getScroll()*0.01f);
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_LEFT))
        {
            offset.x-=speed*delta;
        }
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_RIGHT))
        {
            offset.x+=speed*delta;
        }
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_UP))
        {
            offset.y-=speed*delta;
        }
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_DOWN))
        {
            offset.y+=speed*delta;
        }
        Color[] colors=new Color[WIDTH*HEIGHT];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                int index=i*WIDTH+j;
                float v=(float) noise.eval((offset.x+j)*scale,(offset.y+i)*scale,offset.z)+0.5f;
                colors[index]=new Color(v,v,v,1);
            }
        }
        display=new Panel(new Vector3f(0,0,0),new Vector2f(1.9f,1.9f/Window.getAspectRatio()),new GUIMaterial().texture(new Texture(colors,WIDTH,HEIGHT)));
    }

    @Override
    public void render() {
        display.render();
    }

    @Override
    public void dispose() {

    }
}
