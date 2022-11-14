package Lesson_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task01 {
    public static void main(String[] args) {
        String inputData = "<client>(Какие то данные)<data>79991113344;test@yanasex.ru;Иванов Иван Иванович</data></client>";
        System.out.println(masking(inputData));
    }

    public static String masking(String str) {
        List<String> list = deleteByRegex(str);
        if (list.size() == 1)
            return list.toString().replaceAll("\\[", "").replaceAll("]", "");
        StringBuilder stringBuilder = new StringBuilder();
        String[] strings = list.get(1).split(";");
        stringBuilder.append(list.get(0));
        for (String s : strings) {
            if (s.matches("^\\d{11}$")) {
                stringBuilder.append(maskingNumber(s));
            } else if (s.matches("^\\D+\\s\\D+\\s\\D+$")) {
                stringBuilder.append(maskingDataUser(s));
            } else if (s.contains("@")) {
                stringBuilder.append(maskingEmail(s));
            }
        }
        return deleteDubSymbol(stringBuilder.append(list.get(2)).toString());
    }

    private static String deleteDubSymbol(String str){
        int numberChar = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ';')
                numberChar = i;
        }
        for (int i = 0; i < numberChar; i++) {
            stringBuilder.append(str.charAt(i));
        }
        for (int i = numberChar; i < str.length()-1; i++) {
            stringBuilder.append(str.charAt(i+1));
        }
        return stringBuilder.toString();
    }

    private static List<String> deleteByRegex(String str) {
        String stringSplit = str.replaceAll("<client>.+<data>|<client><data>", "").replaceAll("</data>.+</client>|</data></client>", "");
        if (stringSplit.isEmpty()) {
            List<String> list = new ArrayList<>();
            list.add(str);
            return list;
        } else {
            String[] strings = str.split(stringSplit);
            return Arrays.asList(strings[0], stringSplit, strings[1]);
        }
    }

    private static String maskingEmail(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean key = false;
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == '@') {
                stringBuilder.append(str.charAt(i));
            } else if (str.charAt(i + 1) == '@') {
                stringBuilder.append("*");
                key = true;
            } else if (key) {
                if (str.charAt(i) != '.') {
                    stringBuilder.append("*");
                } else {
                    stringBuilder.append(str.charAt(i));
                    key = false;
                }
            } else {
                stringBuilder.append(str.charAt(i));
            }
        }
        return stringBuilder.append(str.charAt(str.length() - 1)).append(";").toString();
    }

    private static String maskingDataUser(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] strings = str.split(" ");
        stringBuilder.append(strings[0]).append(" ");
        for (int i = 1; i < strings.length; i++) {
            if (i == 1) {
                for (int j = 0; j < strings[1].length(); j++) {
                    stringBuilder.append(j == 0 || j == strings[1].length() - 1 ? strings[1].charAt(j) : "*");
                }
            } else {
                stringBuilder.append(" ").append(strings[2].charAt(0)).append(".;");
            }
        }
        return stringBuilder.toString();
    }

    private static String maskingNumber(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            stringBuilder.append((i >= 4 && i <= 6) ? "*" : str.charAt(i));
        }
        return stringBuilder.append(";").toString();
    }
}
