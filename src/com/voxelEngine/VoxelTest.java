package com.voxelEngine;

import com.engine.core.GameLogic;
import com.engine.core.GameOptions;
import com.engine.entity.Camera;
import com.engine.entity.MeshRenderer;
import com.engine.entity.light.DirectionalLight;
import com.engine.entity.light.PointLight;
import com.engine.exemples.FirstPersonCameraController;
import com.engine.materials.Material;
import com.engine.inputs.Input;
import com.engine.rendering.Renderer;
import com.engine.rendering.shaders.Shaders;
import com.engine.util.Color;
import com.engine.util.noise.OpenSimplexNoise;

import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector3i;
import org.lwjgl.glfw.GLFW;

import java.util.Random;

public class VoxelTest implements GameLogic {
    MeshRenderer mr;
    Vector3i size=new Vector3i(16,16,16);
    FirstPersonCameraController cameraController;
    OpenSimplexNoise noise;
    @Override
    public void init() {
        GameOptions.PRINT_FPS=true;
        GameOptions.AMBIENT_LIGHT=.2f;
        PointLight.Lights.add(new PointLight(new Vector3f(0,1,0),Color.YELLOW,10));
        Renderer.setClearColor(Color.GRAY);
        cameraController=new FirstPersonCameraController(new Vector3f(),new Quaternionf(), Camera.main,10);
        noise=new OpenSimplexNoise();
        boolean[][][] data =new boolean[size.y][size.x][size.z];
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x <size.x ; x++) {
                for (int z = 0; z <size.z ; z++) {
                    data[y][x][z]=noise.eval(x*0.1,y*0.1,z*0.1)>=0f;
                }
            }
        }
        VoxelChunk chunk=new VoxelChunk(data);

        mr=new MeshRenderer(new Vector3f(),new Quaternionf(),1f,chunk,new Material().shader(Shaders.VERTEX_COLOR_SHADER));
    }

    @Override
    public void update(float delta) {
        cameraController.update(delta);
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_1))
        {
            Renderer.setWireframe(true);
        }
        else
        {
            Renderer.setWireframe(false);
        }
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_R))
        {
            Random rnd=new Random();
            noise=new OpenSimplexNoise(rnd.nextInt());
            boolean[][][] data =new boolean[size.y][size.x][size.z];
            for (int y = 0; y < size.y; y++) {
                for (int x = 0; x <size.x ; x++) {
                    for (int z = 0; z <size.z ; z++) {
                        data[y][x][z]=noise.eval(x*0.1,y*0.1,z*0.1)>=0f;
                    }
                }
            }
            VoxelChunk chunk=(VoxelChunk) mr.getMesh();
            chunk.setData(data);
        }
    }

    @Override
    public void render() {
        mr.render();
    }

    @Override
    public void dispose() {

    }
}
