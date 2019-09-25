#version 400 core

in vec3 vertexPosition;
in vec2 textureCoord;
out vec2 uv;

void main(){
    uv=textureCoord;
    gl_Position=vec4(vertexPosition.xyz,1.);
}
