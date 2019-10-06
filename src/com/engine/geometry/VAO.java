package com.engine.geometry;

import com.engine.events.DisposeListener;

import org.lwjgl.opengl.GL30;

import java.util.*;


import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

public class VAO implements DisposeListener {
    private int id=-1;
    private HashMap<Integer,VBO> vbos;
    private VBO indices=null;
    public VAO()
    {
        id=GL30.glGenVertexArrays();
        vbos=new HashMap<Integer, VBO>();
    }

    public void bind()
    {
        GL30.glBindVertexArray(id);
        for (VBO vbo:vbos.values())
        {
            vbo.bind();
        }

    }
    public void unbind()
    {
        for (VBO vbo:vbos.values())
        {
            vbo.unBind();
        }
        GL30.glBindVertexArray(0);
    }
    public void put(float[] data,int position,int size)
    {
        GL30.glBindVertexArray(id);
        VBO vbo=new VBO(position,size,data);
        remove(position);
        vbos.put(position,vbo);
        GL30.glBindVertexArray(0);
    }
    public void setIndices(int[] data)
    {
        if(indices!=null)
            indices.dispose();
        GL30.glBindVertexArray(id);
        indices=new VBO(data);
        GL30.glBindVertexArray(0);
    }

    public void clear()
    {
        for (int i = 0; i <vbos.size() ; i++) {
            remove(i);
        }
        if(indices!=null)
        {
            indices.dispose();
            indices=null;
        }

        vbos.clear();
    }
    public void remove(int position)
    {
        if(vbos.containsKey(position))
        {
            vbos.get(position).dispose();
            vbos.remove(position);
        }

    }
    @Override
    public void dispose() {
        for (VBO vbo:vbos.values())
        {
            vbo.dispose();
        }
        vbos.clear();
        glDeleteVertexArrays(id);
    }
}
