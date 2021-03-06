package com.engine.rendering.shaders.gui;

import com.engine.entity.gui.Panel;

import com.engine.geometry.VBO;

import com.engine.materials.GUIMaterial;

import com.engine.rendering.shaders.ShaderProgram;

import org.joml.Matrix4f;


public class GUIShader extends ShaderProgram {
    private static final String VERTEX_FILE="src/com/engine/rendering/shaders/gui/GUIVertex.glsl";
    private static final String FRAMGMENT_FILE="src/com/engine/rendering/shaders/gui/GUIFragment.glsl";
    private int panelColorLocation;
    private int panelBorderColorLocation;
    private int panelBorderWidthLocation;
    private int rectSizeLocation;
    private int transformMatrixLocation;
    private int aspectRatioLocation;
    private int hasTextureLocation;
    private int pivotOffsetLocation;
    public GUIShader(String vertexFile, String fragmentFile) {
        super(vertexFile, fragmentFile);
    }
    public GUIShader() {
        super(VERTEX_FILE,FRAMGMENT_FILE);
    }
    public void loadPreRenderGeneralUniforms()
    {

    }

    public void loadPreRenderMaterialUniforms(GUIMaterial material)
    {
        loadMaterialProperties(material);
    }
    public void loadPreRenderPanelUniforms(Panel panel)
    {
        loadTransformMatrix(panel.transform.getTransformMatrix());
        loadVectorUniform(rectSizeLocation,panel.getSize());
        loadFloatUniform(aspectRatioLocation,panel.getAspectRatio());
        loadVectorUniform(pivotOffsetLocation,panel.getPivotOffSet());
    }
    @Override
    protected void bindAttributes() {
        bindAttribute(VBO.UVS_ATTRIBUTE_ID,"textureCoord");
        bindAttribute(VBO.VERTICES_ATTRIBUTE_ID,"vertexPosition");
    }

    @Override
    protected void getAllUniformLocations() {
        panelColorLocation=getUniformLocation("color");
        transformMatrixLocation=getUniformLocation("transformMatrix");
        panelBorderColorLocation=getUniformLocation("borderColor");
        panelBorderWidthLocation=getUniformLocation("borderWidth");
        aspectRatioLocation=getUniformLocation("aspectRatio");
        hasTextureLocation=getUniformLocation("hasTexture");
        rectSizeLocation=getUniformLocation("size");
        pivotOffsetLocation=getUniformLocation("pivot");
    }
    private void loadTransformMatrix(Matrix4f transformMatrix)
    {
        loadMatrixUniform(transformMatrixLocation,transformMatrix);
    }
    private void loadMaterialProperties(GUIMaterial material)
    {
        loadFloatUniform(panelBorderWidthLocation,material.borderWidth());
        loadVectorUniform(panelBorderColorLocation,material.borderColor().toVector4f());
        loadVectorUniform(panelColorLocation,material.color().toVector4f());
        loadBooleanUniform(hasTextureLocation,material.hasTexture());
    }


}
