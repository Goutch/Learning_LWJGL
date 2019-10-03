package com.engine.rendering.shader;


/**
 * Inputs:
 * vertexPositions,
 * vertexNormal,
 * vertexColor.
 *
 * Uniforms:
 * transformMatrix,
 * projectionMatrix,
 * viewMatrix,
 * lightPosition,
 * lightColor.
 */
public class VertexColorShader extends DiffuseLightShader {

    private static final String VERTEX_FILE="src/res/shaders/VertexColorVertex.glsl";
    private static final String FRAMGMENT_FILE="src/res/shaders/VertexColorFragment.glsl";

    public VertexColorShader() {
        super(VERTEX_FILE, FRAMGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttributes();
        super.bindAttribute(ShaderProgram.COLORS_ATTRIBUTE_ID,"vertexColor");
    }
}
