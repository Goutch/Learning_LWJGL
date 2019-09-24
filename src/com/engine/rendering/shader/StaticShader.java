package com.engine.rendering.shader;


public class StaticShader extends ShaderProgram{

    private static final String VERTEX_FILE="src/res/shaders/staticVertex.glsl";
    private static final String FRAMGMENT_FILE="src/res/shaders/staticFragment.glsl";

    public StaticShader() {
        super(VERTEX_FILE, FRAMGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {

    }
}
