#version 400 core

in vec3 color;

out vec4 outColor;

void main(void){
    outColor=vec4(color.rgb,1);
}