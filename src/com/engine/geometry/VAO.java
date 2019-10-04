package com.engine.geometry;

import com.engine.events.DisposeListener;

import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.List;


import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

public class VAO implements DisposeListener {
    private int id=-1;
    private List<VBO> vbos;
    private VBO indices=null;
    public VAO()
    {
        id=GL30.glGenVertexArrays();
        vbos=new ArrayList<VBO>();
    }

    public void bind()
    {
        GL30.glBindVertexArray(id);
        for (VBO vbo:vbos)
        {
            vbo.bind();
        }

    }
    public void unbind()
    {
        for (VBO vbo:vbos)
        {
            vbo.unBind();
        }
        GL30.glBindVertexArray(0);
    }
    public void put(float[] data,int position,int size)
    {
        GL30.glBindVertexArray(id);
        VBO vbo=new VBO(position,size,data);
        clear(position);
        vbos.add(vbo);
        GL30.glBindVertexArray(0);
    }
    public void setindices(int[] data)
    {
        if(indices!=null)
            indices.dispose();
        GL30.glBindVertexArray(id);
        indices=new VBO(data);
        GL30.glBindVertexArray(0);
    }

    public void clearAll()
    {
        for (int i = 0; i <vbos.size() ; i++) {
            clear(i);
        }
        if(indices!=null)
        {
            indices.dispose();
            indices=null;
        }

        vbos.clear();
    }
    public void clear(int position)
    {
        int index=-1;
        for (int i = 0; i <vbos.size() ; i++) {
            if(vbos.get(i).getPosition()==position)
                index=i;
        }
        if(index!=-1) {
            vbos.get(index).dispose();
            vbos.remove(index);
        }
    }
    @Override
    public void dispose() {
        for (VBO vbo:vbos)
        {
            vbo.dispose();
        }
        vbos.clear();
        glDeleteVertexArrays(id);
    }
}
