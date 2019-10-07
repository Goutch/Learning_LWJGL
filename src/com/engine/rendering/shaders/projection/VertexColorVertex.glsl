#version 400 core


//base
uniform mat4 transformMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
in vec3 vertexPosition;

//diffuselight
uniform vec3 lightPosition;
in vec3 vertexNormal;
out vec3 normal;
out vec3 toLightDirection;


//specularLight
out vec3 toCameraDirection;

//vertexColor
in vec4 vertexColor;
out vec4 color;


void main()
{
    //vertexColor
    color=vertexColor;

    //Diffuselight
    vec4 position=transformMatrix*vec4(vertexPosition.xyz,1.);
    normal=(transformMatrix*vec4(vertexNormal,0.)).xyz;
    toLightDirection=lightPosition-position.xyz;
    //SpecularLight
    toCameraDirection=(inverse(viewMatrix)*vec4(0.,0.,0.,1.)).xyz-position.xyz;

    //position
    gl_Position=projectionMatrix*viewMatrix*position;

}