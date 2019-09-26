package com.engine.rendering.shader;


import org.joml.Matrix4f;

public class TextureShader extends StaticShader{

    private static final String VERTEX_FILE="src/res/shaders/TextureVertex.glsl";
    private static final String FRAMGMENT_FILE="src/res/shaders/TextureFragment.glsl";

    public TextureShader() {
        super(VERTEX_FILE, FRAMGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        bindAttribute(0,"vertexPosition");
        bindAttribute(1,"textureCoord");
    }


}
