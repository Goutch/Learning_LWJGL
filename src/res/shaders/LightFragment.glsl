#version 400 core
uniform vec3 lightColor;
in vec3 normal;
in vec3 lightDirection;

out vec4 fragColor;
void main()
{
    vec3 normalizedNormal=normalize(normal);
    vec3 normalizedLightDirection=normalize(lightDirection);
    float lightValue=dot(normalizedNormal,normalizedLightDirection);
    clamp(lightValue,0,1);
    fragColor=vec4((lightColor*lightValue).rgb,1.);
}