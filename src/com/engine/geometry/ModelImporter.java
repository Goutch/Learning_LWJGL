package com.engine.geometry;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.assimp.Assimp;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ModelImporter {

    public static TexturedMesh ImportModel(String filePath)
    {
        try {
            FileReader fileReader=new FileReader(new File(filePath));
            BufferedReader bufferedReader=new BufferedReader(fileReader);

            /*
             * unordered list of the vertices,uvs,normals
             */
            List<Float> vertices=new ArrayList<Float>();
            List<Float> uvs=new ArrayList<Float>();
            List<Float> normals=new ArrayList<Float>();

            String line=line=bufferedReader.readLine();
            while (line!=null)
            {

                String[] words=line.split(" ");
                if(line.equals("")||words.length<3)
                {
                    line=bufferedReader.readLine();
                    continue;
                }

                //vertices
                if(words[0].equals("v"))
                {
                    vertices.add(Float.parseFloat(words[1]));
                    vertices.add(Float.parseFloat(words[2]));
                    vertices.add(Float.parseFloat(words[3]));
                }
                //normals
                else if(words[0].equals("vn"))
                {
                    normals.add(Float.parseFloat(words[1]));
                    normals.add(Float.parseFloat(words[2]));
                    normals.add(Float.parseFloat(words[3]));
                }
                //uvs
                else if(words[0].equals("vt"))
                {
                    uvs.add(Float.parseFloat(words[1]));
                    uvs.add(Float.parseFloat(words[2]));
                }
                //indices
                else if(words[0].equals("f"))
                {
                    break;
                }
                line=bufferedReader.readLine();
            }

            List<Integer> indices=new ArrayList<Integer>();

            float[] uvsArray=new float[(vertices.size()/3)*2];
            float[] normalsArray=new float[vertices.size()];
            float[] verticesArray=new float[vertices.size()];
            while (line!=null)
            {
                String[] words=line.split(" ");

                if(words[0].equals("f"))
                {
                    String[] verts;//0=vertexIndex , 1=uv index , 2=normal Index

                    for (int i = 1; i < 4; i++) {
                        verts=words[i].split("/");
                        int vertexPointer=Integer.parseInt(verts[0])-1;
                        int uvPointer=Integer.parseInt(verts[1])-1;
                        int normalPointer=Integer.parseInt(verts[2])-1;

                        indices.add(vertexPointer);

                        verticesArray[vertexPointer*3]=vertices.get(vertexPointer*3);
                        verticesArray[vertexPointer*3+1]=vertices.get(vertexPointer*3+1);
                        verticesArray[vertexPointer*3+2]=vertices.get(vertexPointer*3+2);

                        uvsArray[vertexPointer*2]=uvs.get(uvPointer*2);
                        uvsArray[vertexPointer*2+1]=1-uvs.get(uvPointer*2+1);

                        normalsArray[vertexPointer*3]=normals.get(normalPointer*3);
                        normalsArray[vertexPointer*3+1]=normals.get(normalPointer*3+1);
                        normalsArray[vertexPointer*3+2]=normals.get(normalPointer*3+2);
                    }
                }
                line=bufferedReader.readLine();
            }

            int[] indicesArray=new int[indices.size()];
            for (int i = 0; i <indices.size() ; i++) {

                indicesArray[i]=indices.get(i);
            }

            return new TexturedMesh(verticesArray,indicesArray,normalsArray,uvsArray);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
