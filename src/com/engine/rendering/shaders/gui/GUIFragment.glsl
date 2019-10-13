#version 400 core
uniform vec4 color;
uniform vec4 borderColor;
uniform float borderWidth;
uniform float aspectRatio;
uniform int hasTexture;
uniform sampler2D textureSampler;
in vec2 uv;
out vec4 fragColor;
void main()
{
    float maxX = 1.0 - borderWidth;
    float minX = borderWidth;
    float maxY = 1.0-(borderWidth* aspectRatio);
    float minY = borderWidth*aspectRatio;

    if (uv.x <= maxX &&
    uv.x >= minX &&
    uv.y <= maxY &&
    uv.y >= minY) {
        if(hasTexture==0)
        {
            fragColor = color;
        }
        else{
            fragColor=texture(textureSampler,uv);
        }

    } else {
        fragColor = borderColor;
    }
}