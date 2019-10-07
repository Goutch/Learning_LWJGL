package com.engine.rendering.shaders.projection;

import com.engine.geometry.Material;
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

    private static final String VERTEX_FILE="src/com/engine/rendering/shaders/projection/TextureVertex.glsl";
    private static final String FRAMGMENT_FILE="src/com/engine/rendering/shaders/projection/TextureFragment.glsl";
    private int hasTextureLocation;
    public TextureShader() {
        super(VERTEX_FILE, FRAMGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttributes();
        bindAttribute(VBO.UVS_ATTRIBUTE_ID,"textureCoord");
    }

    @Override
    public void loadPreRenderMaterialUniforms(Material material) {
        super.loadPreRenderMaterialUniforms(material);
        loadBooleanUniform(hasTextureLocation,material.hasTexture());
    }

    @Override
    protected void getAllUniformLocations() {
        super.getAllUniformLocations();
        hasTextureLocation=getUniformLocation("hasTexture");
    }
}
