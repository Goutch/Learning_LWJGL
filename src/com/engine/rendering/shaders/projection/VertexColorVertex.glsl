#version 400 core



//base
uniform mat4 transformMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
in vec3 vertexPosition;
out vec4 position;
//diffuselight
in vec3 vertexNormal;
out vec3 normal;
//directionnal
uniform int directionalLightCount;

//point
uniform int pointLightsCount;
uniform vec3 pointLightsPositions[4] ;

out vec3 toPointsLightsDirections[4] ;

//specularLight
out vec3 toCameraDirection;

//texture
in vec2 textureCoord;
out vec2 uv;

//vertexColor
in vec4 vertexColor;
out vec4 color;
void main(){
    color=vertexColor;
    //texture
    uv=textureCoord;
    //Diffuselight
    position=transformMatrix*vec4(vertexPosition.xyz,1.);
    normal=(transformMatrix*vec4(vertexNormal,0.)).xyz;

    for(int i=0;i<pointLightsCount;i++)
    {
        toPointsLightsDirections[i]=pointLightsPositions[i]-position.xyz;
    }

    //SpecularLight
    toCameraDirection=(inverse(viewMatrix)*vec4(0.,0.,0.,1.)).xyz-position.xyz;

    //position
    gl_Position=projectionMatrix*viewMatrix*position;
}