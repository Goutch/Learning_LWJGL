package com.test;

import com.engine.core.GameLogic;
import com.engine.entities.ColoredMeshRenderer;
import com.engine.entities.MeshRenderer;
import com.engine.entities.TexturedMeshRenderer;
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
    TexturedMeshRenderer model;
    MeshRenderer terrain;

    @Override
    public void init() {
        Display.centerWindow();
       // TexturedMesh modelMesh=ModelImporter.ImportModel("res/models/");
       // Texture modelTex=new Texture("res/models/");
       // model=new TexturedMeshRenderer(new Vector3f(0,0,-3),Transform.ZERO,1,modelMesh,modelTex,Shaders.TEXTURE_SHADER);
        //floating cubes
        Color[] colors=new Color[Geometry.Cube.VERTICES.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i]=Color.RED;
            i++;
            colors[i]=Color.GREEN;
            i++;
            colors[i]=Color.BLUE;
        }

        ColoredMesh coloredMesh=new ColoredMesh(Geometry.Cube.VERTICES,Geometry.Cube.INDEXES,new float[3],colors);
        for (int i = 0; i < 1000; i++) {
            cubes.add(new ColoredMeshRenderer(
                    new Vector3f(
                            (float) (Math.random()*100f)-50,
                            (float) (Math.random()*100)-50,
                            (float) (Math.random()*100)-50),
                    new Vector3f(
                            (float)Math.random()*360f,
                            (float)Math.random()*360,
                            (float)Math.random()*360),
                    1f,
                    coloredMesh,
                    Shaders.VERTEX_COLOR_SHADER));
        }


        Mesh plane=new Plane(100,100);
        ColoredMesh terrainMesh=new ColoredMesh(plane,new Color(0.2f,0.7f,0.2f,0.5f));
        plane.dispose();
        terrain=new ColoredMeshRenderer(new Vector3f(0,-0.5f,0),Transform.ZERO,1,terrainMesh,Shaders.VERTEX_COLOR_SHADER);
        //camera
        Camera.setMainCamera(new FirstPersonCam(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), 90, 10, 3));
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        //Renderer.setWireframe(true);
        for (MeshRenderer mr:cubes) {
            mr.render();
        }

        terrain.render();
        Renderer.setWireframe(false);
    }

    @Override
    public void dispose() {

    }
}
