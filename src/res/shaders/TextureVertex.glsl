#version 400 core

uniform mat4 transformMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

in vec3 vertexPosition;
in vec2 textureCoord;
out vec2 uv;

void main(){
    uv=textureCoord;
    gl_Position=projectionMatrix*viewMatrix*transformMatrix*vec4(vertexPosition.xyz,1.);
}
