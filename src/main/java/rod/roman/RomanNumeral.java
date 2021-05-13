package rod.roman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RomanNumeral {
    public static final int MAX_INT_VALUE = 3999;
    public static final int MIN_INT_VALUE = 1;

    private static HashMap<String, Integer> romanValues;
    private static HashMap<String, Integer> romanUnits;
    private static HashMap<String, Integer> romanTens;
    private static HashMap<String, Integer> romanHundreds;
    private static HashMap<String, Integer> romanThousands;

    static {
        romanValues = new HashMap<String, Integer>();
        romanValues.put("I", 1);
        romanValues.put("V", 5);
        romanValues.put("X", 10);
        romanValues.put("L", 50);
        romanValues.put("C", 100);
        romanValues.put("D", 500);
        romanValues.put("M", 1000);

        romanUnits = new HashMap<String, Integer>(10);
        romanUnits.put("I", 1);
        romanUnits.put("II", 2);
        romanUnits.put("III", 3);
        romanUnits.put("IV", 4);
        romanUnits.put("V", 5);
        romanUnits.put("VI", 6);
        romanUnits.put("VII", 7);
        romanUnits.put("VIII", 8);
        romanUnits.put("IX", 9);
        romanTens = new HashMap<String, Integer>(10);
        romanTens.put("X", 10);
        romanTens.put("XX", 20);
        romanTens.put("XXX", 30);
        romanTens.put("XL", 40);
        romanTens.put("L", 50);
        romanTens.put("LX", 60);
        romanTens.put("LXX", 70);
        romanTens.put("LXXX", 80);
        romanTens.put("XC", 90);

        romanHundreds = new HashMap<String, Integer>(10);
        romanHundreds.put("C", 100);
        romanHundreds.put("CC", 200);
        romanHundreds.put("CCC", 300);
        romanHundreds.put("CD", 400);
        romanHundreds.put("D", 500);
        romanHundreds.put("DC", 600);
        romanHundreds.put("DCC", 700);
        romanHundreds.put("DCCC", 800);
        romanHundreds.put("CM", 900);

        romanThousands = new HashMap<String, Integer>(3);
        romanThousands.put("M", 1000);
        romanThousands.put("MM", 2000);
        romanThousands.put("MMM", 3000);

    }

    public int convert(String romanNumeral) {

        int result = 0;
        romanNumeral.toUpperCase();

        if ((romanNumeral.startsWith("I") || romanNumeral.startsWith("V"))) {
            if (!romanUnits.containsKey(romanNumeral)) {
                return result;
            }
            return romanUnits.get(romanNumeral);
        } else if (romanNumeral.startsWith("X") || romanNumeral.startsWith("L")) {
            if (romanTens.containsKey(romanNumeral))
                return romanTens.get(romanNumeral);
        } else if (romanNumeral.startsWith("C") || romanNumeral.startsWith("D")) {
            if (romanHundreds.containsKey(romanNumeral))
                return romanHundreds.get(romanNumeral);
        } else if (romanNumeral.startsWith("M")) {
            if (romanThousands.containsKey(romanNumeral))
                return romanThousands.get(romanNumeral);
            ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(romanNumeral.split("")));
            arrayList.remove(0);
            // Units
            if (arrayList.get(0).equals("I")
                    && !romanUnits.containsKey(romanNumeral.substring(romanNumeral.indexOf("I"))))
                return 0;
            if (arrayList.get(0).equals("V")
                    && !romanUnits.containsKey(romanNumeral.substring(romanNumeral.indexOf("V"))))
                return 0;
            // Tens
            if (arrayList.get(0).equals("X") || arrayList.get(0).equals("L")) {
                // Tens units
                String tensPart = romanNumeral.substring(romanNumeral.indexOf("X"));

                if (tensPart.indexOf("I") != -1) {
                    if (!romanUnits.containsKey(tensPart.substring(tensPart.indexOf("I"))))
                        return 0;
                } else if (tensPart.indexOf("V") != -1) {
                    if (!romanUnits.containsKey(tensPart.substring(tensPart.indexOf("V"))))
                        return 0;
                } else if (!romanTens.containsKey(String.join("", arrayList))) {
                    return 0;
                }
            }
            // Hundreds
            if (arrayList.get(0).equals("C")) {
                // Tens units
                String tensPart = romanNumeral.substring(romanNumeral.indexOf("X"));
                if (tensPart.indexOf("I") != -1) {
                    if (!romanUnits.containsKey(tensPart.substring(tensPart.indexOf("I"))))
                        return 0;
                } else if (tensPart.indexOf("V") != -1) {
                    if (!romanUnits.containsKey(tensPart.substring(tensPart.indexOf("V"))))
                        return 0;
                } else if (!romanTens.containsKey(String.join("", arrayList))) {
                    return 0;
                }
            }

        } else {
            return 0;
        }

        String[] romanNumeralStrings = romanNumeral.split("");

        if (!romanValues.keySet().containsAll(Arrays.asList(romanNumeralStrings)))
            return 0;

        for (int i = 0; i < romanNumeralStrings.length; i++) {

            if (romanNumeralStrings[i].equals("I") && (i < romanNumeralStrings.length - 1)) {
                if (romanNumeralStrings[i + 1].equals("V") || romanNumeralStrings[i + 1].equals("X")) {
                    result = result
                            + (romanValues.get(romanNumeralStrings[i + 1]) - romanValues.get(romanNumeralStrings[i]));
                    i++;
                    continue;
                }
            } else if ((romanNumeralStrings[i].equals("X") && (i < romanNumeralStrings.length - 1))
                    && (romanNumeralStrings[i + 1].equals("L") || romanNumeralStrings[i + 1].equals("C"))) {
                {
                    result = result
                            + (romanValues.get(romanNumeralStrings[i + 1]) - romanValues.get(romanNumeralStrings[i]));
                    i++;
                    continue;
                }
            } else if ((romanNumeralStrings[i].equals("C") && (i < romanNumeralStrings.length - 1)
                    && (romanNumeralStrings[i + 1].equals("D") || romanNumeralStrings[i + 1].equals("M")))) {
                result = result
                        + (romanValues.get(romanNumeralStrings[i + 1]) - romanValues.get(romanNumeralStrings[i]));
                i++;
                continue;
            }
            if (i < romanNumeralStrings.length - 1) {
                if (romanValues.get(romanNumeralStrings[i]) < romanValues.get(romanNumeralStrings[i + 1])) {
                    return 0;
                }
            } else if (i == romanNumeralStrings.length - 1) {
                if (romanValues.get(romanNumeralStrings[i - 1]) < romanValues.get(romanNumeralStrings[i])) {
                    return 0;
                }
            }
            result += romanValues.get(romanNumeralStrings[i]);

        }

        if (result > RomanNumeral.MAX_INT_VALUE || result < RomanNumeral.MIN_INT_VALUE)
            return 0;
        return result;
    }
}

// public class RomanNumeral {

// private static Map<Character, Integer> map;

// static {
// map = new HashMap<Character, Integer>();
// map.put('I', 1);
// map.put('V', 5);
// map.put('X', 10);
// map.put('L', 50);
// map.put('C', 100);
// map.put('D', 500);
// map.put('M', 1000);
// }

// public int convert(String s) {

// int convertedNumber = 0;
// for(int i = 0; i < s.length(); i++) {
// int currentNumber = map.get(s.charAt(i));
// int next = i+1 < s.length() ? map.get(s.charAt(i+1)) : 0;

// if(currentNumber >= next)
// convertedNumber += currentNumber;
// else
// convertedNumber -= currentNumber;
// }

// return convertedNumber;

// }
// }
