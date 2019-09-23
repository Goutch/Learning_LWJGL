package com.engine.core;

public class Game {
    public Game(int startWidth,int startHeight,boolean vsync,boolean fullScreen,String title)
    {
        GameOptions.WINDOW_START_WIDTH=startWidth;
        GameOptions.WINDOW_START_HEIGHT=startHeight;
        GameOptions.VSYNC=vsync;
        GameOptions.FULLSCREEN=fullScreen;
        GameOptions.TITLE=title;

    }
    public Game()
    {

    }

    public void start()
    {
        GameLoop.start();
    }

}
