#version 400 core

in vec2 uv;
out vec4 fragColor;
uniform sampler2D textureSampler;

void main(){
    vec4 t=texture(textureSampler, uv);
    fragColor=t;
}
