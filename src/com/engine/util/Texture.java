package com.engine.util;

import com.engine.events.DisposeListener;
import com.engine.events.EventManager;
import org.lwjgl.opengl.GL20;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

import static org.lwjgl.opengl.GL11.*;

/**
 * Represent a texture in memory
 */
public class Texture implements DisposeListener {

    private int width, height;
    private int texture;

    public Texture(String path) {
        EventManager.subscribeDispose(this);
        texture = load(path);
    }
    public Texture(Color[] colors,int width,int height)
    {
        EventManager.subscribeDispose(this);
        this.width=width;
        this.height=height;
        int[] data=new int[width*height];
        for (int i = 0; i < colors.length; i++) {
            data[i] = colors[i].toInt();
        }
        texture=createTexture(data);
    }
    public int width()
    {
        return width;
    }
    public int height()
    {
        return height;
    }
    private int load(String path) {
        int[] pixels = null;
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(path));
            width = image.getWidth();
            height = image.getHeight();
            pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] data = new int[width * height];
        for (int i = 0; i < width * height; i++) {
            int a = (pixels[i] & 0xff000000) >> 24;
            int r = (pixels[i] & 0xff0000) >> 16;
            int g = (pixels[i] & 0xff00) >> 8;
            int b = (pixels[i] & 0xff);

            data[i] = a << 24 | b << 16 | g << 8 | r;
        }
        return createTexture(data);
    }
    private int createTexture(int[] data)
    {

        int result = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, result);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        IntBuffer buffer = ByteBuffer.allocateDirect(data.length << 2)
                .order(ByteOrder.nativeOrder()).asIntBuffer();
        buffer.put(data).flip();

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA,
                GL_UNSIGNED_BYTE, buffer);
        glBindTexture(GL_TEXTURE_2D, 0);
        return result;
    }
    public void bind() {
        glBindTexture(GL_TEXTURE_2D, texture);
    }

    public void unBind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public int getTextureID() {
        return texture;
    }

    @Override
    public void dispose() {
        GL20.glDeleteTextures(texture);
    }
}