package com.engine.geometry;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import static org.lwjgl.opengl.GL15.*;

public class MeshLoader {

    public static int getVAO()
    {
        return GL30.glGenVertexArrays();
    }
    public static int[] loadToVAO(int vao,float[] vertices){
        GL30.glBindVertexArray(vao);
        int vbo1=storeDataInAttributeList(0,3,vertices);
        unbindVOA();
        return new int[]{vbo1};
    }
    public static int[] loadToVAO(int vao,float[] vertices,int[] indexes){
        GL30.glBindVertexArray(vao);
        int vbo1=bindIndexesBuffer(indexes);
        int vbo2=storeDataInAttributeList(0,3,vertices);
        unbindVOA();
        return new int[]{vbo1,vbo2};
    }
    public static int[] loadToVAO(int vao,float[] vertices, int[] indexes, float[] atrib1,int atrib1Size)
    {
        GL30.glBindVertexArray(vao);
        bindIndexesBuffer(indexes);
        int vbo1=storeDataInAttributeList(0,3,vertices);
        int vbo2=storeDataInAttributeList(1,atrib1Size,atrib1);
        unbindVOA();
        return new int[]{vbo1,vbo2};
    }
    public static int[] loadToVAO(int vao,float[] vertices, float[] atrib1,int atrib1Size)
    {
        GL30.glBindVertexArray(vao);
        int vbo1=storeDataInAttributeList(0,3,vertices);
        int vbo2=storeDataInAttributeList(1,atrib1Size,atrib1);
        unbindVOA();
        return new int[]{vbo1,vbo2};
    }
    public static int[] loadToVAO(int vao,float[] vertices, int[] indexes, float[] atrib1,int atrib1Size,float[] atrib2,int atrib2Size)
    {
        GL30.glBindVertexArray(vao);
        bindIndexesBuffer(indexes);
        int vbo1=storeDataInAttributeList(0,3,vertices);
        int vbo2=storeDataInAttributeList(1,atrib1Size,atrib1);
        int vbo3=storeDataInAttributeList(2,atrib2Size,atrib2);
        unbindVOA();
        return new int[]{vbo1,vbo2,vbo3};
    }
    public static int[] loadToVAO(int vao,float[] vertices,float[] atrib1,int atrib1Size,float[] atrib2,int atrib2Size)
    {
        GL30.glBindVertexArray(vao);
        int vbo1=storeDataInAttributeList(0,3,vertices);
        int vbo2=storeDataInAttributeList(1,atrib1Size,atrib1);
        int vbo3=storeDataInAttributeList(2,atrib2Size,atrib2);
        unbindVOA();
        return new int[]{vbo1,vbo2,vbo3};
    }
    public static int[] loadToVAO(int vao,float[] vertices, int[] indexes, float[] atrib1,int atrib1Size,float[] atrib2,int atrib2Size,float[] atrib3,int atrib3Size)
    {
        GL30.glBindVertexArray(vao);
        bindIndexesBuffer(indexes);
        int vbo1=storeDataInAttributeList(0,3,vertices);
        int vbo2=storeDataInAttributeList(1,atrib1Size,atrib1);
        int vbo3=storeDataInAttributeList(2,atrib2Size,atrib2);
        int vbo4=storeDataInAttributeList(2,atrib3Size,atrib3);
        unbindVOA();
        return new int[]{vbo1,vbo2,vbo3,vbo4};
    }
    public static int[] loadToVAO(int vao,float[] vertices,float[] atrib1,int atrib1Size,float[] atrib2,int atrib2Size,float[] atrib3,int atrib3Size)
    {
        GL30.glBindVertexArray(vao);
        int vbo1=storeDataInAttributeList(0,3,vertices);
        int vbo2=storeDataInAttributeList(1,atrib1Size,atrib1);
        int vbo3=storeDataInAttributeList(2,atrib2Size,atrib2);
        int vbo4=storeDataInAttributeList(2,atrib3Size,atrib3);
        unbindVOA();
        return new int[]{vbo1,vbo2,vbo3,vbo4};
    }
    private static int bindIndexesBuffer(int[] indexes){
        int vbo=GL15.glGenBuffers();
        GL15.glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,vbo);
        IntBuffer buffer =storeDataInIntBuffer(indexes);
        GL15.glBufferData(GL_ELEMENT_ARRAY_BUFFER,buffer,GL_STATIC_DRAW);
        return vbo;
    }

    private static IntBuffer storeDataInIntBuffer(int[] data){
        IntBuffer buffer=BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
    private static int storeDataInAttributeList(int attributeNumber,int atributeSize,float[] data)
    {
        int vbo=glGenBuffers();
        GL15.glBindBuffer(GL_ARRAY_BUFFER,vbo);
        FloatBuffer buffer=storeDataInFloatBuffer(data);
        GL15.glBufferData(GL_ARRAY_BUFFER,buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber,atributeSize, GL11.GL_FLOAT,false,0,0);
        GL15.glBindBuffer(GL_ARRAY_BUFFER,0);
        return vbo;
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

}
