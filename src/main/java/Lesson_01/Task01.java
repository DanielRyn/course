package Lesson_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Task01 {
    public static void main(String[] args) {
        System.out.println("Test №1");
        System.out.println("\t" + masking("<client>что то тут есть...<data>79991113344;danil1872@yandex.ru;Даниилов Даниил Даниилович</data></client>"));
        System.out.println("Test №2");
        System.out.println("\t" + masking("<client>что то тут есть...<data>79991113344;danil1872@yandex.ru</data></client>"));
    }

    public static String masking(String str) {
        List<String> list = separationString(str);
        if (list.size() == 1) {
            return list.toString().replaceAll("\\[", "").replaceAll("]", "");
        }
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

    private static String deleteDubSymbol(String str) {
        int numberChar = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ';') {
                numberChar = i;
            }
        }
        for (int i = 0; i < str.length() - 1; i++) {
            stringBuilder.append(i < numberChar ? str.charAt(i) : str.charAt(i + 1));
        }
        return stringBuilder.toString();
    }

    private static List<String> separationString(String str) {
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
        return new StringBuilder().append(str)
                .replace(str.indexOf("@") - 1, str.indexOf("@"), "*")
                .replace(str.indexOf("@") + 1, str.indexOf("."), repeat('*', (str.indexOf(".") - str.indexOf("@")) - 1)).append(";").toString();
    }

    private static String maskingDataUser(String str) {
        StringBuilder stringBuilder = new StringBuilder(), stringBuilderTemp = new StringBuilder();
        String[] strings = str.split(" ");
        stringBuilder.append(strings[0])
                .replace(1, strings[0].length() - 1, repeat('*', strings[0].length() - 2)).append(" ").append(strings[1]).append(" ");
        stringBuilderTemp.append(strings[2]).replace(1, strings[2].length(), ".;");
        return stringBuilder.append(stringBuilderTemp).toString();
    }

    private static String maskingNumber(String str) {
        return new StringBuilder().append(str).replace(4, 7, repeat('*', 3)).append(";").toString();
    }

    //в моей jdk нету такого метода в реализации String, поэтому сделал свой
    private static String repeat(char symbol, int repeat) {
        StringBuilder stringBuilder = new StringBuilder();
        Stream.iterate(1, o -> o + 1).limit(repeat).forEach(o -> stringBuilder.append("*"));
        return stringBuilder.toString();
    }
}
