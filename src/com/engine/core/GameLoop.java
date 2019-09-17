package com.engine.core;

import com.engine.events.EventManager;
import com.engine.util.TimeHelper;

public class GameLoop {
        public static Boolean running = false;
        private static final int TARGET_FPS = 60;
        private static final boolean SHOW_FPS=true;
        private static final int TARGET_TIME_PER_FRAME = TimeHelper.NANO_SECOND / TARGET_FPS;
        private static final int MAX_UPDATES = 5;

        public static void start() {
            Thread thread = new Thread() {
                public void run() {
                    running = true;
                    long lastUpdateTime = System.nanoTime();
                    long lastFpsCheck=System.nanoTime();
                    int fps=0;
                    int updates = 0;
                    while (running) {
                        long currentTime = System.nanoTime();
                        updates = 0;
                        long delta=currentTime - lastUpdateTime;
                        while (delta >= TARGET_TIME_PER_FRAME) {
                            lastUpdateTime = System.nanoTime();
                            EventManager.onUpdate(TimeHelper.nanoSecondToSecond(delta));
                            delta=currentTime - lastUpdateTime;
                            updates++;
                            if (updates > MAX_UPDATES) break;
                        }

                        EventManager.onRender();
                        if(SHOW_FPS)
                        {
                            fps++;
                            if (System.nanoTime()>=lastFpsCheck+TimeHelper.NANO_SECOND)
                            {
                                System.out.println(fps);
                                fps=0;
                                lastFpsCheck=System.nanoTime();
                            }
                        }

                        long timeTaken = System.nanoTime() - currentTime;
                        if (timeTaken < TARGET_TIME_PER_FRAME) {
                            try {
                                sleep((TARGET_TIME_PER_FRAME - timeTaken) / 1000000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            };
            thread.setName("GameLoop");
            thread.start();
    }
}
