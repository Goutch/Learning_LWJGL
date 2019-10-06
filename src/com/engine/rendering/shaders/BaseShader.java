package com.engine.rendering.shaders;

import com.engine.entity.entity3D.Entity3D;
import com.engine.events.EventManager;
import com.engine.events.ProjectionMatrixChangeListener;
import com.engine.entity.entity3D.Camera;
import com.engine.geometry.VBO;
import com.engine.geometry.Material;
import org.joml.Matrix4f;

/**
 * Inputs:
 * vertexPositions
 *
 * Unifroms:
 * transformMatrix
 * projectionMatrix
 * viewMatrix
 */
public class BaseShader extends ShaderProgram implements ProjectionMatrixChangeListener {

    private static final String VERTEX_FILE="src/com/engine/rendering/shaders/BaseVertex.glsl";
    private static final String FRAMGMENT_FILE="src/com/engine/rendering/shaders/BaseFragment.glsl";
    private int transformMatrixLocation;
    private int projectionMatrixLocation;
    private int viewMatrixLocation;

    private int shineFactorLocation;
    private int dampFactorLocation;
    private int materialColorLocation;
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

    }
    @Override
    protected void initUniforms()
    {
        if(Camera.main!=null)
        {
            onProjectionMatrixChanged(Camera.main.getProjectionMatrix());
        }
        EventManager.subscribeProjectionMatrixChange(this);
    }


    /**
     * Called when the engines has finished to initialize to ensure the main camera exist
     */
    @Override
    protected void getAllUniformLocations() {
        transformMatrixLocation =getUniformLocation("transformMatrix");
        projectionMatrixLocation=getUniformLocation("projectionMatrix");
        viewMatrixLocation=getUniformLocation("viewMatrix");

        shineFactorLocation=getUniformLocation("shineFactor");
        dampFactorLocation=getUniformLocation("dampFactor");
        materialColorLocation=getUniformLocation("materialColor");
    }

    public void loadPreRenderGeneralUniforms(){

        loadViewMatrix(Camera.main.getViewMatrix());
    }

    public void loadPreRenderMaterialUniforms(Material material)
    {
        loadMaterial(material);
    }
    public void loadPreRenderEntityUniforms(Entity3D entity)
    {
        loadTransformMatrix(entity.transform.toTranformMatrix());
    }
    protected void bindAttributes() {
        super.bindAttribute(VBO.VERTICES_ATTRIBUTE_ID,"vertexPosition");
    }
    private void loadTransformMatrix(Matrix4f transformMatrix)
    {
        loadMatrixUniform(transformMatrixLocation,transformMatrix);
    }
    private void loadProjectionMatrix(Matrix4f projectionMatrix)
    {
        loadMatrixUniform(projectionMatrixLocation,projectionMatrix);
    }
    private void loadViewMatrix(Matrix4f viewMatrix)
    {
        loadMatrixUniform(viewMatrixLocation,viewMatrix);
    }
    private void loadMaterial(Material material)
    {
        loadFloatUniform(shineFactorLocation,material.getShineFactor());
        loadFloatUniform(dampFactorLocation,material.getDampFactor());
        loadVectorUniform(materialColorLocation,(material.getColor().toVector3f()));
    }
    @Override
    public void onProjectionMatrixChanged(Matrix4f projectionMatrix) {
        start();
        loadProjectionMatrix(projectionMatrix);
        stop();
    }
}
