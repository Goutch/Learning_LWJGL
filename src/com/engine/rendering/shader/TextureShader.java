package com.engine.rendering.shader;


public class TextureShader extends ShaderProgram{

    private static final String VERTEX_FILE="src/res/shaders/TextureVertex.glsl";
    private static final String FRAMGMENT_FILE="src/res/shaders/TextureFragment.glsl";

    public TextureShader() {
        super(VERTEX_FILE, FRAMGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0,"vertexPosition");
        super.bindAttribute(1,"textureCoord");
    }
}
