#version 400 core

in vec3 position;

out vec3 vertexColor;

void main(void){
    gl_Position=vec4(position.xyz,1.);

    vertexColor=vec3(position.x+0.5,position.y+0.5,position.z);
}
