package com.engine.rendering.shaders;


import com.engine.geometry.VBO;

/**
 * Inputs:
 * vertexPositions,
 * vertexNormals,
 * vertexColor.
 *
 * Unifroms:
 * transformMatrix,
 * projectionMatrix,
 * viewMatrix,
 * lightPosition,
 * lightColor.
 * dampFactor,
 * shineFactor,
 * materialColor,
 *
 */
public class VertexColorShader extends DiffuseLightShader {

    private static final String VERTEX_FILE="src/com/engine/rendering/shaders/VertexColorVertex.glsl";
    private static final String FRAMGMENT_FILE="src/com/engine/rendering/shaders/VertexColorFragment.glsl";

    public VertexColorShader() {
        super(VERTEX_FILE, FRAMGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttributes();
        super.bindAttribute(VBO.COLORS_ATTRIBUTE_ID,"vertexColor");
    }
}
