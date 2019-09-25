#version 400 core

in vec3 vertexPosition;
out vec4 vertexColor;
void main()
{

    vertexColor=vec4(vertexPosition.xyz, 1.);
    gl_Position=vec4(vertexPosition.xyz,1.);
}