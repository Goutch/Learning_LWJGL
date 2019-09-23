package com.engine.rendering.shader;


public class StaticShader extends ShaderProgram{

    private static final String VERTEX_FILE="src/com/engine/rendering/shader/vertexShader.glsl";
    private static final String FRAMGMENT_FILE="src/com/engine/rendering/shader/fragmentShader.glsl";

    public StaticShader() {
        super(VERTEX_FILE, FRAMGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {

    }
}
