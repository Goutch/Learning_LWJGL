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

import com.test.Test;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector3i;
import org.lwjgl.glfw.GLFW;

import java.util.Random;

public class VoxelTest implements GameLogic {
    FirstPersonCameraController cameraController;
    VoxelWorld voxelWorld;
    @Override
    public void init() {
        GameOptions.PRINT_FPS=true;
        GameOptions.AMBIENT_LIGHT=1f;
        Renderer.setClearColor(Color.GRAY);
        voxelWorld=new VoxelWorld(new TestGenerator());
        voxelWorld.init();
        cameraController=new FirstPersonCameraController(new Vector3f(),new Quaternionf(), Camera.main,10);

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
