
#version 400 core

uniform mat4 transformMatrix;

in vec3 vertexPosition;
in vec2 textureCoord;
out vec2 uv;
void main()
{
    uv=textureCoord;
    gl_Position=transformMatrix*vec4(vertexPosition.xyz,1.);
}