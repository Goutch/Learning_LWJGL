#version 400 core
uniform mat4 transformMatrix;
uniform float time;

in vec3 vertexPosition;

void main()
{
    gl_Position=transformMatrix*vec4(vertexPosition.xyz,1.);
}