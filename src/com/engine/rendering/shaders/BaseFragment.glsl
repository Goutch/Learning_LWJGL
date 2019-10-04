#version 400 core
//Material
uniform vec3 materialColor;
out vec4 fragColor;
void main()
{
    fragColor=vec4(materialColor,1.);
}