#version 400 core

in vec3 vertexPosition;
in vec3 color;
out vec3 vertexColor;
void main()
{
    vertexColor=color;
    gl_Position=vec4(vertexPosition.xyz,1.);
}
