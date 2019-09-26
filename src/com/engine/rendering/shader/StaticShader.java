package com.engine.rendering.shader;

import org.joml.Matrix4f;

public class StaticShader extends ShaderProgram{

    private static final String VERTEX_FILE="src/res/shaders/StaticVertex.glsl";
    private static final String FRAMGMENT_FILE="src/res/shaders/StaticFragment.glsl";

    public StaticShader() {
        super(VERTEX_FILE, FRAMGMENT_FILE);

    }
    public StaticShader(String vertexFile,String fragFile)
    {
        super(vertexFile, fragFile);
    }
    protected int transformationMatrixLocation;
    @Override
    protected void bindAttributes() {
        super.bindAttribute(0,"vertexPosition");
    }

    @Override
    protected void getAllUniformLocations() {
        transformationMatrixLocation=getUniformLocation("transformMatrix");
    }
    public void loadTransformationMatrix(Matrix4f matrix){
        loadMatrix(transformationMatrixLocation,matrix);
    }
}
