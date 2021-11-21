package com.company;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        /*float floatVal = 2147483421.0f;
        double doubleVal = 2000000000000000000.0d;
        int intVal = 2147483647;
        long longVal = 9223372036854775807L;
        short shortVal = 32767;
        byte byteVal = 127;
        String  s1 = "I love";
        s1 += " Java";
        String s2 = "I";
        s2 += " love Java";

        int resultInt = (int) (longVal - intVal);
        long resultLong = longVal + intVal;
        short resultShort = (short) (intVal + shortVal);
        byte resultByte = (byte) (floatVal + byteVal);

        System.out.println ( ConsoleColors.WHITE_BOLD_BRIGHT +
                "Float : " + ConsoleColors.RED_BOLD +  floatVal + "\n" + ConsoleColors.WHITE_BOLD_BRIGHT +
                "Double : " + ConsoleColors.RED_BOLD + doubleVal + "\n" + ConsoleColors.WHITE_BOLD_BRIGHT +
                "Int : " + ConsoleColors.RED_BOLD + intVal + "\n" + ConsoleColors.WHITE_BOLD_BRIGHT +
                "Long : " + ConsoleColors.RED_BOLD + longVal + "\n" + ConsoleColors.WHITE_BOLD_BRIGHT +
                "Short : " + ConsoleColors.RED_BOLD + shortVal + "\n" + ConsoleColors.WHITE_BOLD_BRIGHT +
                "Byte : " + ConsoleColors.RED_BOLD + byteVal + ConsoleColors.RESET + "\n" +
                "------------------" + "\n"
        );

        System.out.println ( ConsoleColors.WHITE_BOLD_BRIGHT +
                "resultInt : " + ConsoleColors.RED_BOLD + resultInt + "\n" + ConsoleColors.WHITE_BOLD_BRIGHT +
                "resultLong : " + ConsoleColors.RED_BOLD + resultLong + "\n" + ConsoleColors.WHITE_BOLD_BRIGHT +
                "resultShort : " + ConsoleColors.RED_BOLD + resultShort + "\n" + ConsoleColors.WHITE_BOLD_BRIGHT +
                "resultByte : " + ConsoleColors.RED_BOLD + resultByte + "\n" + ConsoleColors.WHITE_BOLD_BRIGHT +
                ConsoleColors.RESET
        );

        if (s1  s2) {
            System.out.println(ConsoleColors.GREEN_BOLD + "Success");
        } else {
            System.out.println(ConsoleColors.RED_BOLD + "Fail");
        } */

        double[] val1 = {20.0d, 30.0d, 40.0d, 50.0d};
        double[] val2 = {5.0d, 4.0d, 3.0d, 2.0d};
        char[] opt = {'a', 's', 'm', 'd'};
        double[] results = new double[opt.length];

        if (args.length == 0) {
            for (int i = 0; i < opt.length; i++) {
                results[i] = execute(opt[i], val1[i], val2[i]);
            }

            for (double currentResult : results) {
                System.out.println(currentResult);
            }
        }
        else if (args.length == 1 && args[0].equals("interactive")) {
            interactiveMode();
        }
        else if (args.length == 3) {
            handleCommandline(args);
        } else {
            System.out.println("Please enter the correct values - operation code and 2 numerics");
        }
    }

    static void interactiveMode() {
        System.out.println("Enter an operation and 2 numbers: ");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String[] parts = userInput.split(" ");

        performOperation(parts);
    }

    private static void performOperation(String parts[]) {
        char opt = optFromString(parts[0]);
        if (opt == 'w') {
            handleWhen(parts);
        } else {
            double val1 = valueFromString(parts[1]);
            double val2 = valueFromString(parts[2]);
            double result = execute(opt, val1, val2);
            //System.out.println(results);
            displayResult(opt, val1, val2, result);
        }
    }

    private static void handleWhen(String[] parts) {
        LocalDate startDate = LocalDate.parse(parts[1]);
        long daysToAdd = (long) valueFromString(parts[2]);
        LocalDate newDate = startDate.plusDays(daysToAdd);
        String output = String.format("%s plus %d days is %s", startDate, daysToAdd, newDate);
        System.out.println(output);
    }

    private static void displayResult(char opt, double val1, double val2, double result) {
        char symbol = symbolFromOpt(opt);
        StringBuilder builder = new StringBuilder(20);

        /*builder.append(val1);
        builder.append(" ");
        builder.append(symbol);
        builder.append(" ");
        builder.append(val2);
        builder.append(" = ");
        builder.append(result);
        String output = builder.toString();*/

        String output = String.format("%.0f %c %.0f = %.0f", val1, symbol, val2, result);
        System.out.println(output);
    }

    private static char symbolFromOpt(char opt) {
        char[] opts = {'a', 's', 'd', 'm'};
        char[] symbols = {'+', '-', '/', '*'};
        char symbol = ' ';

        for(int index = 0; index < opts.length; index++) {
            if (opt == opts[index]) {
                symbol = symbols[index];
                break;
            }
        }

        return symbol;
    }

    static void handleCommandline(String[] args) {
        char opt = args[0].charAt(0);
        double val1 = Double.parseDouble(args[1]);
        double val2 = Double.parseDouble(args[2]);
        double results = execute(opt, val1, val2);
        System.out.println(results);
    }

    static double execute(char operation, double value1, double value2){
        double result;

        switch(operation) {
            case 'a':
                result =  value1 + value2;
                break;

            case 's':
                result = value1 - value2;
                break;

            case 'm':
                result = value1 * value2;
                break;

            case 'd':
                result = value2 != 0 ? value1 / value2 : 0.0d;
                break;

            default:
                System.out.println("Invalid operation: " + operation);
                result = 0.0d;
                break;
        }
        return result;
    }

    static char optFromString (String optName) {
        char opt = optName.charAt(0);
        return opt;
    }

    static double valueFromString (String wordName) {
        String[] word = {
                "zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine"
        };
        double value = -1d;
        for (int i = 0; i < word.length; i++) {
            if (wordName.equals(word[i])){
                value = i;
                break;
            }
        }

        if(value == -1d) {
            value = Double.parseDouble(wordName);
        }

        return value;
    }
}
