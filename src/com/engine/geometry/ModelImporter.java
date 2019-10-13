package com.engine.geometry;


import java.io.*;
import java.util.ArrayList;

import java.util.List;

public class ModelImporter {

    public static Mesh ImportModel(String filePath) {
        try {
            FileReader fileReader = new FileReader(new File(filePath));
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            /*
             * unordered list of the vertices,uvs,normals
             */
            List<Float> vertices = new ArrayList<Float>();
            List<Float> uvs = new ArrayList<Float>();
            List<Float> normals = new ArrayList<Float>();
            List<String> indices = new ArrayList<String>();
            String line = line = bufferedReader.readLine();


            while (line != null) {

                String[] words = line.split(" ");
                if (line.equals("") || words.length < 3) {
                    line = bufferedReader.readLine();
                    continue;
                }

                //vertices
                switch (words[0]) {
                    case "v":
                        vertices.add(Float.parseFloat(words[1]));
                        vertices.add(Float.parseFloat(words[2]));
                        vertices.add(Float.parseFloat(words[3]));
                        break;
                    //normals
                    case "vn":
                        normals.add(Float.parseFloat(words[1]));
                        normals.add(Float.parseFloat(words[2]));
                        normals.add(Float.parseFloat(words[3]));
                        break;
                    //uvs
                    case "vt":
                        uvs.add(Float.parseFloat(words[1]));
                        uvs.add(Float.parseFloat(words[2]));
                        break;
                    //indices
                    case "f":
                        if(words.length==5)
                        {
                            if(words[1].contains("/"))
                            {
                                indices.add(words[1]);
                                indices.add(words[2]);
                                indices.add(words[3]);
                                indices.add(words[3]);
                                indices.add(words[2]);
                                indices.add(words[4]);
                            }
                            else
                                {
                                indices.add(words[1]+"/"+words[2]+"/"+words[3]);
                                indices.add(words[3]+"/"+words[2]+"/"+words[4]);
                            }

                        }
                        if(words.length==4)
                        {
                            if(words[1].contains("/"))
                            {
                                indices.add(words[1]);
                                indices.add(words[2]);
                                indices.add(words[3]);
                            }
                            else
                            {
                                indices.add(words[1]+"/"+words[2]+"/"+words[3]);
                            }
                        }
                        break;
                }

                line = bufferedReader.readLine();
            }


            int[] indicesArray = new int[indices.size() * 3];
            float[] uvsArray = new float[(vertices.size() / 3) * 2];
            float[] normalsArray = new float[vertices.size()];
            float[] verticesArray = new float[vertices.size()];
            String[] verts;//0=vertexIndex , 1=uv index , 2=normal Index
            for (int i = 0; i < indices.size(); i++) {
                verts = indices.get(i).split("/");


                int vertexPointer = Integer.parseInt(verts[0]) - 1;
                int uvPointer = Integer.parseInt(verts[1]) - 1;
                int normalPointer = Integer.parseInt(verts[2]) - 1;

                indicesArray[i] = vertexPointer;

                verticesArray[vertexPointer * 3] = vertices.get(vertexPointer * 3);
                verticesArray[vertexPointer * 3 + 1] = vertices.get(vertexPointer * 3 + 1);
                verticesArray[vertexPointer * 3 + 2] = vertices.get(vertexPointer * 3 + 2);
                if(!uvs.isEmpty())
                {
                    uvsArray[vertexPointer * 2] = uvs.get(uvPointer * 2);
                    uvsArray[vertexPointer * 2 + 1] = 1 - uvs.get(uvPointer * 2 + 1);
                }

                if(!normals.isEmpty())
                {
                    normalsArray[vertexPointer * 3] = normals.get(normalPointer * 3);
                    normalsArray[vertexPointer * 3 + 1] = normals.get(normalPointer * 3 + 1);
                    normalsArray[vertexPointer * 3 + 2] = normals.get(normalPointer * 3 + 2);

                }

            }
            if (uvsArray.length==0)uvsArray=null;
            if(normalsArray.length==0)normalsArray=null;
            return new Mesh()
                    .vertices(verticesArray)
                    .indices(indicesArray)
                    .normals(normalsArray)
                    .uvs(uvsArray);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
