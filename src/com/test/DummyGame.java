package com.test;

import com.engine.core.GameLogic;
import com.engine.geometry.Geometry;
import com.engine.geometry.Mesh;

import com.engine.rendering.Camera;
import com.engine.rendering.shader.Shaders;
import com.engine.util.Texture;
import org.joml.Vector3f;


public class DummyGame implements GameLogic {
    Mesh[] sprites = new Mesh[4];
    Vector3f[] positions = new Vector3f[]
            {
                    new Vector3f(0, 0, -2),
                    new Vector3f(0, 0, 2),
                    new Vector3f(2, 0, 0),
                    new Vector3f(-2, 0, 0),
            };

    @Override
    public void init() {

        for (int i = 0; i < 4; i++) {
            sprites[i] = new Mesh(positions[i],
                    new Vector3f(0, 0, 0),
                    1f,
                    Geometry.Cube.VERTICES,
                    Geometry.Cube.INDEXES,
                    Geometry.Cube.UVS,
                    new Texture("res/textures/Untitled.png"),
                    Shaders.TEXTURE_SHADER);
        }


        Camera.setMainCamera(new GodCam(new Vector3f(0, 0, 0), new Vector3f(0, 45, 0), 90, 1000, 3));
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        for (int i = 0; i < 4; i++) {
            sprites[i].render();
        }
    }

    @Override
    public void dispose() {

    }
}
