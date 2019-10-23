
#version 400 core

uniform mat4 transformMatrix;
uniform vec2 size;
uniform vec2 pivot;
in vec3 vertexPosition;
in vec2 textureCoord;
out vec2 uv;
void main()
{
    uv=textureCoord;
    gl_Position=transformMatrix*vec4((vertexPosition.xyz+vec3(pivot.xy,0))*vec3(size.xy,1.),1.);
}