package com.engine.examples.voxelEngine;

import com.engine.core.GameLogic;
import com.engine.core.GameOptions;
import com.engine.entity.Camera;

import com.engine.entity.light.DirectionalLight;
import com.engine.entity.light.Light;
import com.engine.entity.light.PointLight;
import com.engine.examples.FirstPersonCameraController;

import com.engine.inputs.Input;
import com.engine.rendering.Renderer;

import com.engine.rendering.Window;
import com.engine.util.Color;


import org.joml.Quaternionf;
import org.joml.Vector3f;

import org.lwjgl.glfw.GLFW;

import java.util.Collection;


public class VoxelTest implements GameLogic {
    FirstPersonCameraController cameraController;
    VoxelWorld voxelWorld;
    @Override
    public void init() {
        GameOptions.PRINT_FPS=true;
        GameOptions.AMBIENT_LIGHT=0.2f;
        Renderer.setClearColor(new Color(.1f,.4f,1f,1f));
        voxelWorld=new VoxelWorld(new TestGenerator());
        cameraController=new FirstPersonCameraController(new Vector3f(),new Quaternionf(), Camera.main,20);
        voxelWorld.chunkLoader=cameraController;
        DirectionalLight.Lights.add(new DirectionalLight(new Vector3f(),new Quaternionf().rotateXYZ((float) Math.toRadians(90),0,0), Color.WHITE ));
        Vector3f f=DirectionalLight.Lights.get(0).transform.forward();

    }

    @Override
    public void update(float delta) {
        voxelWorld.update(delta);
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
            voxelWorld=new VoxelWorld(new TestGenerator());
            voxelWorld.chunkLoader=cameraController;
        }
        if (Input.IsKeyPressed(GLFW.GLFW_KEY_Q))
        {
           Vector3f f= cameraController.transform.forward();
            System.out.println("("+f.x+","+f.y+","+f.z+")");
        }
    }

    @Override
    public void render() {
        voxelWorld.render();
    }

    @Override
    public void dispose() {

    }
}
