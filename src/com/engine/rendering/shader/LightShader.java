package com.engine.rendering.shader;

import com.engine.entities.Entity;
import com.engine.entities.Light;
import org.joml.Vector3f;

public class LightShader extends BaseShader {
    private static final String VERTEX_FILE="src/res/shaders/LightVertex.glsl";
    private static final String FRAMGMENT_FILE="src/res/shaders/LightFragment.glsl";
    protected int lightPositionLocation;
    protected int lightColorLocation;
    public LightShader() {
        super(VERTEX_FILE, FRAMGMENT_FILE);
    }
    @Override
    protected void bindAttributes() {
        super.bindAttributes();
        super.bindAttribute(NORMALS_ATTRIBUTE_ID,"vertexNormal");
    }
    @Override
    public void loadUniformsBeforeRender(Entity entity)
    {
        super.loadUniformsBeforeRender(entity);
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
