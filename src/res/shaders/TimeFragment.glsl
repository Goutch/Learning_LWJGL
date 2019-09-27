#version 400 core


out vec4 fragColor;

uniform float time;
void main()
{
    fragColor=vec4(sin(time),1.,1.,1.);
}