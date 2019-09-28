package com.engine.rendering.shader;

public class VertexColorShader extends BaseShader {

    private static final String VERTEX_FILE="src/res/shaders/VertexColorVertex.glsl";
    private static final String FRAMGMENT_FILE="src/res/shaders/VertexColorFragment.glsl";

    public VertexColorShader() {
        super(VERTEX_FILE, FRAMGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttributes();
        super.bindAttribute(1,"color");
    }
}
