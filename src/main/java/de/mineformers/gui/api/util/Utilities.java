package de.mineformers.gui.api.util;

public class Utilities
{
    public static float lerp(float a, float b, float x)
    {
        return a + (b - a) * x;
    }
}
