package com.test;

import com.engine.core.GameLogic;
import com.engine.entities.ColoredMeshRenderer;
import com.engine.entities.MeshRenderer;
import com.engine.entities.TexturedMeshRenderer;
import com.engine.entities.Transform;
import com.engine.geometry.*;

import com.engine.rendering.Camera;
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

    @Override
    public void init() {
        //textured cubes
        TexturedMesh texturedMesh=new TexturedMesh(Geometry.Cube.VERTICES,Geometry.Cube.INDEXES,Geometry.Cube.UVS);
        Texture tex=new Texture("res/textures/Untitled.png");
        Vector3f[] positions = new Vector3f[]
                {
                        new Vector3f(0, 0, -2),
                        new Vector3f(0, 0, 2),
                        new Vector3f(2, 0, 0),
                        new Vector3f(-2, 0, 0),
                };
        for (int i = 0; i < 4; i++) {
            cubes.add( new TexturedMeshRenderer(positions[i],
                    new Vector3f(0, 0, 0),
                    1f,
                    texturedMesh,
                    tex,
                    Shaders.TEXTURE_SHADER));
        }

        //floating cubes
        Color[] colors=new Color[Geometry.Cube.VERTICES.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i]=Color.RED;
            i++;
            colors[i]=Color.GREEN;
            i++;
            colors[i]=Color.BLUE;
        }

        ColoredMesh coloredMesh=new ColoredMesh(Geometry.Cube.VERTICES,Geometry.Cube.INDEXES,colors);
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

        //terrain
        int terrainSize=10;
        float[] vertices=new float[(terrainSize+1)*(terrainSize+1)*3];
        int[] indices=new int[(terrainSize+1)*(terrainSize+1)*6];
        int rowCounter=0;
        for (int i = 0; i < vertices.length/3; i++) {
            if(i%terrainSize+1==terrainSize)
            {
                rowCounter++;
            }
            else
            {
                vertices[i*3]=i%terrainSize -((float)terrainSize/2)+1.5f;
                vertices[i*3+1]=0;
                vertices[i*3+2]=rowCounter-((float)terrainSize/2)+1.5f;
            }
        }

        for (int i = 0; i < indices.length/6; i++) {
            indices[i*6]=i;
            indices[i*6+1]=i+1;
            indices[i*6+2]=i+terrainSize;
            indices[i*6+3]=i+terrainSize;
            indices[i*6+4]=i+1;
            indices[i*6+5]=i+terrainSize+1;

        }

        ColoredMesh terrainMesh=new ColoredMesh(vertices,indices,Color.GREEN);
        terrain=new ColoredMeshRenderer(new Vector3f(0,-0.5f,0),Transform.ZERO,1,terrainMesh,Shaders.VERTEX_COLOR_SHADER);
        //camera
        Camera.setMainCamera(new GodCam(new Vector3f(0, 0, 0), new Vector3f(0, 45, 0), 90, 1000, 3));
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        Renderer.setWireframe(true);
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
