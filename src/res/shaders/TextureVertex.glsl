#version 400 core

in vec3 vertexPosition;
in vec2 textureCoord;
out vec2 uv;
uniform mat4 transformMatrix;
void main(){
    uv=textureCoord;
    gl_Position=transformMatrix*vec4(vertexPosition.xyz,1.);
}
