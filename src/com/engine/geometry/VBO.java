package com.engine.geometry;

import com.engine.events.DisposeListener;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;

public class VBO implements DisposeListener {
    public static final int VERTICES_ATTRIBUTE_ID=0;
    public static final int VERTICES_ATTRIBUTE_SIZE=3;
    public static final int NORMALS_ATTRIBUTE_ID=1;
    public static final int NORMALS_ATTRIBUTE_SIZE=3;
    public static final int UVS_ATTRIBUTE_ID =2;
    public static final int UVS_ATTRIBUTE_SIZE=2;
    public static final int COLORS_ATTRIBUTE_ID=3;
    public static final int COLORS_ATTRIBUTE_SIZE=4;
    private int id=-1;
    private int position=-1;

    /**
     *
     * @param attributePosition
     * @param atributeSize
     * @param data
     */
    public VBO(int attributePosition,int atributeSize,float[] data)
    {
        id=glGenBuffers();
        position=attributePosition;
        GL15.glBindBuffer(GL_ARRAY_BUFFER,id);
        FloatBuffer buffer=storeDataInFloatBuffer(data);
        GL15.glBufferData(GL_ARRAY_BUFFER,buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributePosition,atributeSize, GL11.GL_FLOAT,false,0,0);
        GL15.glBindBuffer(GL_ARRAY_BUFFER,0);
    }

    /**
     *
     * @param attributePosition
     * @param atributeSize
     * @param data
     */
    public VBO(int attributePosition,int atributeSize,int[] data)
    {
        id=glGenBuffers();
        position=attributePosition;
        GL15.glBindBuffer(GL_ARRAY_BUFFER,id);
        IntBuffer buffer=storeDataInIntBuffer(data);
        GL15.glBufferData(GL_ARRAY_BUFFER,buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributePosition,atributeSize, GL11.GL_FLOAT,false,0,0);
        GL15.glBindBuffer(GL_ARRAY_BUFFER,0);
    }

    /**
     *
     * @param indices
     */
    public VBO(int[] indices)
    {
        id=glGenBuffers();
        GL15.glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,id);
        IntBuffer buffer =storeDataInIntBuffer(indices);
        GL15.glBufferData(GL_ELEMENT_ARRAY_BUFFER,buffer,GL_STATIC_DRAW);
    }
    public void bind()
    {
        GL20.glEnableVertexAttribArray(position);
    }
    public void unBind()
    {
        GL20.glDisableVertexAttribArray(position);
    }

    /**
     *
     * @return the vbo position inside the vao. return -1 if not set or is an indices vbo
     */
    public int getPosition()
    {
        return position;
    }
    private static FloatBuffer storeDataInFloatBuffer(float[] data)
    {
        FloatBuffer buffer= BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
    private static IntBuffer storeDataInIntBuffer(int[] data){
        IntBuffer buffer=BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    @Override
    public void dispose() {
        glDeleteBuffers(id);
    }
}
