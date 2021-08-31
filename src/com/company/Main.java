package com.company;

import java.util.Scanner;

import static com.company.Main.NumberConvertManager.transform_number_to_roman_numeral;

public class Main<m> {

    public static void main(String[] args) {

        System.out.println("Добрый день. Приветствуем Вас в нашей программе КАЛЬКУЛЯТОР.");
        System.out.println("Введите выражение для расчета:");
        Scanner d = new Scanner(System.in);
        String str = new String();
        str = d.nextLine();

        char[] chArray = str.toCharArray(); //преобразовываем строку в массив
        String vvod = str;

        Proverka proverka1 = new Proverka();
        proverka1.proverka(vvod);
        int m = proverka1.proverka(vvod);
        int t1 = proverka1.rim(str);
        int t2 = proverka1.arab(vvod);

        int res1 = 0, res2 = 0, res3 = 0;

// выполнение задания арабскими цифрами
        if (t2 > 0) {
            OprChisel arab1 =  new OprChisel();
            res1 = arab1.arab1(m,vvod);
            res2 = arab1.arab2(m,vvod);
        }
        //выполнение задания римскими цифрами
        if (t1 > 0) {
            OprChisel rim =  new OprChisel();
            res1 = rim.rim1(m,vvod);
            res2 = rim.rim2(m,vvod);

        }

        switch (chArray[m]) {
            case '/':
                res3 = res1 / res2;
                break;
            case '*':
                res3 = res1 * res2;
                break;
            case '-':
                res3 = res1 - res2;
                break;
            case '+':
                res3 = res1 + res2;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + chArray[m]);
        }
        if ((res3 < 0 || res3 > 3999) && t1>0) {
            System.out.println("Ошибка. В римском исчислении нет отрицательных чисел и нет чисел больше 3999");
            System.exit(0);

        }

        int number_input = res3;
        String roman_numeral_output = transform_number_to_roman_numeral(number_input);
        if (res3 == 0 && t1>0) {
            System.out.println("В римском представлении ноль не представлен");

        }
        if (res3 > 0 && res3<=3999 && t1>0) {
            System.out.println("Результат в римском представлении:" + roman_numeral_output);
        }
        if (t2>0) {
            System.out.println("Результат в арабском представлении: " + number_input);
        }
    }

    public static int pow(int value, int powValue) {
        return (int) Math.pow(value, powValue);
    }


    class NumberConvertManager {
        public static String transform_number_to_roman_numeral(int number) {
            int[] roman_value_list = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
            String[] roman_char_list = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < roman_value_list.length; i += 1) {
                while (number >= roman_value_list[i]) {
                    number -= roman_value_list[i];
                    res.append(roman_char_list[i]);
                }
            }
            return res.toString();
        }
    }
    static class Proverka {

        int proverka(String vvod) {
            //привет
            char[] chArray = vvod.toCharArray();
            char[] chArray1 = {'i','v','x','l','c','d','m','I','V','X','L','C','D','M'};
            int bi = chArray.length;
            int t = 0, n = 0, m = 0, t1 = 0, t2 = 0, t3 = 0;
            boolean arab = false;

//перебираем все элементы массива
            for (int i = 0; i < chArray.length; i++) {
                if (chArray[i] == '/' || chArray[i] == '*' || chArray[i] == '-' || chArray[i] == '+') {
                    t++;//считаем количество математических функций
                    m = i; //порядковый номер мат функции

                }
                for (int a = 0; a< chArray1.length; a++){
                    if (chArray[i] == chArray1[a]) {
                        t1++;//считаем количество римских цифр
                    }
                }

                if (chArray[i] != 'I' && chArray[i] != 'V' && chArray[i] != 'X' && chArray[i] != 'L' && chArray[i] != 'C' && chArray[i] != 'D' && chArray[i] != 'M') {
                    if (chArray[i] != 'i' && chArray[i] != 'v' && chArray[i] != 'x' && chArray[i] != 'l' && chArray[i] != 'c' && chArray[i] != 'd' && chArray[i] != 'm') {
                        arab = Character.isDigit(chArray[i]);
                        if (chArray[i] != '/' && chArray[i] != '*' && chArray[i] != '-' && chArray[i] != '+' && arab == false) {
                            t3++;//считаем количество неизвестных символов
                        }
                    }
                }
                arab = Character.isDigit(chArray[i]); //подсчет арабских цифр
                if (arab == true) {
                    t2++;
                }
            }

            if (t != 1) {
                System.out.println("Ошибка. Отсутствует математическая функция или их несколько. Должна быть только одна.");
                    System.exit(0);
                }
            if (t1 > 0 && t2 > 0) {
                System.out.println("Ошибка. Система чисел должна быть однотипна: арабская или римская.");
                    System.exit(0);
               }
            if (t3 != 0) {
               System.out.println("Ошибка. Введены недопустимые символы.");
                    System.exit(0);
                }
            return m;

        }
        int arab (String vvod){
            char[] chArray = vvod.toCharArray();
            int t2 = 0;
            boolean arab = false;

//перебираем все элементы массива
            for (int i = 0; i < chArray.length; i++) {
                arab = Character.isDigit(chArray[i]); //подсчет арабских цифр
                if (arab == true) {
                    t2++;
                }
            } return t2;
        }

        int rim (String vvod1){ //подсчет римских цифр
            char[] chArray = vvod1.toCharArray();
            char[] chArray1 = {'i','v','x','l','c','d','m','I','V','X','L','C','D','M'};
            int t1 = 0;

            for (int i = 0; i < chArray.length; i++) {
                for (int a = 0; a< chArray1.length; a++){
                if (chArray[i] == chArray1[a]) {
                        t1++;//считаем количество римских цифр
                    }
                }
            //    System.out.println(t1);
              //  System.out.println("массив"+chArray[i]);
            }
            return t1;
       }
    }
    static class OprChisel{
        int arab1 (int m, String vvod){
            boolean arab = false;
            char[] chArray = vvod.toCharArray();
            int[] intArray = new int[chArray.length];
            String str11;
            int res1 =0;
            for (int i = 0; i < m; i++) {
                arab = Character.isDigit(chArray[i]);
                if (arab == true) {
                    str11 = String.valueOf(chArray[i]);
                    intArray[i] = Integer.parseInt(str11.trim()) * pow(10, m - 1 - i);
                    res1 = intArray[i] + res1;
                }

            }
            return res1;
        }
        int arab2 (int m, String vvod){
            boolean arab = false;
            char[] chArray = vvod.toCharArray();
            int[] intArray = new int[chArray.length];
            String str12;
            int res2 =0;
            for (int i = m + 1; i < chArray.length; i++) {
                arab = Character.isDigit(chArray[i]);
                if (arab == true) {
                    str12 = String.valueOf(chArray[i]);
                    intArray[i] = Integer.parseInt(str12.trim()) * pow(10, (chArray.length - 1 - i));
                    res2 = intArray[i] + res2;
                }

            }
            return res2;
        }
        int rim1 (int m, String vvod){
            char[] chArray = vvod.toCharArray();
            int[] intArray = new int[chArray.length];
            int[] intArray1 = new int[chArray.length];
            int res1 =0;
            for (int i = 0; i < m; i++) {
                switch (chArray[i]) {
                    case 'I':
                        intArray[i] = 1;
                        break;
                    case 'V':
                        intArray[i] = 5;
                        break;
                    case 'X':
                        intArray[i] = 10;
                        break;
                    case 'L':
                        intArray[i] = 50;
                        break;
                    case 'C':
                        intArray[i] = 100;
                        break;
                    case 'D':
                        intArray[i] = 500;
                        break;
                    case 'M':
                        intArray[i] = 1000;
                        break;
                    case 'i':
                        intArray[i] = 1;
                        break;
                    case 'v':
                        intArray[i] = 5;
                        break;
                    case 'x':
                        intArray[i] = 10;
                        break;
                    case 'l':
                        intArray[i] = 50;
                        break;
                    case 'c':
                        intArray[i] = 100;
                        break;
                    case 'd':
                        intArray[i] = 500;
                        break;
                    case 'm':
                        intArray[i] = 1000;
                        break;
                }
            }
            for (int i = 0; i < m; i++) {
                if (intArray[i] >= intArray[i + 1]) {
                    intArray1[i] = intArray[i];

                }
                if (intArray[i] < intArray[i + 1]) {
                    intArray1[i] = intArray[i + 1] - intArray[i];
                    i++;
                }
            }
            for (int i = 0; i < m; i++) {
                res1 = intArray1[i] + res1;
            }
            return res1;
        }
        int rim2 (int m, String vvod) {
            char[] chArray = vvod.toCharArray();
            int[] intArray = new int[chArray.length];
            int[] intArray2 = new int[chArray.length];
            int res2 = 0;
            for (int i = m + 1; i < chArray.length; i++) {
                switch (chArray[i]) {
                    case 'I':
                        intArray[i - m - 1] = 1;
                        break;
                    case 'V':
                        intArray[i - m - 1] = 5;
                        break;
                    case 'X':
                        intArray[i - m - 1] = 10;
                        break;
                    case 'L':
                        intArray[i - m - 1] = 50;
                        break;
                    case 'C':
                        intArray[i - m - 1] = 100;
                        break;
                    case 'D':
                        intArray[i - m - 1] = 500;
                        break;
                    case 'M':
                        intArray[i - m - 1] = 1000;
                        break;
                    case 'i':
                        intArray[i - m - 1] = 1;
                        break;
                    case 'v':
                        intArray[i - m - 1] = 5;
                        break;
                    case 'x':
                        intArray[i - m - 1] = 10;
                        break;
                    case 'l':
                        intArray[i - m - 1] = 50;
                        break;
                    case 'c':
                        intArray[i - m - 1] = 100;
                        break;
                    case 'd':
                        intArray[i - m - 1] = 500;
                        break;
                    case 'm':
                        intArray[i - m - 1] = 1000;
                        break;
                }
            }
            for (int i = 0; i < chArray.length - m; i++) {
                if (intArray[i] >= intArray[i + 1]) {
                    intArray2[i] = intArray[i];

                }
                if (intArray[i] < intArray[i + 1]) {
                    intArray2[i] = intArray[i + 1] - intArray[i];
                    i++;
                }
            }
            for (int i = 0; i < chArray.length - m - 1; i++) {
                res2 = intArray2[i] + res2;
            }
            return res2;
        }
    }

}


