package com.engine.geometry;
import com.engine.rendering.shader.ShaderProgram;
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

    public static int[] loadToVAO(int vao,float[] vertices, int[] indexes, float[] normals,float[] uvs,float[] colors)
    {
        GL30.glBindVertexArray(vao);
        bindIndexesBuffer(indexes);
        int verticesVBO=storeDataInAttributeList(ShaderProgram.VERTICES_ATTRIBUTE_ID,ShaderProgram.VERTICES_ATTRIBUTE_SIZE,vertices);
        int normalsVBO=normals==null?-1:storeDataInAttributeList(ShaderProgram.NORMALS_ATTRIBUTE_ID,ShaderProgram.NORMALS_ATTRIBUTE_SIZE,normals);
        int uvsVBO=uvs==null?-1:storeDataInAttributeList(ShaderProgram.UVS_ATTRIBUTE_ID,ShaderProgram.UVS_ATTRIBUTE_SIZE,uvs);
        int colorsVBO=colors==null?-1:storeDataInAttributeList(ShaderProgram.COLORS_ATTRIBUTE_ID,ShaderProgram.COLORS_ATTRIBUTE_SIZE,colors);
        unbindVOA();
        return new int[]{verticesVBO,uvsVBO,normalsVBO,colorsVBO};
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
