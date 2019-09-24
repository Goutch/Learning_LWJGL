#version 400 core

in vec3 vertexColor;

out vec4 fragColor;

void main(void){
    fragColor=vec4(vertexColor.rgb,1);
}