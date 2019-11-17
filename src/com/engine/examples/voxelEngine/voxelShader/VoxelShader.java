package com.engine.examples.voxelEngine.voxelShader;


import com.engine.geometry.VBO;
import com.engine.materials.Material;
import com.engine.rendering.shaders.BaseShader;

import javax.print.DocFlavor;

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
public class VoxelShader extends BaseShader {
    public static final int AMBIENT_OCCLUSION_ID =4;
    public static final int AMBIENT_UCCLUSION_SIZE =3;
    private static final String VERTEX_FILE="src/com/engine/examples/voxelEngine/voxelShader/VoxelVertex.glsl";
    private static final String FRAMGMENT_FILE="src/com/engine/examples/voxelEngine/voxelShader/VoxelFragment.glsl";

    public VoxelShader() {
        super(VERTEX_FILE, FRAMGMENT_FILE);
    }
    private int hasTextureLocation;

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

    @Override
    protected void bindAttributes() {
        super.bindAttributes();
        super.bindAttribute(VBO.COLORS_ATTRIBUTE_ID,"vertexColor");
        super.bindAttribute(VBO.UVS_ATTRIBUTE_ID,"textureCoords");
        super.bindAttribute(VBO.NORMALS_ATTRIBUTE_ID,"vertexNormal");
        super.bindAttribute(AMBIENT_OCCLUSION_ID,"ambientOcclusion");
    }
}
