#version 400 core

in vec3 vertexPosition;



uniform mat4 transformMatrix;

void main()
{

    gl_Position=transformMatrix*vec4(vertexPosition.xyz,1.);
}