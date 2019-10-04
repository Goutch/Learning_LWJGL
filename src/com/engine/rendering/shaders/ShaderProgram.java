package com.engine.rendering.shaders;

import com.engine.entities.Entity;
import com.engine.events.DisposeListener;
import com.engine.events.EventManager;
import com.engine.geometry.Material;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

/**
 * Represent a vertex and fragment shader.
 */
public abstract class ShaderProgram implements DisposeListener {


    private int programID;
    private int vertexShaderID;
    private int fragmentShaderID;
    private int attributeCount =0;

    private static FloatBuffer matrixBuffer= BufferUtils.createFloatBuffer(16);
    public ShaderProgram(String vertexFile,String fragmentFile)
    {
        EventManager.subscribeDispose(this);
        vertexShaderID=loadShader(vertexFile,GL20.GL_VERTEX_SHADER);
        fragmentShaderID=loadShader(fragmentFile,GL20.GL_FRAGMENT_SHADER);
        programID=GL20.glCreateProgram();
        GL20.glAttachShader(programID,vertexShaderID);
        GL20.glAttachShader(programID,fragmentShaderID);
        bindAttributes();
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
        getAllUniformLocations();
        initUniforms();
    }

    public void start()
    {
        GL20.glUseProgram(programID);
    }

    protected void initUniforms(){}
    public void stop()
    {
        GL20.glUseProgram(0);
    }


    /**
     *  Bind the an attribute to the shader program ex: vertex positions, uvs , colors
     * @param attribute index of the attribute in the vertexArrayObject
     * @param variableName name of the attribute variable ex: "vertexPositions"
     */
    protected void bindAttribute(int attribute,String variableName)
    {
        GL20.glBindAttribLocation(programID,attribute,variableName);
        attributeCount++;
    }

    /**
     * Called in the constructor to bind all attributes to the program via the bindAttribute method
     */
    protected abstract void bindAttributes();

    protected void loadFloatUniform(int location,float value)
    {
        GL20.glUniform1f(location,value);
    }
    protected void loadVectorUniform(int location, Vector3f vector)
    {
        GL20.glUniform3f(location,vector.x,vector.y,vector.z);
    }
    protected void loadVectorUniform(int location,Vector4f vector) {
        GL20.glUniform4f(location,vector.x,vector.y,vector.z,vector.w);
    }
    protected void loadBooleanUniform(int location, boolean value)
    {
        GL20.glUniform1f(location, (value) ? 1 : 0);
    }
    protected void loadIntUniform(int location,int value)
    {
        GL20.glUniform1i(location,value);
    }
    protected void loadMatrixUniform(int location, Matrix4f matrix)
    {
        GL20.glUniformMatrix4fv(location, false, matrix.get(matrixBuffer));
    }
    protected int getUniformLocation(String uniformName)
    {
        return  GL20.glGetUniformLocation(programID,uniformName);
    }

    /**
     * Called in the constructor to get the location of the shader uniforms via the method getUniformLocation
     */
    protected abstract void getAllUniformLocations();

    public int getAttributeCount()
    {
        return attributeCount;
    }

    /**
     * Load the shader from a file.
     * @param file
     * @param type
     * @return The shader reference
     */
    private static int loadShader(String file,int type){
        StringBuilder shaderSource = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine())!=null){
                shaderSource.append(line).append("//\n");
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS )== GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader!");
            System.exit(-1);
        }
        return shaderID;
    }
    @Override
    public void dispose()
    {
        stop();
        GL20.glDetachShader(programID,vertexShaderID);
        GL20.glDetachShader(programID,fragmentShaderID);
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        GL20.glDeleteProgram(programID);
    }
}
