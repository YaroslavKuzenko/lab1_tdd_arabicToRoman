package org.example;
/*
  @author   yaroslavkuzenko
  @project   untitled
  @class  Util
  @version  1.0.0 
  @since 09.02.2024 - 22.40
*/

public class Util {
    public static String convertArabicToRoman(int number){
        String tysiachi[] = {"", "M", "MM", "MMM"};
        String sotni[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String desiatky[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String odynytsi[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        if(number > 3999 || number < 1){
            return null;
        }else{
                return tysiachi[number/1000] + sotni[(number%1000)/100] + desiatky[(number%100)/10] + odynytsi[number%10];
        }
    }
}
