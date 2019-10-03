#version 400 core

//Diffuse
uniform vec3 lightColor;
uniform float ambientLight;
in vec3 normal;
in vec3 toLightDirection;

//Specular
uniform float shineFactor; //
uniform float dampFactor; //
in vec3 toCameraDirection; //

//texture
uniform sampler2D textureSampler;
in vec2 uv;


out vec4 fragColor;
void main(){
    //texture
    vec4 textureColor=texture(textureSampler, uv);
    //light
    vec3 normalizedNormal=normalize(normal);
    vec3 normalizedToLightDirection=normalize(toLightDirection);

    float lightValue=dot(normalizedNormal,normalizedToLightDirection);
    lightValue=max(lightValue,ambientLight);
    vec3 light=lightColor*lightValue;
    //specular
    if(shineFactor>0.0001f)
    {
        vec3 normalizedToCameraDirection=normalize(toCameraDirection);
        vec3 lightDirection=-normalizedToLightDirection;
        vec3 reflectedLightDirection=reflect(lightDirection,normalizedNormal);

        float specularReflection=dot(reflectedLightDirection,normalizedToCameraDirection);
        specularReflection=max(specularReflection,0.);
        float dampedReflection=pow(specularReflection,dampFactor);
        vec3 SpecularLight=dampedReflection*shineFactor*lightColor;
        light=light+SpecularLight;
    }

    fragColor=textureColor*vec4(light,1.);
}
