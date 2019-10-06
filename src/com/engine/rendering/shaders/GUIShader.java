package com.engine.rendering.shaders;

import com.engine.entity.entity2D.gui.Panel;
import com.engine.geometry.VBO;

import com.engine.util.Color;
import org.joml.Matrix4f;


public class GUIShader extends ShaderProgram {
    private static final String VERTEX_FILE="src/com/engine/rendering/shaders/GUIVertex.glsl";
    private static final String FRAMGMENT_FILE="src/com/engine/rendering/shaders/GUIFragment.glsl";
    private int panelColorLocation;
    private int transformMatrixLocation;
    public GUIShader(String vertexFile, String fragmentFile) {
        super(vertexFile, fragmentFile);
    }
    public GUIShader() {
        super(VERTEX_FILE,FRAMGMENT_FILE);
    }
    public void loadPreRenderPanelUniforms(Panel panel)
    {
        loadPanelColor(panel.getColor());
        loadTransformMatrix(panel.transform.toTranformMatrix());
    }
    @Override
    protected void bindAttributes() {
        super.bindAttribute(VBO.VERTICES_ATTRIBUTE_ID,"vertexPosition");
    }

    @Override
    protected void getAllUniformLocations() {
        panelColorLocation=getUniformLocation("panelColor");
        transformMatrixLocation=getUniformLocation("transformMatrix");
    }
    private void loadTransformMatrix(Matrix4f transformMatrix)
    {
        loadMatrixUniform(transformMatrixLocation,transformMatrix);
    }
    private void loadPanelColor(Color color)
    {
        loadVectorUniform(panelColorLocation,color.toVector4f());
    }


}
