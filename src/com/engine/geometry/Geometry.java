package com.engine.geometry;

public class Geometry {
    public static Mesh getPlateData(int sizeX,int sizeZ)
    {
        float[] vertices=new float[(sizeX+1)*(sizeZ+1)*3];
        int[] indices =new int[sizeX*sizeZ*6];
        float[] normals=new float[(sizeX+1)*(sizeZ+1)*3];
        for (int x = 0; x < sizeX+1; x++) {
            for (int z = 0; z < sizeZ+1; z++) {
                int index=(x*(sizeZ+1))+z;
                vertices[index * 3] = z-(float)sizeZ/2;
                vertices[index * 3 + 1] = 0;
                vertices[index * 3 + 2] = x-(float)sizeX/2;
            }
        }


        for (int x = 0; x < sizeX; x++) {
            for (int z = 0; z < sizeZ; z++) {
                int index=(x*(sizeZ)+z);
                indices[index*6]=index+x;
                indices[index*6+1]=index+x+1;
                indices[index*6+2]=index+x+sizeZ+1;
                indices[index*6+3]=index+x+sizeZ+1;
                indices[index*6+4]=index+x+1;
                indices[index*6+5]=index+x+sizeZ+2;

            }
        }
        for (int i = 0; i <(sizeX+1)*(sizeZ+1) ; i++) {
            normals[i*3]= 0;
            normals[i*3+1]= 1;
            normals[i*3+2]= 0;
        }
        return new Mesh(vertices,indices,normals);
    }
    public static class Triangle {
        public static final float[] VERTICES = {
                0f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
        };
        public static final int[] INDEXES={
                1,2,3
        };
    }
    public static class Quad {
        public static final float[] VERTICES = {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
        };
        public static final int[] INDEXES={
                0,1,3,
                3,2,1
        };
        public static final float[] UVS={

                0f,0f,
                0f,1f,
                1f,1f,
                1f,0f,
        };
    }
    public static class Cube{
        public static final float[] VERTICES = {
                //LEFT
                -0.5f,0.5f,-0.5f,
                -0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,0.5f,-0.5f,

                //RIGHT
                -0.5f,0.5f,0.5f,
                -0.5f,-0.5f,0.5f,
                0.5f,-0.5f,0.5f,
                0.5f,0.5f,0.5f,

                //BACK
                0.5f,0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,0.5f,
                0.5f,0.5f,0.5f,

                //FRONT
                -0.5f,0.5f,-0.5f,
                -0.5f,-0.5f,-0.5f,
                -0.5f,-0.5f,0.5f,
                -0.5f,0.5f,0.5f,

                //UP
                -0.5f,0.5f,0.5f,
                -0.5f,0.5f,-0.5f,
                0.5f,0.5f,-0.5f,
                0.5f,0.5f,0.5f,

                //DOWN
                -0.5f,-0.5f,0.5f,
                -0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,0.5f

        };
        //public static final float[] VERTICES2 = {
        //        0.5f,0.5f,0.5f, //right up back     0
        //        -0.5f,0.5f,0.5f,//left up back      1
        //        0.5f,-0.5f,0.5f,//right down back   2
        //        0.5f,0.5f,-0.5f,//right up front    3
        //        -0.5f,-0.5f,0.5f,//left,down,back   4
        //        0.5f,-0.5f,-0.5f,//right,down,front 5
        //        -0.5f,0.5f,-0.5f,//left,up,front    6
        //        -0.5f,-0.5f,-0.5f,//left,down,front 7
        //};
        //public static final int[] INDEXES2={
        //        //BACK
        //        0,1,4,
        //        4,2,0,
        //        //FRONT
        //        3,5,7,
        //        7,6,3,
        //        //LEFT
        //        7,4,1,
        //        1,6,7,
        //        //RIGHT
        //        3,2,0,
        //        3,5,0,
        //        //UP
        //        //DOWN
//
        //} ;



        public static final int[] INDEXES = {
                //LEFT
                0,1,3,
                3,1,2,
                //RIGHT
                4,5,7,
                7,5,6,
                //BACK
                8,9,11,
                11,9,10,
                //FRONT
                12,13,15,
                15,13,14,
                //UP
                16,17,19,
                19,17,18,
                //DOWN
                20,21,23,
                23,21,22

        };
        public static final float[] UVS = {
                //LEFT
                0,0,
                0,1,
                1,1,
                1,0,
                //RIGHT
                0,0,
                0,1,
                1,1,
                1,0,
                //BACK
                0,0,
                0,1,
                1,1,
                1,0,
                //FRONT
                0,0,
                0,1,
                1,1,
                1,0,
                //UP
                0,0,
                0,1,
                1,1,
                1,0,
                //DOWN
                0,0,
                0,1,
                1,1,
                1,0
        };
    }
}
