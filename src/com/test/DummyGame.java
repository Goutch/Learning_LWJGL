package com.test;

import com.engine.core.GameLogic;
import com.engine.core.GameOptions;
import com.engine.entities.Light;
import com.engine.entities.MeshRenderer;
import com.engine.entities.Transform;
import com.engine.geometry.*;

import com.engine.entities.Camera;
import com.engine.rendering.Display;
import com.engine.rendering.Material;
import com.engine.rendering.Renderer;
import com.engine.rendering.shader.Shaders;
import com.engine.util.Color;
import com.engine.util.Texture;
import org.joml.Vector3f;

import java.util.LinkedList;
import java.util.Random;


public class DummyGame implements GameLogic {
    LinkedList<MeshRenderer> cubes = new LinkedList<MeshRenderer>();
    MeshRenderer terrain;
    MeshRenderer dragon;
    @Override
    public void init() {
        Display.centerWindow();
        GameOptions.PRINT_FPS=true;
        //camera
        Camera.setMainCamera(new FirstPersonCam(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), 90, 10, 3));

        Material.DEFAULT=new Material().shader(Shaders.DIFFUSE_LIGHT_SHADER).shineFactor(1f).dampFactor(100);
        Material mat=new Material().shader(Shaders.VERTEX_COLOR_SHADER);
        Material walnutMat=new Material().texture(new Texture("res/models/walnut.jpg")).shader(Shaders.TEXTURE_SHADER);
        Mesh dragonMesh=ModelImporter.ImportModel("res/models/dragon.obj");
        Mesh cubeMesh = ModelImporter.ImportModel("res/models/cube.obj");
        Mesh sphereMesh = ModelImporter.ImportModel("res/models/sphere.obj");
        Mesh walnutMesh= ModelImporter.ImportModel("res/models/walnut.obj");
        Mesh terrainMesh = Geometry.getPlane(100, 100);
        terrainMesh.setColors(Color.GREEN);
        terrainMesh.build();

        terrain = new MeshRenderer(new Vector3f(0, -0.5f, 0), Transform.ZERO, 1,terrainMesh,mat);
        dragon=new MeshRenderer(
                new Vector3f(0,0,10),
                new Vector3f(0,0,0),
                1f,
                dragonMesh);

        for (int i = 0; i < 100; i++) {
            cubes.add(new MeshRenderer(
                    new Vector3f(
                            (float) (Math.random() * 50f) - 25,
                            (float) (Math.random() * 25),
                            (float) (Math.random() * 50) - 25),
                    new Vector3f(
                            (float) Math.random() * 360f,
                            (float) Math.random() * 360,
                            (float) Math.random() * 360),
                    0.25f,

                    sphereMesh,
                    mat));
        }
        for (int i = 0; i < 100; i++) {
            cubes.add(new MeshRenderer(
                    new Vector3f(
                            (float) (Math.random() * 50) - 25,
                            (float) (Math.random() * 25),
                            (float) (Math.random() * 50) - 25),
                    new Vector3f(
                            (float) Math.random() * 360f,
                            (float) Math.random() * 360,
                            (float) Math.random() * 360),
                    1f,
                    cubeMesh,
                    mat));
        }
        for (int i = 0; i < 10; i++) {
            cubes.add(new MeshRenderer(
                    new Vector3f(
                            (float) (Math.random() * 10f) - 5,
                            (float) (Math.random() * 10) ,
                            (float) (Math.random() * 10) - 5),
                    new Vector3f(
                            (float) Math.random() * 360f,
                            (float) Math.random() * 360,
                            (float) Math.random() * 360),
                    1f,
                    walnutMesh,
                    walnutMat));
        }

        Color[] sphereColors = new Color[sphereMesh.getVertices().length];
        for (int i = 0; i < sphereColors.length; i++) {
            sphereColors[i] = Color.RED;
            i++;
            sphereColors[i] = Color.GREEN;
            i++;
            sphereColors[i] = Color.BLUE;
        }
        sphereMesh.setColors(sphereColors);
        sphereMesh.build();
        Color[] cubeColors = new Color[cubeMesh.getVertices().length];
        for (int i = 0; i < cubeColors.length; i++) {
            cubeColors[i] = Color.RED;
            i++;
            cubeColors[i] = Color.GREEN;
            i++;
            cubeColors[i] = Color.BLUE;
        }
        cubeMesh.setColors(cubeColors);
        cubeMesh.build();

    }

    @Override
    public void update(float delta) {

        //Light.main.transform.position=Camera.main.transform.position;
        //dragon.transform.rotation.y+=180*delta;
    }

    @Override
    public void render() {

        for (MeshRenderer mr : cubes) {
            mr.render();
        }
        dragon.render();
        terrain.render();

    }

    @Override
    public void dispose() {

    }
}
