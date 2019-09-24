package com.engine.geometry;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.LinkedList;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

public class GeometryLoader {

    private static LinkedList<Integer> vaos=new LinkedList<Integer>();
    private static LinkedList<Integer> vbos=new LinkedList<Integer>();
    private static LinkedList<Integer> textures=new LinkedList<Integer>();
    public static int loadToVAO(float[] positions,int[] indexes){
        int vaoID=createVAO();
        bindIndexesBuffer(indexes);
        storeDataInAttributeList(0,positions);
        unbindVOA();
        return vaoID;
    }
    public static int loadTexture(String path,String format)
    {
        Texture texture=null;
        int textureID=0;
        try {
            texture= TextureLoader.getTexture(format,new FileInputStream(path));
            textureID=texture.getTextureID();
            textures.add(textureID);

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return textureID;
    }
    private static void bindIndexesBuffer(int[] indexes){
        int vbo=GL15.glGenBuffers();
        vbos.add(vbo);
        GL15.glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,vbo);
        IntBuffer buffer =storeDataInIntBuffer(indexes);
        GL15.glBufferData(GL_ELEMENT_ARRAY_BUFFER,buffer,GL_STATIC_DRAW);
    }

    private static IntBuffer storeDataInIntBuffer(int[] data){
        IntBuffer buffer=BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
    private static int createVAO()
    {
        int vao= GL30.glGenVertexArrays();
        vaos.add(vao);
        GL30.glBindVertexArray(vao);
        return vao;
    }
    private static void storeDataInAttributeList(int attributeNumber,float[] data)
    {
        int vbo=glGenBuffers();
        vbos.add(vbo);
        GL15.glBindBuffer(GL_ARRAY_BUFFER,vbo);
        FloatBuffer buffer=storeDataInFloatBuffer(data);
        GL15.glBufferData(GL_ARRAY_BUFFER,buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber,3, GL11.GL_FLOAT,false,0,0);
        GL15.glBindBuffer(GL_ARRAY_BUFFER,0);
    }

    private static void unbindVOA()
    {
        GL30.glBindVertexArray(0);
    }

    private static FloatBuffer storeDataInFloatBuffer(float[] data)
    {
        FloatBuffer buffer= BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }


    public static void dispose() {
        for (int t:textures)
        {
            glDeleteTextures(t);
        }
        for (int vao:vaos)
        {
            glDeleteVertexArrays(vao);
        }
        for (int vbo:vbos)
        {
            glDeleteBuffers(vbo);
        }
    }
}
