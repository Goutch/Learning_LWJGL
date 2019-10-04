package com.test;

import com.engine.core.GameLogic;
import com.engine.core.GameOptions;
import com.engine.entities.MeshRenderer;
import com.engine.entities.Transform;
import com.engine.entities.light.DirectionalLight;
import com.engine.geometry.*;

import com.engine.entities.Camera;
import com.engine.rendering.Window;
import com.engine.geometry.Material;
import com.engine.rendering.shaders.Shaders;
import com.engine.util.Color;
import com.engine.util.Texture;
import org.joml.Vector3f;

import java.util.LinkedList;


public class DummyGame implements GameLogic {
    LinkedList<MeshRenderer> cubes = new LinkedList<MeshRenderer>();
    MeshRenderer terrain;
    MeshRenderer dragon;
    @Override
    public void init() {
        Window.centerWindow();
        GameOptions.PRINT_FPS=true;
        //camera
        Camera.setMainCamera(new FirstPersonCam(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), 90, 10, 3));

        Material daragonMat=new Material().color(new Color(.6f,.6f,0f,1f)).shader(Shaders.DIFFUSE_LIGHT_SHADER).shineFactor(10f).dampFactor(10);
        Material cubeMat=new Material().shader(Shaders.VERTEX_COLOR_SHADER);
        Material sphereMat=new Material().shader(Shaders.DIFFUSE_LIGHT_SHADER).color(Color.BLUE).shineFactor(10f).dampFactor(1);
        Material walnutMat=new Material().texture(new Texture("res/models/walnut.jpg")).shader(Shaders.TEXTURE_SHADER).color(Color.BLUE);
        Material terrainMat=new Material().color(Color.GREEN).shader(Shaders.DIFFUSE_LIGHT_SHADER);
        Mesh dragonMesh=ModelImporter.ImportModel("res/models/dragon.obj");
        Mesh cubeMesh = ModelImporter.ImportModel("res/models/cube.obj");
        Mesh sphereMesh = ModelImporter.ImportModel("res/models/sphere.obj");
        Mesh walnutMesh= ModelImporter.ImportModel("res/models/walnut.obj");
        Mesh terrainMesh = Geometry.getPlane(100, 100);

        terrain = new MeshRenderer(new Vector3f(0, -0.5f, 0), Transform.ZERO, 1,terrainMesh,terrainMat);
        dragon=new MeshRenderer(
                new Vector3f(0,0,-10),
                new Vector3f(0,0,0),
                1f,
                dragonMesh,
                daragonMat);

        float range=25;
        for (int i = 0; i < 10; i++) {
            cubes.add(new MeshRenderer(
                    getRandomVector(-range,range,0,range,-range,range),
                    getRandomVector(0,360,0,360,0,360),
                    0.25f,
                    sphereMesh,
                    sphereMat));
        }
        for (int i = 0; i < 10; i++) {
            cubes.add(new MeshRenderer(
                    getRandomVector(-range,range,0,range,-range,range),
                    getRandomVector(0,360,0,360,0,360),
                    1f,
                    cubeMesh,
                    cubeMat));
        }
        for (int i = 0; i < 10; i++) {
            cubes.add(new MeshRenderer(
                    getRandomVector(-range,range,0,range,-range,range),
                    getRandomVector(0,360,0,360,0,360),
                    1f,
                    walnutMesh,
                    walnutMat));
        }
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

        DirectionalLight.main.transform.position=Camera.main.transform.position;
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

    private Vector3f getRandomVector(float minX,float maxX,float minY,float maxY,float minZ,float maxZ){
        return  new Vector3f(
                (float) (Math.random() * maxX) +minX,
                 (float) (Math.random() * maxY) +minY,
                (float) (Math.random() * maxZ) +minZ);
    }
}
