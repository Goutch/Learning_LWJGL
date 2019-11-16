#version 400 core

in vec4 position;
//Material
uniform vec3 materialColor;
//texture
uniform sampler2D textureSampler;
uniform int hasTexture;
in vec2 uv;

//vertexColor
in vec4 color;

in vec2 ao;

out vec4 fragColor;
void main(){

    if(hasTexture==1)
    {
        //texture
        vec4 textureColor=texture(textureSampler, uv);
        fragColor=color*textureColor*vec4(materialColor,1.)*length(ao);
    }
    else{
        fragColor =color*ao.x;

    }
}