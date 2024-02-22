package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
  @author   yaroslavkuzenko
  @project   untitled
  @class  UtilTest
  @version  1.0.0 
  @since 09.02.2024 - 22.47
*/class UtilTest {
    @Test
    public void whenNumberGreater3999ThenNull(){
        assertNull(Util.convertArabicToRoman(4000));
    }

    @Test
    public void whenNumberLess1ThenNull(){
        assertNull(Util.convertArabicToRoman(-3));
    }

    @Test
    public void whenNumberIs_1Then_I(){
        assertEquals(Util.convertArabicToRoman(1), "I");
    }

    @Test
    public void whenNumberIs_3Then_III(){
        assertEquals(Util.convertArabicToRoman(3), "III");
    }

    @Test
    public void whenNumberIs_5Then_V(){
        assertEquals(Util.convertArabicToRoman(5), "V");
    }

    @Test
    public void whenNumberIs_4Then_IV(){
        assertEquals(Util.convertArabicToRoman(4), "IV");
    }

    @Test
    public void whenNumberIs_6Then_VI(){
        assertEquals(Util.convertArabicToRoman(6), "VI");
    }

    @Test
    public void whenNumberIs_10Then_X(){
        assertEquals(Util.convertArabicToRoman(10), "X");
    }

    @Test
    public void whenNumberIs_9Then_IX(){
        assertEquals(Util.convertArabicToRoman(9), "IX");
    }

    @Test
    public void whenNumberIs_11Then_XI(){
        assertEquals(Util.convertArabicToRoman(11), "XI");
    }

    @Test
    public void whenNumberIs_30Then_XXX(){
        assertEquals(Util.convertArabicToRoman(30), "XXX");
    }

    @Test void whenNumberIs_37Then_XXXVII(){
        assertEquals(Util.convertArabicToRoman(37), "XXXVII");
    }

    @Test void whenNumberIs_50Then_L(){
        assertEquals(Util.convertArabicToRoman(50), "L");
    }

    @Test void whenNumberIs_40Then_XL(){
        assertEquals(Util.convertArabicToRoman(40), "XL");
    }

    @Test void whenNumberIs_60Then_LX(){
        assertEquals(Util.convertArabicToRoman(60), "LX");
    }

    @Test void whenNumberIs_100Then_C(){
        assertEquals(Util.convertArabicToRoman(100), "C");
    }

    @Test void whenNumberIs_90Then_XC(){
        assertEquals(Util.convertArabicToRoman(90), "XC");
    }

    @Test void whenNumberIs_120Then_CXX(){
        assertEquals(Util.convertArabicToRoman(120), "CXX");
    }

    @Test void whenNumberIs_99Then_XCIX(){
        assertEquals(Util.convertArabicToRoman(99), "XCIX");
    }

    @Test void whenNumberIs_150Then_CL(){
        assertEquals(Util.convertArabicToRoman(150), "CL");
    }

    @Test void whenNumberIs_300Then_CCC(){
        assertEquals(Util.convertArabicToRoman(300), "CCC");
    }

    @Test void whenNumberIs_500Then_D(){
        assertEquals(Util.convertArabicToRoman(500), "D");
    }

    @Test void whenNumberIs_400Then_CD(){
        assertEquals(Util.convertArabicToRoman(400), "CD");
    }

    @Test void whenNumberIs_800Then_DCCC(){
        assertEquals(Util.convertArabicToRoman(800), "DCCC");
    }


    @Test void whenNumberIs_1000Then_M(){
        assertEquals(Util.convertArabicToRoman(1000), "M");
    }

    @Test void whenNumberIs_900Then_CM(){
        assertEquals(Util.convertArabicToRoman(900), "CM");
    }

    @Test void whenNumberIs_1100Then_MC(){
        assertEquals(Util.convertArabicToRoman(1100), "MC");
    }

    @Test void whenNumberIs_1500Then_MD(){
        assertEquals(Util.convertArabicToRoman(1500), "MD");
    }

    @Test void whenNumberIs_1700Then_MD(){
        assertEquals(Util.convertArabicToRoman(1700), "MDCC");
    }

    @Test void whenNumberIs_1450Then_MD(){
        assertEquals(Util.convertArabicToRoman(1450), "MCDL");
    }

    @Test void whenNumberIs_2000Then_MM(){
        assertEquals(Util.convertArabicToRoman(2000), "MM");
    }

    @Test void whenNumberIs_3999Then_MMMCMXCIX(){
        assertEquals(Util.convertArabicToRoman(3999), "MMMCMXCIX");
    }
}