package com.example.android_numbersystemstranslator;

public class Examination {

    // проверка на корректность системы
    public static boolean filter_Sys(String systemCh){
        String alpha = "0123456789";
        if (systemCh.length() == 0) return false;
        for (int i = 0; i < systemCh.length(); i++)
            if (!alpha.contains(systemCh.charAt(i) + ""))
                return false;
        return true;
    }

    // проверка на заполнение всех полей
    public static boolean cleanPoli(String poli){
        return poli.length() > 0;
    }

    // проверка на корректность введёного числа
    public static boolean filter_Num(String num_error){
        String alpha = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (num_error.length() == 0) return false;
        num_error = num_error.toUpperCase();
        for (int i = 0; i < num_error.length(); i++)
            if (!alpha.contains(num_error.charAt(i) + ""))
                return false;
        return true;
    }

    // проверка на то правильно ли записана система для числа
    public static boolean filter_Sys_stack(String num, String sysChisl){
        int sysCh = Integer.valueOf(sysChisl + "");
        String alpha = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; num.length() > i; i++)
            if (alpha.indexOf(num.charAt(i) + "") >= sysCh)
                return false;
        return true;
    }
}
