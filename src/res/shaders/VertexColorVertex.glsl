#version 400 core

in vec3 vertexPosition;
in vec3 color;
out vec3 vertexColor;

uniform mat4 transformMatrix;
void main()
{
    vertexColor=color;
    gl_Position=transformMatrix*vec4(vertexPosition.xyz,1.);
}
