#version 400 core

uniform mat4 transformMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

in vec3 vertexPosition;
in vec4 color;
out vec4 vertexColor;


void main()
{
    vertexColor=color;
    gl_Position=projectionMatrix*viewMatrix*transformMatrix*vec4(vertexPosition.xyz,1.);
}