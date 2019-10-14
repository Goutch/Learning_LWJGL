package com.voxelEngine;

import com.engine.entity.Transform;
import com.engine.geometry.Mesh;
import com.engine.util.Color;
import org.joml.Vector3f;

import java.util.LinkedList;

public class VoxelChunk extends Mesh{

    private static final Vector3f BOTTOM_LEFT_FRONT=new Vector3f(-0.5f,-0.5f,0.5f);
    private static final Vector3f BOTTOM_LEFT_BACK=new Vector3f(-0.5f,-0.5f,-0.5f);
    private static final Vector3f BOTTOM_RIGHT_FRONT=new Vector3f(0.5f,-0.5f,0.5f);
    private static final Vector3f BOTTOM_RIGHT_BACK=new Vector3f(0.5f,-0.5f,-0.5f);
    private static final Vector3f TOP_LEFT_FRONT=new Vector3f(-0.5f,0.5f,0.5f);
    private static final Vector3f TOP_LEFT_BACK=new Vector3f(-0.5f,0.5f,-0.5f);
    private static final Vector3f TOP_RIGHT_FRONT=new Vector3f(0.5f,0.5f,0.5f);
    private static final Vector3f TOP_RIGHT_BACK=new Vector3f(0.5f,0.5f,-0.5f);

    private boolean data[][][];
    public VoxelChunk(boolean data[][][])
    {
        setData(data);
    }
    public void setData(boolean[][][] data) {
        this.data=data;
        LinkedList<Float> vertices=new LinkedList<>();
        LinkedList<Integer> indices=new LinkedList<>();
        LinkedList<Color> colors=new LinkedList<>();
        LinkedList<Vector3f> normals=new LinkedList<>();
        int sizeY=data.length;
        int sizeX=data[0].length;
        int sizeZ=data[0][0].length;
        for (int y = 0; y <sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                for (int z = 0; z < sizeZ; z++) {
                    if (data[y][x][z]) {
                        makeCube(vertices,indices,normals,colors,x,y,z,data,sizeX,sizeY,sizeZ);
                    }
                }
            }
        }
        float[] vertArray=new float[vertices.size()];
        int count=0;
        for (Float f:
             vertices) {
            vertArray[count]=f;
            count++;
        }
        int[] indicesArray=new int[indices.size()];
        count=0;
        for (Integer i:indices)
        {
            indicesArray[count]=i;
            count++;
        }
        count=0;
        float[] normalsArray=new float[normals.size()*3];
        for (Vector3f n:normals)
        {
            normalsArray[count]=n.x;
            count++;
            normalsArray[count]=n.y;
            count++;
            normalsArray[count]=n.z;
            count++;
        }
        Color[] colorArray=new Color[colors.size()];
        count=0;
        for (Color c:colors)
        {
            colorArray[count]=c;
            count++;
        }
        vertices(vertArray).indices(indicesArray).colors(colorArray).normals(normalsArray);
    }
    private static void makeCube(LinkedList<Float> vertices,LinkedList<Integer> indices,LinkedList<Vector3f> normals,LinkedList<Color> colors,int x,int y,int z,boolean[][][] data,int sizeX,int sizeY,int sizeZ)
    {
        Vector3f offSet=new Vector3f(x,y,z);
        //front
        if(z+1>=sizeZ || !data[y][x][z+1]){
            makeQuad(vertices,indices,normals,colors,BOTTOM_LEFT_FRONT,TOP_LEFT_FRONT,BOTTOM_RIGHT_FRONT,TOP_RIGHT_FRONT,offSet, Transform.FOWARD,Color.WHITE);
        }
        //back
        if(z-1<0||!data[y][x][z-1])
        {
            makeQuad(vertices,indices,normals,colors,BOTTOM_RIGHT_BACK,TOP_RIGHT_BACK,BOTTOM_LEFT_BACK,TOP_LEFT_BACK,offSet,Transform.BACKWARD,Color.RED);
        }
	    //left
        if(x-1<0||!data[y][x-1][z]){
            makeQuad(vertices,indices,normals,colors,BOTTOM_LEFT_BACK,TOP_LEFT_BACK,BOTTOM_LEFT_FRONT,TOP_LEFT_FRONT,offSet,Transform.LEFT,Color.GREEN);
        }
        //right
        if(x+1>=sizeX||!data[y][x+1][z])
        {
            makeQuad(vertices,indices,normals,colors,BOTTOM_RIGHT_FRONT,TOP_RIGHT_FRONT,BOTTOM_RIGHT_BACK,TOP_RIGHT_BACK,offSet,Transform.RIGHT,Color.BLUE);
        }
        //top
        if(y+1>=sizeY||!data[y+1][x][z])
        {
            makeQuad(vertices,indices,normals,colors,TOP_LEFT_FRONT,TOP_LEFT_BACK,TOP_RIGHT_FRONT,TOP_RIGHT_BACK,offSet,Transform.UP,Color.PINK);
        }
	    //bottom
        if(y-1<0||!data[y-1][x][z])
        {
            makeQuad(vertices,indices,normals,colors,BOTTOM_LEFT_BACK,BOTTOM_LEFT_FRONT,BOTTOM_RIGHT_BACK,BOTTOM_RIGHT_FRONT,offSet,Transform.DOWN,Color.YELLOW);
        }
    }
    private static void makeQuad(LinkedList<Float> vertices, LinkedList<Integer> indices,LinkedList<Vector3f> normals,LinkedList<Color> colors, Vector3f v1, Vector3f  v2, Vector3f v3,Vector3f v4,Vector3f offSet,Vector3f normal,Color color) {
        //2---4
        //| / |
        //1---3
        int i =vertices.size()/3;
        vertices.add(v1.x+offSet.x);
        vertices.add(v1.y+offSet.y);
        vertices.add(v1.z+offSet.z);
        colors.add(color);
        normals.add(normal);
        vertices.add(v2.x+offSet.x);
        vertices.add(v2.y+offSet.y);
        vertices.add(v2.z+offSet.z);
        colors.add(color);
        normals.add(normal);
        vertices.add(v3.x+offSet.x);
        vertices.add(v3.y+offSet.y);
        vertices.add(v3.z+offSet.z);
        colors.add(color);
        normals.add(normal);
        vertices.add(v4.x+offSet.x);
        vertices.add(v4.y+offSet.y);
        vertices.add(v4.z+offSet.z);
        colors.add(color);
        normals.add(normal);

        indices.add(i+3);
        indices.add(i+1);
        indices.add(i);

        indices.add(i);
        indices.add(i+2);
        indices.add(i+3);

    }
}
