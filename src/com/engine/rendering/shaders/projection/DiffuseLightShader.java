package com.engine.rendering.shaders.projection;

import com.engine.core.GameOptions;
import com.engine.entity.light.DirectionalLight;
import com.engine.geometry.VBO;
import com.engine.materials.Material;
import com.engine.rendering.shaders.BaseShader;
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
 * shineFactor,
 * materialColor,
 *
 */
public class DiffuseLightShader extends BaseShader {
    private static final String VERTEX_FILE="src/com/engine/rendering/shaders/projection/DiffuseLightVertex.glsl";
    private static final String FRAMGMENT_FILE="src/com/engine/rendering/shaders/projection/DiffuseLightFragment.glsl";
    private int lightPositionLocation;
    private int lightColorLocation;
    private int ambientLightLocation;
    private int shineFactorLocation;
    private int dampFactorLocation;
    private int hasTextureLocation;

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
        super.bindAttribute(VBO.NORMALS_ATTRIBUTE_ID,"vertexNormal");
    }
    @Override
    public void loadPreRenderGeneralUniforms()
    {
        super.loadPreRenderGeneralUniforms();
        loadLight();

    }
    @Override
    protected void getAllUniformLocations() {
        super.getAllUniformLocations();
        lightPositionLocation=getUniformLocation("lightPosition");
        lightColorLocation=getUniformLocation("lightColor");
        ambientLightLocation=getUniformLocation("ambientLight");
        shineFactorLocation=getUniformLocation("shineFactor");
        dampFactorLocation=getUniformLocation("dampFactor");
        hasTextureLocation=getUniformLocation("hasTexture");

    }

    protected void loadMaterial(Material material) {
        super.loadMaterial(material);
        loadFloatUniform(shineFactorLocation, material.getShineFactor());
        loadFloatUniform(dampFactorLocation, material.getDampFactor());
        loadBooleanUniform(hasTextureLocation, material.hasTexture());
    }

    private void loadLight(){
        if(DirectionalLight.main!=null)
            loadVectorUniform(lightColorLocation,new Vector3f(DirectionalLight.main.getColor().r, DirectionalLight.main.getColor().g, DirectionalLight.main.getColor().b));
            loadVectorUniform(lightPositionLocation, DirectionalLight.main.transform.getGlobalPosition());
            loadFloatUniform(ambientLightLocation, GameOptions.AMBIENT_LIGHT);
    }

}
