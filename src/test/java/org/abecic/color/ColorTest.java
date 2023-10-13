package org.abecic.color;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {
    @Test
    void testDecode1() {
        String hexColor = "#FF0000"; // Replace with your test input
        Color expectedColor = new Color(255, 0, 0);

        var decodedColor = Color.decode(hexColor);

        assertEquals(decodedColor.getRGB(), expectedColor.getRGB());
    }

    @Test
    void testDecode3() {
        String hexColor = "#0FCBD4"; // Replace with your test input
        Color expectedColor = new Color(15, 203, 212);

        var decodedColor = Color.decode(hexColor);

        assertEquals(decodedColor.getRGB(), expectedColor.getRGB());
    }

    @Test
    void testRGBtoHSB1() {
        var c = new Color(255, 255, 255);
        float[] hsb_expected = new float[3];
        hsb_expected[0] = 0;
        hsb_expected[1] = 0;
        hsb_expected[2] = 1;

        float[] hsb_converted = new float[3];
        Color.RGBtoHSB(c.getRed(), c.getBlue(), c.getGreen(), hsb_converted);

        assertArrayEquals(hsb_expected, hsb_converted);
    }

    @Test
    void testRGBtoHSB2() {
        var c = new Color(2, 23, 254);
        float[] hsb_expected = new float[3];
        hsb_expected[0] = 0.3472222F;
        hsb_expected[1] = 0.992126F;
        hsb_expected[2] = 0.99607843F;

        float[] hsb_converted = new float[3];
        Color.RGBtoHSB(c.getRed(), c.getBlue(), c.getGreen(), hsb_converted);

        assertArrayEquals(hsb_expected, hsb_converted);
    }

    @Test
    void testRGBtoHSL1() {
        var c = new Color(75, 191, 82);
        float[] hsl_expected = new float[3];
        hsl_expected[0] = 123.6207F;
        hsl_expected[1] = 0.47540987F;
        hsl_expected[2] = 0.52156866F;

        float[] hsl_converted = new float[3];
        Color.RGBtoHSL(75, 191, 82, hsl_converted);

        assertArrayEquals(hsl_expected, hsl_converted);
    }

    @Test
    void testRGBtoCMYK(){
        var c = new Color(16,16,16);
        float[] cymk_expected = new float[4];

        cymk_expected[0] = 0;
        cymk_expected[1] = 0;
        cymk_expected[2] = 0;
        cymk_expected[3] = 93.725494f;

        float[] cymk_converted = new float[4];

        Color.RGBtoCMYK(c.getRed(), c.getBlue(), c.getGreen(), cymk_converted);

        assertArrayEquals(cymk_converted, cymk_expected);
    }
}