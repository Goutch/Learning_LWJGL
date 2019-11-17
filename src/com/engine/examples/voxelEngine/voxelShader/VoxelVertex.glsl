#version 400 core

//base
uniform mat4 transformMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
in vec3 vertexPosition;
out vec4 position;

//texture
in vec2 textureCoords;
out vec2 uv;

//vertexColor
in vec4 vertexColor;
out vec4 color;

in vec3 ambientOcclusion;
out vec3 ao;
void main(){

    ao=ambientOcclusion;
    color=vertexColor*ambientOcclusion.x;
    //texture
    uv=textureCoords;
    //position
    gl_Position=projectionMatrix*viewMatrix*transformMatrix*vec4(vertexPosition.xyz,1.);


}