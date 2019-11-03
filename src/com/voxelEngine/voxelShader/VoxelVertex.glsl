#version 400 core



//base
uniform mat4 transformMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
in vec3 vertexPosition;
out vec4 position;

//texture
in vec2 textureCoord;
out vec2 uv;

//vertexColor
in vec4 vertexColor;
out vec4 color;
void main(){
    color=vertexColor;
    //texture
    uv=textureCoord;
    //position
    gl_Position=projectionMatrix*viewMatrix*transformMatrix*vec4(vertexPosition.xyz,1.);
}