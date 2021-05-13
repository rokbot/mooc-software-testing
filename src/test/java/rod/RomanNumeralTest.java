package rod;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rod.roman.RomanNumeral;

public class RomanNumeralTest {

    private RomanNumeral rn;

    @BeforeEach
    void init() {
        rn = new RomanNumeral();
    }

    // Edge cases
    @Test
    public void convertMaxValue_MMMCMXCIX_to3999() {
        int result = rn.convert("MMMCMXCIX");
        Assertions.assertEquals(3999, result);
    }

    @Test
    public void convertMinValue_I_to1() {
        int result = rn.convert("I");
        Assertions.assertEquals(1, result);
    }

    // No roman symbols

    @Test
    public void convertNoRomanSymbolsTo0() {
        String[] invalid_roman_symbols = { "A", "B" };
        int[] expected = new int[invalid_roman_symbols.length];

        int[] actual = Arrays.asList(invalid_roman_symbols).stream().mapToInt(x -> rn.convert(x)).toArray();

        Assertions.assertArrayEquals(expected, actual);
    }

    // Roman Units
    @Test
    public void convertUnits() {
        String[] units = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
        int[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        int[] actual = Arrays.asList(units).stream().mapToInt(x -> rn.convert(x)).toArray();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void convertUnitsInvalidto0() {
        String[] invalid_numbers = { "IVI", "IIII", "VIIII" };
        int[] expected = new int[invalid_numbers.length];

        int[] actual = Arrays.asList(invalid_numbers).stream().mapToInt(x -> rn.convert(x)).toArray();

        Assertions.assertArrayEquals(expected, actual);
    }

    // Tens
    @Test
    public void convertTens() {
        String[] units = { "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
        int[] expected = { 10, 20, 30, 40, 50, 60, 70, 80, 90 };

        int[] actual = Arrays.asList(units).stream().mapToInt(x -> rn.convert(x)).toArray();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void convertTensXXXIXto39() {
        int result = rn.convert("XXXIX");
        Assertions.assertEquals(39, result);
    }

    @Test
    public void convertTensXVto15() {
        int result = rn.convert("XV");
        Assertions.assertEquals(15, result);
    }

    // Hundreds

    @Test
    public void convertHundreds() {
        String[] units = { "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
        int[] expected = { 100, 200, 300, 400, 500, 600, 700, 800, 900 };

        int[] actual = Arrays.asList(units).stream().mapToInt(x -> rn.convert(x)).toArray();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void convertHundredCCXLVI_to246() {
        int result = rn.convert("CCXLVI");
        Assertions.assertEquals(246, result);
    }

    // Thousands
    @Test
    public void convertThousands() {
        String[] units = { "M", "MM", "MMM" };
        int[] expected = { 1000, 2000, 3000 };

        int[] actual = Arrays.asList(units).stream().mapToInt(x -> rn.convert(x)).toArray();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void convertThousandsInvalidto0() {
        String[] invalid_numbers = { "MLXXXC", "MVL", "DM", "MIIII", "MIVI", "MXXXX", "MXIIII", "MMMM" };
        int[] expected = new int[invalid_numbers.length];

        int[] actual = Arrays.asList(invalid_numbers).stream().mapToInt(x -> rn.convert(x)).toArray();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void convertThousandMIII_to1003() {
        int result = rn.convert("MIII");
        Assertions.assertEquals(1003, result);
    }

    @Test
    public void convertThousandMXI_to1011() {
        int result = rn.convert("MXI");
        Assertions.assertEquals(1011, result);
    }

    @Test
    public void convertMLXVIto1066() {
        int result = rn.convert("MLXVI");
        Assertions.assertEquals(1066, result);
    }

    @Test
    public void convertMLXXXto1080() {
        int result = rn.convert("MLXXX");
        Assertions.assertEquals(1080, result);
    }

}