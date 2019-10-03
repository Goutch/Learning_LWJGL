#version 400 core


//base
uniform mat4 transformMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
in vec3 vertexPosition;


//light
uniform vec3 lightPosition;
in vec3 vertexNormal;
out vec3 normal;
out vec3 lightDirection;

//texture
in vec2 textureCoord;
out vec2 uv;
void main(){
    //texture
    uv=textureCoord;

    //light
    vec4 position=transformMatrix*vec4(vertexPosition.xyz,1.);
    normal=(transformMatrix*vec4(vertexNormal,0.)).xyz;
    lightDirection=lightPosition-position.xyz;

    //position
    gl_Position=projectionMatrix*viewMatrix*position;
}
