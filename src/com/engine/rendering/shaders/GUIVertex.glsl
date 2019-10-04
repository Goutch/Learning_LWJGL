
#version 400 core

uniform mat4 transformMatrix;
in vec3 vertexPosition;

void main()
{
    gl_Position=transformMatrix*vec4(vertexPosition.xyz,1.);
}