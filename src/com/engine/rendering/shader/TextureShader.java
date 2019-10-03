package com.engine.rendering.shader;

/**
 * Inputs:
 * vertexPositions,
 * vertexNormals,
 * textureCoord(uv).
 *
 * Uniforms:
 * transformMatrix,
 * projectionMatrix,
 * viewMatrix,
 * lightPosition,
 * lightColor.
 */
public class TextureShader extends DiffuseLightShader {

    private static final String VERTEX_FILE="src/res/shaders/TextureVertex.glsl";
    private static final String FRAMGMENT_FILE="src/res/shaders/TextureFragment.glsl";

    public TextureShader() {
        super(VERTEX_FILE, FRAMGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttributes();
        bindAttribute(ShaderProgram.UVS_ATTRIBUTE_ID,"textureCoord");
    }
}
