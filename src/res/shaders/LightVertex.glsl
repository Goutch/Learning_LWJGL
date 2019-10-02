#version 400 core

uniform mat4 transformMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform vec3 lightPosition;

in vec3 vertexPosition;
in vec3 vertexNormal;
out vec3 normal;
out vec3 lightDirection;

void main()
{
    vec4 position=transformMatrix*vec4(vertexPosition.xyz,1.);
    normal=(transformMatrix*vec4(vertexNormal,0.)).xyz;
    lightDirection=lightPosition-position.xyz;
    gl_Position=projectionMatrix*viewMatrix*position;

}