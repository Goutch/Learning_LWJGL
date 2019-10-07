#version 400 core

//Material
uniform vec3 materialColor;
//Diffuse
uniform vec3 lightColor;
uniform float ambientLight;
in vec3 normal;
in vec3 toLightDirection;

//Specular
uniform float shineFactor; //
uniform float dampFactor; //
in vec3 toCameraDirection; //
//vertexColor
in vec4 color;


out vec4 fragColor;
void main()
{
    //diffuse
    vec3 normalizedNormal=normalize(normal);
    vec3 normalizedToLightDirection=normalize(toLightDirection);

    float lightValue=dot(normalizedNormal,normalizedToLightDirection);
    lightValue=max(lightValue,0.);
    vec3 diffuseLight=lightColor*lightValue;
    //specular
    vec3 specularLight=vec3(0.,0.,0.);
    if(shineFactor>0.0001)
    {
        vec3 normalizedToCameraDirection=normalize(toCameraDirection);
        vec3 lightDirection=-normalizedToLightDirection;
        vec3 reflectedLightDirection=reflect(lightDirection,normalizedNormal);

        float specularReflection=dot(reflectedLightDirection,normalizedToCameraDirection);
        specularReflection=max(specularReflection,0.);
        float dampedReflection=pow(specularReflection,dampFactor);
        specularLight=dampedReflection*shineFactor*lightColor;
    }
    vec4 light=vec4(ambientLight+diffuseLight+specularLight,1.);

    fragColor=color*vec4(materialColor,1.)*light;
}