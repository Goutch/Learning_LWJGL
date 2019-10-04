package com.engine.rendering.shaders;

import com.engine.geometry.VBO;

/**
 * Inputs:
 * vertexPositions,
 * vertexNormals.
 * textureCoord(uvs).
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
public class TextureShader extends DiffuseLightShader {

    private static final String VERTEX_FILE="src/com/engine/rendering/shaders/TextureVertex.glsl";
    private static final String FRAMGMENT_FILE="src/com/engine/rendering/shaders/TextureFragment.glsl";

    public TextureShader() {
        super(VERTEX_FILE, FRAMGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttributes();
        bindAttribute(VBO.UVS_ATTRIBUTE_ID,"textureCoord");
    }
}
