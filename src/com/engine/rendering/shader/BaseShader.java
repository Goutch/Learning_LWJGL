package com.engine.rendering.shader;

import com.engine.entities.Entity;
import com.engine.events.EventManager;
import com.engine.events.ProjectionMatrixChangeListener;
import com.engine.rendering.Camera;
import com.engine.rendering.Renderer;
import org.joml.Matrix4f;

public class BaseShader extends ShaderProgram implements ProjectionMatrixChangeListener {

    private static final String VERTEX_FILE="src/res/shaders/BaseVertex.glsl";
    private static final String FRAMGMENT_FILE="src/res/shaders/BaseFragment.glsl";
    private int transformMatrixLocation;
    private int projectionMatrixLocation;
    private int viewMatrixLocation;

    public BaseShader() {
        super(VERTEX_FILE, FRAMGMENT_FILE);
        if(Camera.main!=null)
        {
            start();
            loadProjectionMatrix(Camera.main.getProjectionMatrix());
            stop();
        }
        EventManager.subscribeProjectionMatrixChange(this);
    }
    public BaseShader(String vertexFile, String fragFile)
    {
        super(vertexFile, fragFile);
        if(Camera.main!=null)
        {
            start();
            loadProjectionMatrix(Camera.main.getProjectionMatrix());
            stop();
        }
        EventManager.subscribeProjectionMatrixChange(this);
    }
    @Override
    public void onProjectionMatrixChanged(Matrix4f projectionMatrix) {
        start();
        loadProjectionMatrix(projectionMatrix);
        stop();
    }

    /**
     * Called when the engines has finished to initialize to ensure the main camera exist
     */
    @Override
    protected void getAllUniformLocations() {
        transformMatrixLocation =getUniformLocation("transformMatrix");
        projectionMatrixLocation=getUniformLocation("projectionMatrix");
        viewMatrixLocation=getUniformLocation("viewMatrix");
    }

    @Override
    public void loadUniforms(Entity entity)
    {
        loadTransformMatrix(entity.transform.toTranformMatrix());
        loadViewMatrix(Camera.main.getViewMatrix());
    }
    @Override
    protected void bindAttributes() {
        super.bindAttribute(0,"vertexPosition");
    }


    private void loadTransformMatrix(Matrix4f transformMatrix)
    {
        loadMatrix(transformMatrixLocation,transformMatrix);
    }
    private void loadProjectionMatrix(Matrix4f projectionMatrix)
    {
        loadMatrix(projectionMatrixLocation,projectionMatrix);
    }
    private void loadViewMatrix(Matrix4f viewMatrix)
    {
        loadMatrix(viewMatrixLocation,viewMatrix);
    }


}
