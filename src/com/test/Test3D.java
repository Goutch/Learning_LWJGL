package com.test;

import com.engine.core.GameLogic;
import com.engine.entity.Entity;
import com.engine.entity.MeshRenderer;
import com.engine.entity.gui.Panel;
import com.engine.entity.light.DirectionalLight;
import com.engine.entity.light.PointLight;
import com.engine.examples.FirstPersonCameraController;
import com.engine.geometry.*;

import com.engine.entity.Camera;
import com.engine.inputs.Input;
import com.engine.rendering.Renderer;
import com.engine.rendering.Window;
import com.engine.materials.Material;
import com.engine.rendering.shaders.Shaders;
import com.engine.util.Color;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import java.util.LinkedList;


public class Test3D implements GameLogic {
    LinkedList<Entity> entities = new LinkedList<Entity>();
    LinkedList<Panel> GUIEntities = new LinkedList<Panel>();

    MeshRenderer dragon1;
    MeshRenderer dragon2;
    MeshRenderer terrain;
    FirstPersonCameraController cameraController;

    @Override
    public void init() {
        Window.centerWindow();

        //Camera
        cameraController=new FirstPersonCameraController(new Vector3f(0, 1, 0), new Quaternionf(), Camera.main, 10);

        //materials
        Material daragonMat=new Material().color(new Color(0.8f,.2f,0f,0.5f)).shader(Shaders.DIFFUSE_LIGHT_SHADER).shineFactor(2f).dampFactor(20);
        Material cubeMat=new Material().shader(Shaders.VERTEX_COLOR_SHADER);
        Material terrainMat=new Material().color(new Color(0.2f,0.7f,0.2f,1)).shader(Shaders.DIFFUSE_LIGHT_SHADER);

        //meshes
        Mesh dragonMesh=ModelImporter.ImportModel("res/models/dragon.obj");
        Mesh cubeMesh = ModelImporter.ImportModel("res/models/cube.obj");
        Mesh sphereMesh = ModelImporter.ImportModel("res/models/sphere.obj");
        Mesh terrainMesh = Geometry.getPlane(100, 100);

        terrain=new MeshRenderer(
                new Vector3f(0,0,0)
                ,new Quaternionf(),
                1f,
                terrainMesh,
                terrainMat);
        dragon1=new MeshRenderer(
                new Vector3f(0,0,-10),
                new Quaternionf(),
                1f,
                dragonMesh,
                daragonMat);
        dragon2=new MeshRenderer(
                new Vector3f(0,0,10),
                new Quaternionf(),
                1f,
                dragonMesh,
                daragonMat);

        float range=25;
        int amount=100;
       // colored cubes mesh
        for (int i = 0; i < amount; i++) {
            entities.add(new MeshRenderer(
                    getRandomVector(-range,range,0,range,-range,range),
                    new Quaternionf(),
                    1f,
                    cubeMesh,
                    cubeMat));
        }

        Color[] cubeColors = new Color[cubeMesh.getVertices().length];
        for (int i = 0; i < cubeColors.length; i++) {
            cubeColors[i] = Color.RED;
            i++;
            cubeColors[i] = Color.GREEN;
            i++;
            cubeColors[i] = Color.BLUE;
        }
        cubeMesh.colors(cubeColors);
        DirectionalLight.Lights.add(new DirectionalLight(new Vector3f(0,1,0),new Quaternionf().rotateXYZ((float) Math.PI/3,(float) Math.PI/3,0),new Color(1f,1f,0.7f,1f)));

        PointLight.Lights.add(new PointLight(new Vector3f(0,10,0),Color.WHITE,10));
        for (PointLight p: PointLight.Lights) {
            Entity light=new MeshRenderer(
                    new Vector3f(0,0,0),
                    new Quaternionf(),
                    0.25f,
                    sphereMesh,
                    new Material().color(p.getColor()).shader(Shaders.BASE_SHADER));
            light.transform.setParent(p.transform);
            entities.add(light);
        }
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

    }

    @Override
    public void render() {
        for (Entity e : entities) {
            e.render();
        }
        for (Panel p:
             GUIEntities) {
            p.render();
        }
        dragon1.render();
        dragon2.render();
        terrain.render();
    }

    @Override
    public void dispose() {

    }

    private Vector3f getRandomVector(float minX,float maxX,float minY,float maxY,float minZ,float maxZ){
        return  new Vector3f(
                (float) (Math.random() * Math.abs(maxX-minX)) +minX,
                 (float) (Math.random() * Math.abs(maxY-minY)) +minY,
                (float) (Math.random() * Math.abs(maxZ-minZ)) +minZ);
    }
}
