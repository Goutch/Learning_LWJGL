#version 400 core
float scaleX=5.;
float scaleY=5.;
int complexity=5;
float speed=1.;
out vec4 fragColor
in vec2 fragCoord
void main()
{

    vec2 uv = fragCoord/iResolution.xy;

    float x=uv.x*scaleX;
    float y=uv.y*scaleY;

    //time scaled to speed;
    float time=iTime*speed;

    float r=cos(x+time);
    float b=cos(y+time+(3.1416/3.));
    float g=cos(y+x+time+((3.1416/3.)*2.));

    for(int i=0;i<complexity;i++)
    {
        r*=abs(sin(r-b))+.31416;
        b*=abs(sin(b-g))+.31416;
        g*=abs(sin(g-r))+.31416;

        r*=abs(cos(r-b))+.31416;
        b*=abs(cos(b-g))+.31416;
        g*=abs(cos(g-r))+.31416;

    }

    vec3 color = vec3(abs(r),abs(b),abs(g));

    fragColor = vec4(color,1.0);
}