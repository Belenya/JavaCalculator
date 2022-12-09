import java.io.IOException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.*;
public class Main {
    public static List<String> operations = Arrays.stream(new String[] {"+", "-", "/", "*"}).toList();
    public static List<String> roman = Arrays.stream(new String[] {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"}).toList();
    public static List<String> arabic = Arrays.stream(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}).toList();

    public static void main(String[] args) throws IOException
    {
        Scanner scanner= new Scanner(System.in);
        for(;;)
        {
            var string = scanner.nextLine();
            System.out.println(calc(string));
        }
    }

    public static String calc(String string) throws IOException
    {
        var example = parse(string);
        var a = example[0];
        var b = example[2];
        var operation = example[1];

        isCorrectOperation(operation);
        isCorrectOperands(a, b);

        if (isArabic(a))
        {
            return doCalc(Integer.parseInt(a), Integer.parseInt(b), operation) + "";
        }
        else
        {
            var result = doCalc(roman.indexOf(a) + 1, roman.indexOf(b) + 1, operation);
            return arabicToRoman(result);
        }
    }

    public static int doCalc(int a, int b, String op)
    {
        int result = 0;
        switch (op)
        {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "/" :
                result = a / b;
                break;
            case "*" :
                result = a * b;
                break;
            default: break;
        }
        return result;
    }
    public static void isCorrectOperation(String operation) throws IOException
    {
        if (!operations.contains(operation))
        {
            throw new IOException("Неправильно указана операция с числами.");
        }
    }

    public static String[] parse(String string) throws IOException
    {
        var result = string.split(" ");
        if (Arrays.stream(result).count() != 3)
        {
            throw new IOException("Выражение должно состоять из трех частей.");
        }
        return result;
    }

    public static void isCorrectOperands(String a, String b) throws IOException
    {
        if (arabic.contains(a) && arabic.contains(b))
        {
        }
        else if (roman.contains(a) && roman.contains(b))
        {
            if (roman.indexOf(a) + 1 < roman.indexOf(b) + 1)
            {
                throw new IOException("В римской системе нет отрицательных чисел.");

            }
        }
        else
        {
            throw new IOException("Неправильно введены цифры, должны быть только арабские или римские цифры не превышающие 10 включительною.");
        }
    }

    public static boolean isArabic(String a)
    {
        return arabic.contains(a);
    }
    public static String arabicToRoman(int num)
    {
        if (num >= 4000 || num <= 0)
            return null;
        StringBuilder result = new StringBuilder();
        for(Integer key : units.descendingKeySet()) {
            while (num >= key) {
                num -= key;
                result.append(units.get(key));
            }
        }
        return result.toString();
    }
    private static final NavigableMap<Integer, String> units;
    static {
        NavigableMap<Integer, String> initMap = new TreeMap<>();
        initMap.put(1000, "M");
        initMap.put(900, "CM");
        initMap.put(500, "D");
        initMap.put(400, "CD");
        initMap.put(100, "C");
        initMap.put(90, "XC");
        initMap.put(50, "L");
        initMap.put(40, "XL");
        initMap.put(10, "X");
        initMap.put(9, "IX");
        initMap.put(5, "V");
        initMap.put(4, "IV");
        initMap.put(1, "I");
        units = Collections.unmodifiableNavigableMap(initMap);
    }
}

