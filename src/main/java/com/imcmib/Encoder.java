package com.imcmib;

public class Encoder {

    private final static int BLOCKS_COUNT = 4;
    private final static int BITS_COUNT = 8;

    /**
     * Encodes string using "The Weird Text Format (8-bit)" algorithm.
     * @param string string to encode.
     * @return encoded value in decimal format.
     */
    public int encode(String string) {
        System.out.println("Input:\t\t\t" + string);

        System.out.print("Input ASCII:\t");
        printHex(string);

        char[] decoded = strToBin(string);
        System.out.print("Decoded:\t\t");
        printChars(decoded);

        char[] encoded = encode(decoded);
        System.out.print("Encoded:\t\t");
        printChars(encoded);

        int result = binToDec(encoded);
        System.out.print("Output (hex):\t");
        System.out.println("0x" + Integer.toHexString(result));
        System.out.println("Output (dec):\t" + result);

        return result;
    }

    public String decode(int value) {
        System.out.println("Input (dec):\t" + value);

        char[] encoded = decToBin(value, BITS_COUNT * BLOCKS_COUNT);
        System.out.print("Encoded:\t\t");
        printChars(encoded);

        char[] decoded = decode(encoded);
        System.out.print("Decoded:\t\t");
        printChars(decoded);

        String result = binToStr(decoded);
        System.out.println("Output:\t\t\t" + result);

        return result;
    }

    /**
     * Encodes array of chars.
     * @param chars array of chars that should be encoded.
     * @return array of chars that are encoded.
     */
    private char[] encode(char[] chars) {
        char[] result = new char[chars.length];
        for (int i = 0; i < BLOCKS_COUNT; i++) {
            for (int j = 0; j < BITS_COUNT; j++) {
                result[i + (j * BLOCKS_COUNT)] = chars[(i * BITS_COUNT) + j];
            }
        }
        return result;
    }

    /**
     * Decodes array of chars.
     * @param chars array of chars that should be decoded.
     * @return array of chars that are decoded.
     */
    private char[] decode(char[] chars) {
        char[] result = new char[chars.length];
        for (int i = 0; i < BLOCKS_COUNT; i++) {
            for (int j = 0; j < BITS_COUNT; j++) {
                result[(i * BITS_COUNT) + j] = chars[i + (j * BLOCKS_COUNT)];
            }
        }
        return result;
    }

    /**
     * Converts string to binary.
     * @param string String that should be converted.
     * @return Array of chars that represents string in binary format.
     */
    private char[] strToBin(String string) {
        char[] result = new char[BLOCKS_COUNT * BITS_COUNT];
        for (int i = 0; i < BLOCKS_COUNT; i++) {
            char c = i >= string.length() ? '\0' : string.charAt(i);
            char[] binArray = decToBin(c, BITS_COUNT);
            int destPosition = result.length - ((i + 1) * BITS_COUNT);
            System.arraycopy(binArray, 0, result, destPosition, binArray.length);
        }

        return result;
    }

    /**
     * Converts binary chars array to string.
     * @param chars Array of chars that represents string in binary format.
     * @return String.
     */
    private String binToStr(char[] chars) {
        StringBuilder result = new StringBuilder();

        char[] array = new char[BITS_COUNT];
        for (int i = 0; i < BLOCKS_COUNT; i++) {
            int srcPosition = chars.length - ((i + 1) * BITS_COUNT);
            System.arraycopy(chars, srcPosition, array, 0, BITS_COUNT);
            int charCode = binToDec(array);
            if (charCode != '\0') {
                result.append((char) charCode);
            }
        }

        return result.toString();
    }

    /**
     * Converts binary array of chars to decimal number
     * @param chars array of chars that should be converted.
     * @return decimal value.
     */
    private int binToDec(char[] chars) {
        int result = 0;

        int base = 1;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '1')
                result += base;
            base = base * 2;
        }

        return result;
    }

    /**
     * Converts decimal number to binary array of chars
     * @param value decimal value that should be converted.
     * @param size size of an output array.
     * @return binary array of chars.
     */
    private char[] decToBin(int value, int size) {
        char[] result = new char[size];
        int count = 0;
        while (value > 0) {
            push(result, (value % 2 == 1) ? '1' : '0');
            value /= 2;
            count++;
        }
        for (int i = count; i < result.length; i++) {
            push(result, '0');
        }
        return result;
    }

    /**
     * Pushes new element to the head of the array and moves all other elements.
     * @param chars source array.
     * @param push element to push.
     */
    private void push(char[] chars, char push) {
        for (int i = chars.length - 2; i >= 0; i--) {
            chars[i + 1] = chars[i];
        }
        chars[0] = push;
    }

    /**
     * Prints string in HEX format separated by space.
     * @param string string to print.
     */
    private void printHex(String string) {
        for (int i = 0; i < string.length(); i++) {
            System.out.print("0x" + Integer.toHexString(string.charAt(i)));
            if (i < string.length() - 1) {
                System.out.print(' ');
            }
        }
        System.out.println();
    }

    /**
     * Prints chars array separated by space.
     * @param chars array to print.
     */
    private void printChars(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]);
            if ((i + 1) % BITS_COUNT == 0) {
                System.out.print(' ');
            }
        }
        System.out.println();
    }
}
