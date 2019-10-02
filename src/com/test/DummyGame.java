package com.test;

import com.engine.core.GameLogic;
import com.engine.entities.MeshRenderer;
import com.engine.entities.Transform;
import com.engine.geometry.*;

import com.engine.entities.Camera;
import com.engine.rendering.Display;
import com.engine.rendering.Renderer;
import com.engine.rendering.shader.Shaders;
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
        Display.centerWindow();

        Mesh dragonMesh=ModelImporter.ImportModel("res/models/dragon.obj");
        dragonMesh.setColors(Color.RED);
        dragonMesh.build();
        dragon=new MeshRenderer(new Vector3f(0,0,0),
                new Vector3f(0,0,0),1f,
                dragonMesh,
                Shaders.VERTEX_COLOR_SHADER);
        //floating cubes
        Color[] colors = new Color[Geometry.Cube.VERTICES.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = Color.RED;
            i++;
            colors[i] = Color.GREEN;
            i++;
            colors[i] = Color.BLUE;
        }

        Mesh coloredMesh = new Mesh(Geometry.Cube.VERTICES,Geometry.Cube.INDEXES,colors);
        for (int i = 0; i < 1000; i++) {
            cubes.add(new MeshRenderer(
                    new Vector3f(
                            (float) (Math.random() * 100f) - 50,
                            (float) (Math.random() * 100) - 50,
                            (float) (Math.random() * 100) - 50),
                    new Vector3f(
                            (float) Math.random() * 360f,
                            (float) Math.random() * 360,
                            (float) Math.random() * 360),
                    1f,
                    coloredMesh,

                    Shaders.VERTEX_COLOR_SHADER));
        }


        Mesh terrainMesh = Geometry.getPlateData(100, 100);
        terrainMesh.setColors(Color.GREEN);
        terrainMesh.build();
        terrain = new MeshRenderer(new Vector3f(0, -0.5f, 0), Transform.ZERO, 1, terrainMesh, Shaders.VERTEX_COLOR_SHADER);
        //camera
        Camera.setMainCamera(new FirstPersonCam(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), 90, 10, 3));
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {

        for (MeshRenderer mr : cubes) {
            mr.render();
        }
        //Renderer.setWireframe(true);
        dragon.render();
        //Renderer.setWireframe(false);
        terrain.render();

    }

    @Override
    public void dispose() {

    }
}
