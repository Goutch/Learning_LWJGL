#version 400 core

in vec4 position;
//Material
uniform vec3 materialColor;
//Diffuse
uniform vec3 lightColor;
uniform float ambientLight;
in vec3 normal;
//directionnal
uniform int directionalLightsCount;
uniform vec3 directionalLightsColors[2] ;
uniform vec3 directionalLightsDirections[2];
//point
uniform int pointLightsCount;
uniform float pointLightsRadius[4];
uniform vec3 pointLightsColors[4];
uniform vec3 pointLightsPositions[4] ;
in vec3 toPointlightDirections[4];
//Specular
uniform float shineFactor; //
uniform float dampFactor; //
in vec3 toCameraDirection; //

//texture
uniform sampler2D textureSampler;
uniform int hasTexture;
in vec2 uv;

out vec4 fragColor;
void main(){
    //texture
    vec4 textureColor=texture(textureSampler, uv);
    //diffuse
    vec3 normalizedNormal=normalize(normal);
    vec3 diffuseLight=vec3(0.,0.,0.);
    vec3 specularLight=vec3(0.,0.,0.);
    //directionnalsLights
    for(int i=0;i<directionalLightsCount;i++)
    {
        vec3 normalizedLightDirection=normalize(directionalLightsDirections[i]);

        float lightValue=dot(normalizedNormal,-normalizedLightDirection);
        lightValue=max(lightValue,0.);
        diffuseLight+=directionalLightsColors[i]*lightValue;
        //specular

        if(shineFactor>0.0001)
        {
            vec3 normalizedToCameraDirection=normalize(toCameraDirection);

            vec3 reflectedLightDirection=reflect(normalizedLightDirection,normalizedNormal);

            float specularReflection=dot(reflectedLightDirection,normalizedToCameraDirection);
            specularReflection=max(specularReflection,0.);
            float dampedReflection=pow(specularReflection,dampFactor);
            specularLight+=dampedReflection*shineFactor*directionalLightsColors[i];
        }
    }
    //pointLights
    for(int i=0;i<pointLightsCount;i++)
    {
        float dist=distance(pointLightsPositions[i],position.xyz);

        if(dist<pointLightsRadius[i])
        {
            float intensity=1-dist/pointLightsRadius[i];
            vec3 normalizedToLightDirection=normalize(toPointlightDirections[i]);

            float lightValue=dot(normalizedNormal,normalizedToLightDirection);
            lightValue=max(lightValue,0.);
            diffuseLight+=pointLightsColors[i]*lightValue*intensity;
            //specular

            if(shineFactor>0.0001)
            {
                vec3 normalizedToCameraDirection=normalize(toCameraDirection);
                vec3 lightDirection=-normalizedToLightDirection;
                vec3 reflectedLightDirection=reflect(lightDirection,normalizedNormal);

                float specularReflection=dot(reflectedLightDirection,normalizedToCameraDirection);
                specularReflection=max(specularReflection,0.);
                float dampedReflection=pow(specularReflection,dampFactor);
                specularLight+=dampedReflection*shineFactor* pointLightsColors[i]*intensity;
            }
        }
    }
    vec4 light=vec4(ambientLight+diffuseLight+specularLight,1.);
    if(hasTexture==1)
    {
        fragColor=textureColor*vec4(materialColor,1.)*light;
    }
    else{
        fragColor=vec4(materialColor,1.)*light;
    }
}