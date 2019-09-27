package com.engine.geometry;


import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.LinkedList;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

public class MeshLoader {

    private static LinkedList<Integer> vaos=new LinkedList<Integer>();
    private static LinkedList<Integer> vbos=new LinkedList<Integer>();

    public static int loadToVAO(float[] vertices,int[] indexes){
        int vaoID=createVAO();
        bindIndexesBuffer(indexes);
        storeDataInAttributeList(0,3,vertices);
        unbindVOA();
        return vaoID;
    }
    public static int loadToVAO(float[] vertices,int[] indexes,float[] uvs)
    {
        int vaoID=createVAO();
        bindIndexesBuffer(indexes);
        storeDataInAttributeList(0,3,vertices);
        storeDataInAttributeList(1,2,uvs);
        unbindVOA();
        return vaoID;
    }
    public static int loadToVAO(float[] vertices, int[] indexes, float[] atrib,int atribSize)
    {
        int vaoID=createVAO();
        bindIndexesBuffer(indexes);
        storeDataInAttributeList(0,3,vertices);
        storeDataInAttributeList(1,atribSize,atrib);
        unbindVOA();
        return vaoID;
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
    private static void storeDataInAttributeList(int attributeNumber,int atributeSize,float[] data)
    {
        int vbo=glGenBuffers();
        vbos.add(vbo);
        GL15.glBindBuffer(GL_ARRAY_BUFFER,vbo);
        FloatBuffer buffer=storeDataInFloatBuffer(data);
        GL15.glBufferData(GL_ARRAY_BUFFER,buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber,atributeSize, GL11.GL_FLOAT,false,0,0);
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
