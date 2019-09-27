package com.engine.rendering.shader;

import com.engine.entities.Entity;
import org.joml.Matrix4f;

public class BaseShader extends ShaderProgram{

    private static final String VERTEX_FILE="src/res/shaders/BaseVertex.glsl";
    private static final String FRAMGMENT_FILE="src/res/shaders/BaseFragment.glsl";
    private int transformationMatrixLocation;
    public BaseShader() {
        super(VERTEX_FILE, FRAMGMENT_FILE);
    }
    public BaseShader(String vertexFile, String fragFile)
    {
        super(vertexFile, fragFile);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0,"vertexPosition");
    }

    @Override
    protected void getAllUniformLocations() {
        transformationMatrixLocation=getUniformLocation("transformMatrix");
    }
    @Override
    public void start(Entity entity)
    {
        super.start(entity);
        Matrix4f transformMatrix= entity.transform.toTranformMatrix();
        loadTransformationMatrix(transformMatrix);
    }
    private void loadTransformationMatrix(Matrix4f matrix){
        loadMatrix(transformationMatrixLocation,matrix);
    }
}
