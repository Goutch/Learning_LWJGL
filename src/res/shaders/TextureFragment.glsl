#version 400 core



//light
uniform vec3 lightColor;
in vec3 normal;
in vec3 lightDirection;

//texture
uniform sampler2D textureSampler;
in vec2 uv;


out vec4 fragColor;
void main(){
    //texture
    vec4 textureColor=texture(textureSampler, uv);
    //Light
    vec3 normalizedNormal=normalize(normal);
    vec3 normalizedLightDirection=normalize(lightDirection);
    float lightValue=dot(normalizedNormal,normalizedLightDirection);
    clamp(lightValue,0,1);
    vec4 light=vec4(lightColor*lightValue,1.);

    fragColor=textureColor*light;
}
