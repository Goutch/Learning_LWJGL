package com.engine.rendering.shaders.projection;

import com.engine.core.GameOptions;
import com.engine.entity.Entity;
import com.engine.entity.light.DirectionalLight;
import com.engine.entity.light.PointLight;
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
    private static final int MAX_POINT_LIGHTS=4;
    private static final int MAX_DIRECTIONAL_LIGHTS=2;
    private int pointLightsCountLocation;
    private int[] pointLightsPositionLocations;
    private int[] pointLightsColorsLocations;
    private int[] pointLightsRadiusLocations;
    private int directionalLightCountLocation;
    private int[] directionalLightsDirectionsLocations;
    private int[] directionalLightsColorsLocations;
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
        loadDirectionalsLight();
        loadAmbientLight();
    }

    @Override
    public void loadPreRenderEntityUniforms(Entity entity) {
        super.loadPreRenderEntityUniforms(entity);
        loadPointsLights(entity);
    }

    @Override
    protected void getAllUniformLocations() {
        super.getAllUniformLocations();
        pointLightsPositionLocations=new int[MAX_POINT_LIGHTS];
        pointLightsRadiusLocations=new int[MAX_POINT_LIGHTS];
        pointLightsColorsLocations=new int[MAX_POINT_LIGHTS];
        pointLightsCountLocation=getUniformLocation("pointLightsCount");
        for (int i = 0; i < MAX_POINT_LIGHTS; i++) {
            pointLightsPositionLocations[i]=getUniformLocation("pointLightsPositions["+i+"]");
            pointLightsColorsLocations[i]=getUniformLocation("pointLightsColors["+i+"]");
            pointLightsRadiusLocations[i]=getUniformLocation("pointLightsRadius["+i+"]");
        }

        directionalLightsColorsLocations=new int[MAX_DIRECTIONAL_LIGHTS];
        directionalLightsDirectionsLocations=new int[MAX_DIRECTIONAL_LIGHTS];
        directionalLightCountLocation=getUniformLocation("directionalLightsCount");
        for (int i = 0; i < MAX_DIRECTIONAL_LIGHTS; i++) {
            directionalLightsDirectionsLocations[i]=getUniformLocation("directionalLightsDirections["+i+"]");
            directionalLightsColorsLocations[i]=getUniformLocation("directionalLightsColors["+i+"]");
        }

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
    private void loadPointsLights(Entity entity){
        //todo: load closest lights
        Vector3f pos=entity.transform.getGlobalPosition();
        int pointCount=0;
        for(PointLight l:PointLight.Lights)
        {
            if(pointCount>MAX_POINT_LIGHTS)
            {
                break;
            }
            loadVectorUniform(pointLightsPositionLocations[pointCount],l.transform.getGlobalPosition());
            loadVectorUniform(pointLightsColorsLocations[pointCount],l.getColor().toVector3f());
            loadFloatUniform(pointLightsRadiusLocations[pointCount],l.gerRadius());

            pointCount++;
        }
        loadIntUniform(pointLightsCountLocation,pointCount);
    }
    private void loadDirectionalsLight(){

        int directionalCount=0;
        for(DirectionalLight l:DirectionalLight.Lights)
        {
            if(directionalCount>MAX_DIRECTIONAL_LIGHTS)
            {
                break;
            }

            loadVectorUniform(directionalLightsDirectionsLocations[directionalCount],l.transform.forward());
            loadVectorUniform(directionalLightsColorsLocations[directionalCount],l.getColor().toVector3f());
            directionalCount++;
        }
        loadIntUniform(directionalLightCountLocation,directionalCount);
    }
    private void loadAmbientLight()
    {
        loadFloatUniform(ambientLightLocation, GameOptions.AMBIENT_LIGHT);
    }

}
