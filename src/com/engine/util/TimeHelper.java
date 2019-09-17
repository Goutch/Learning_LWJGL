package com.engine.util;

public class TimeHelper {
    public static final int NANO_SECOND=1000000000;
    public static long secondToNanoSecond(float sec)
    {
        return (long)sec*NANO_SECOND;
    }
    public static float nanoSecondToSecond(Long nanoSec)
    {
        return (float)nanoSec/NANO_SECOND;
    }
}
