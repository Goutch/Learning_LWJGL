package com.engine.rendering.shader;

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
 */
public class DiffuseLightShader extends BaseShader {
    private static final String VERTEX_FILE="src/res/shaders/DiffuseLightVertex.glsl";
    private static final String FRAMGMENT_FILE="src/res/shaders/DiffuseLightFragment.glsl";
    private int lightPositionLocation;
    private int lightColorLocation;
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
        loadLight();
    }
    @Override
    protected void getAllUniformLocations() {
        super.getAllUniformLocations();
        lightPositionLocation=getUniformLocation("lightPosition");
        lightColorLocation=getUniformLocation("lightColor");
    }

    private void loadLight(){
        if(Light.main.transform.position!=null)
            loadVectorUniform(lightColorLocation,new Vector3f(Light.main.color.r,Light.main.color.g,Light.main.color.b));
            loadVectorUniform(lightPositionLocation, Light.main.transform.position);
    }
}
