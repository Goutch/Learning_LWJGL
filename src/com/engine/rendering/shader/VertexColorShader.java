package com.engine.rendering.shader;

public class VertexColorShader extends StaticShader {

    private static final String VERTEX_FILE="src/res/shaders/VertexColorVertex.glsl";
    private static final String FRAMGMENT_FILE="src/res/shaders/VertexColorFragment.glsl";

    public VertexColorShader() {
        super(VERTEX_FILE, FRAMGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0,"vertexPosition");
        super.bindAttribute(1,"color");
    }
}
