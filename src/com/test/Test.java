package com.test;

import com.engine.core.GameLogic;
import com.engine.core.GameOptions;
import com.engine.entity.Entity;
import com.engine.entity.MeshRenderer;
import com.engine.entity.Transform;
import com.engine.entity.gui.Panel;
import com.engine.events.EventManager;
import com.engine.exemples.FirstPersonCameraController;
import com.engine.geometry.*;

import com.engine.entity.Camera;
import com.engine.gui.GUIMaterial;
import com.engine.inputs.Input;
import com.engine.rendering.Renderer;
import com.engine.rendering.Window;
import com.engine.geometry.Material;
import com.engine.rendering.shaders.Shaders;
import com.engine.util.Color;
import com.engine.util.Texture;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import java.util.LinkedList;


public class Test implements GameLogic {
    LinkedList<Entity> entities = new LinkedList<Entity>();
    LinkedList<Panel> GUIEntities = new LinkedList<Panel>();
    MeshRenderer pivot;
    MeshRenderer dragon1;
    MeshRenderer dragon2;
    MeshRenderer terrain;
    FirstPersonCameraController cameraController;
    Entity parent;
    @Override
    public void init() {
        Window.centerWindow();
        //GameOptions.PRINT_FPS=true;

        //Camera
        cameraController=new FirstPersonCameraController(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), Camera.main, 10);

        //GUI
        GUIEntities.add(new Panel(new Vector3f(-1,1,0),new Vector2f(0.7f,0.1f), Panel.PivotPoint.CENTER,new GUIMaterial().color(Color.RED)));
        GUIEntities.add(new Panel(new Vector3f(-1,1,0),new Vector2f(0.35f,0.1f), Panel.PivotPoint.CENTER,new GUIMaterial().color(Color.RED)));
        GUIEntities.add(new Panel(new Vector3f(1,1,0),new Vector2f(0.1f,0.1f), Panel.PivotPoint.CENTER,new GUIMaterial().color(Color.RED)));
        //materials
        Material daragonMat=new Material().color(new Color(0.8f,.2f,0f,0.5f)).shader(Shaders.DIFFUSE_LIGHT_SHADER).shineFactor(2f).dampFactor(20);
        Material cubeMat=new Material().shader(Shaders.VERTEX_COLOR_SHADER);
        Material sphereMat=new Material().shader(Shaders.DIFFUSE_LIGHT_SHADER);
        Material walnutMat=new Material().texture(new Texture("res/models/walnut.jpg")).shader(Shaders.TEXTURE_SHADER).shineFactor(1f).dampFactor(30f);
        Material terrainMat=new Material().color(Color.GREEN).shader(Shaders.DIFFUSE_LIGHT_SHADER);


        //meshes
        Mesh dragonMesh=ModelImporter.ImportModel("res/models/dragon.obj");
        Mesh cubeMesh = ModelImporter.ImportModel("res/models/cube.obj");
        Mesh sphereMesh = ModelImporter.ImportModel("res/models/sphere.obj");
        Mesh walnutMesh= ModelImporter.ImportModel("res/models/walnut.obj");
        Mesh terrainMesh = Geometry.getPlane(100, 100);
        pivot=new MeshRenderer(new Vector3f(),new Vector3f(),1f,cubeMesh);
        EventManager.subscribeRender(pivot);
        terrain=new MeshRenderer(
                new Vector3f(0,0,0)
                ,Transform.ZERO,
                1f,
                terrainMesh,
                terrainMat);
        dragon1=new MeshRenderer(
                new Vector3f(0,0,-10),
                new Vector3f(0,0,0),
                1f,
                dragonMesh,
                daragonMat);
        dragon2=new MeshRenderer(
                new Vector3f(0,0,10),
                new Vector3f(0,0,0),
                1f,
                dragonMesh,
                daragonMat);
        //Camera.main.setParent(dragon1);
        float range=25;
        int amount=100;

        //
        //for (int i = 0; i < amount; i++) {
        //    entities.add(new MeshRenderer(
        //            getRandomVector(-range,range,0,range,-range,range),
        //            getRandomVector(0,360,0,360,0,360),
        //            0.25f,
        //            sphereMesh,
        //            sphereMat));
        //}
        ////colored cubes mesh
        //for (int i = 0; i < amount; i++) {
        //    entities.add(new MeshRenderer(
        //            getRandomVector(-range,range,0,range,-range,range),
        //            getRandomVector(0,360,0,360,0,360),
        //            1f,
        //            cubeMesh,
        //            cubeMat));
        //}
        ////textured mesh
        //for (int i = 0; i < amount; i++) {
        //    entities.add(new MeshRenderer(
        //            getRandomVector(-range,range,0,range,-range,range),
        //            getRandomVector(0,360,0,360,0,360),
        //            1f,
        //            walnutMesh,
        //            walnutMat));
        //}
        //for (Entity e:entities)
        //{
        //    e.transform.setParent(pivot.transform);
        //}
        Color[] cubeColors = new Color[cubeMesh.getVertices().length];
        for (int i = 0; i < cubeColors.length; i++) {
            cubeColors[i] = Color.RED;
            i++;
            cubeColors[i] = Color.GREEN;
            i++;
            cubeColors[i] = Color.BLUE;
        }
        cubeMesh.colors(cubeColors);
        cubeMesh.build();
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
        pivot.transform.rotate(new Vector3f(0,10*delta,0));
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_RIGHT))
        {
            Vector2f add=new Vector2f(GUIEntities.get(1).getSize());
            add.x=add.x+delta;
            GUIEntities.get(1).setSize(add);
        }
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_LEFT))
        {
            Vector2f add=new Vector2f(GUIEntities.get(1).getSize());
            add.x=add.x-delta;
            GUIEntities.get(1).setSize(add);
        }
        //DirectionalLight.main.transform.position=Camera.main.transform.position;
    }

    @Override
    public void render() {
       // for (Entity e : entities) {
       //     e.render();
       // }
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
