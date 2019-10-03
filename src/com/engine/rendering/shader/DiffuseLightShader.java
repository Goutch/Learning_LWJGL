package com.engine.rendering.shader;

import com.engine.core.GameOptions;
import com.engine.entities.Entity;
import com.engine.entities.Light;
import com.engine.rendering.Material;
import org.joml.Vector3f;
/**
 * Inputs:
 * vertexPositions,
 * vertexNormals.
 *
 * Unifroms:
 * transformMatrix,
 * projectionMatrix,
 * viewMatrix,
 * lightPosition,
 * lightColor.
 * dampFactor,
 * shineFactor.
 *
 */
public class DiffuseLightShader extends BaseShader {
    private static final String VERTEX_FILE="src/res/shaders/DiffuseLightVertex.glsl";
    private static final String FRAMGMENT_FILE="src/res/shaders/DiffuseLightFragment.glsl";
    private int lightPositionLocation;
    private int lightColorLocation;
    private int shineFactorLocation;
    private int dampFactorLocation;
    private int ambientLightLocation;
    public DiffuseLightShader(String vertex,String frag)
    {
        super(vertex,frag);
    }
    public DiffuseLightShader() {
        super(VERTEX_FILE, FRAMGMENT_FILE);
    }
    @Override
    protected void bindAttributes() {
        super.bindAttributes();
        super.bindAttribute(NORMALS_ATTRIBUTE_ID,"vertexNormal");
    }
    @Override
    public void loadUniformsBeforeRender(Entity entity,Material material)
    {
        super.loadUniformsBeforeRender(entity,material);
        loadLight(material);
    }
    @Override
    protected void getAllUniformLocations() {
        super.getAllUniformLocations();
        lightPositionLocation=getUniformLocation("lightPosition");
        lightColorLocation=getUniformLocation("lightColor");
        dampFactorLocation=getUniformLocation("dampFactor");
        shineFactorLocation=getUniformLocation("shineFactor");
        ambientLightLocation=getUniformLocation("ambientLight");
    }

    private void loadLight(Material material){
        if(Light.main.transform.position!=null)
            loadVectorUniform(lightColorLocation,new Vector3f(Light.main.color.r,Light.main.color.g,Light.main.color.b));
            loadVectorUniform(lightPositionLocation, Light.main.transform.position);
            loadFloatUniform(shineFactorLocation,material.getShineFactor());
            loadFloatUniform(dampFactorLocation,material.getDampFactor());
            loadFloatUniform(ambientLightLocation, GameOptions.AMBIENT_LIGHT);
    }
}
