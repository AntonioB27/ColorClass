package org.abecic.color;

import java.awt.color.ColorSpace;

import static java.lang.Math.max;

public class Color {
    int value;

    private float[] frgbvalue = null;

    private float[] fvalue = null;

    private float falpha = 0.0f;


    public int getRed() {
        return (getRGB() >> 16) & 0xFF;
    }

    public int getGreen() {
        return (getRGB() >> 8) & 0xFF;
    }

    public int getBlue() {
        return (getRGB()) & 0xFF;
    }

    public Color(int r, int g, int b, int a) {
        value = ((a & 0xFF) << 24) |
                ((r & 0xFF) << 16) |
                ((g & 0xFF) << 8) |
                ((b & 0xFF) << 0);
        testColorValueRange(r, g, b, a);
    }

    public Color(int r, int g, int b) {
        this(r, g, b, 255);
    }

    private static void testColorValueRange(int r, int g, int b, int a) {
        boolean rangeError = false;
        String badComponentString = "";

        if (a < 0 || a > 255) {
            rangeError = true;
            badComponentString = badComponentString + " Alpha";
        }
        if (r < 0 || r > 255) {
            rangeError = true;
            badComponentString = badComponentString + " Red";
        }
        if (g < 0 || g > 255) {
            rangeError = true;
            badComponentString = badComponentString + " Green";
        }
        if (b < 0 || b > 255) {
            rangeError = true;
            badComponentString = badComponentString + " Blue";
        }
        if (rangeError) {
            throw new IllegalArgumentException("Color parameter outside of expected range:"
                    + badComponentString);
        }
    }

    public int getRGB() {
        return value;
    }

    public static Color decode(String nm) throws NumberFormatException {
        Integer intval = Integer.decode(nm);
        int i = intval.intValue();
        return new Color((i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF);
    }

    public static float[] RGBtoHSB(int r, int g, int b, float[] hsbvals) {
        float hue, saturation, brightness;
        if (hsbvals == null) {
            hsbvals = new float[3];
        }
        int cmax = (r > g) ? r : g;
        if (b > cmax) cmax = b;
        int cmin = (r < g) ? r : g;
        if (b < cmin) cmin = b;

        brightness = ((float) cmax) / 255.0f;
        if (cmax != 0)
            saturation = ((float) (cmax - cmin)) / ((float) cmax);
        else
            saturation = 0;
        if (saturation == 0)
            hue = 0;
        else {
            float redc = ((float) (cmax - r)) / ((float) (cmax - cmin));
            float greenc = ((float) (cmax - g)) / ((float) (cmax - cmin));
            float bluec = ((float) (cmax - b)) / ((float) (cmax - cmin));
            if (r == cmax)
                hue = bluec - greenc;
            else if (g == cmax)
                hue = 2.0f + redc - bluec;
            else
                hue = 4.0f + greenc - redc;
            hue = hue / 6.0f;
            if (hue < 0)
                hue = hue + 1.0f;
        }
        hsbvals[0] = hue;
        hsbvals[1] = saturation;
        hsbvals[2] = brightness;
        return hsbvals;
    }

    public static float[] RGBtoHSL(int r, int g, int b, float[] hslvals){

        if(hslvals == null){
            hslvals = new float[3];
        }

        float h = 0,s = 0,l = 0;

        float red = r/255.0f;
        float green = g/255.0f;
        float blue = b/255.0f;

        float max = Math.max(red, Math.max(green, blue));
        float min = Math.min(red, Math.min(green, blue));

        l = (max + min) / 2.0f;

        if (max == min) {
            s = 0.0f;
        } else {
            s = (l < 0.5f) ? ((max - min) / (max + min)) : ((max - min) / (2.0f - max - min));
        }

        if (max == red) {
            h = (green - blue) / (max - min);
        } else if (max == green) {
            h = 2.0f + (blue - red) / (max - min);
        } else {
            h = 4.0f + (red - green) / (max - min);
        }

        h *= 60.0f;


        hslvals[0] = h;
        hslvals[1] = s;
        hslvals[2] = l;


        return hslvals;
    }
}
