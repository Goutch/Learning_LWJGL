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
        int terrainSize=4;
        float[] vertices=new float[(terrainSize+1)*(terrainSize+1)*3];
        int[] indices=new int[(terrainSize+1)*(terrainSize+1)*6];

        for (int i = 0; i < terrainSize+1; i++) {
            for (int j = 0; j < terrainSize+1; j++) {
                int index=(i*(terrainSize+1))+j;
                    vertices[index * 3] = j-(float)terrainSize/2;
                    vertices[index * 3 + 1] = 0;
                    vertices[index * 3 + 2] = i-(float)terrainSize/2;
            }
        }

        for (int i = 0; i < terrainSize; i++) {
            for (int j = 0; j < terrainSize+1; j++) {
                int index=(i*(terrainSize))+j;
                    if(j!=terrainSize)
                    {
                        indices[index*6]=index+i;
                        indices[index*6+1]=index+i+1;
                        indices[index*6+2]=index+i+terrainSize+1;
                        indices[index*6+3]=index+i+terrainSize+1;
                        indices[index*6+4]=index+i+1;
                        indices[index*6+5]=index+i+terrainSize+2;
                    }
            }
        }

        ColoredMesh terrainMesh=new ColoredMesh(vertices,indices,Color.GREEN);
        terrain=new ColoredMeshRenderer(new Vector3f(0,-0.5f,0),Transform.ZERO,1,terrainMesh,Shaders.VERTEX_COLOR_SHADER);
        //camera
        Camera.setMainCamera(new GodCam(new Vector3f(0, 0, 0), new Vector3f(0, 45, 0), 90, 10, 3));
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        Renderer.setWireframe(true);
       // for (MeshRenderer mr:cubes) {
       //     mr.render();
       // }

        terrain.render();
        Renderer.setWireframe(false);
    }

    @Override
    public void dispose() {

    }
}
