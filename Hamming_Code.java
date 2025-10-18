import java.util.*;
import java.io.*;
import java.time.*;
import java.lang.*;

public class Hamming_Code {

    // Convert input string into array
    static int[] toBinaryArray(String input) {
        int[] arr = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            arr[i] = input.charAt(i) - '0'; // Conver char "1"/"0" into int 1/0
        }
        return arr;
    }

    // Find how many parity bits are needed
    static int findParityBits(int dataLength) {
        int r = 0;
        while (Math.pow(2, r) < (dataLength + r + 1)) {
            r++;
        }
        return r;
    }

    // Insert parity bits (-1) at position 1 ,2 4 ,8 ......
    static int[] insertParityBits(int[] data, int r) {
        int totalLength = data.length + r;
        int[] code = new int[totalLength + 1];
        int j = 0;
        for (int i = 0; i <= totalLength; i++) {
            if  ((i & (i - 1)) == 0){
                code[i] = -1;

            } else {
                code[i] = data[data.length-1-j];
                j++;
            }
        }
        return code;
    }

    // Calculate parity bits using XOR
    static void calculateParity(int[] code, int r) {
        for (int i = 0; i < r; i++) {
            int pos = (int) Math.pow(2, i);
            int parity = 0;

            for (int j = pos; j < code.length; j += 2 * pos) {
                for (int k = j; k < j + pos && k < code.length; k++) {
                    if (k != pos) {
                        parity ^= (code[k] == -1 ? 0 : code[k]);
                    }

                }
            }

            code[pos] = parity; // assign final parity value (0 or 1)

        }
    }

    // Display Final Hamming Code
    static void displayCode(int[] code)

    {
        System.out.println("Final Hamming Code");
        for (int i = code.length -1 ; i >= 1 ; i--) {
            System.out.print(code[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Hi, Javed!");
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Your Binary Data : ");
        String input = sc.nextLine();

        // Step no 01 : Convert Array....
        int[] data = toBinaryArray(input);

        // Stpe no 02 : FInd how many parity bits are required ....
        int r = findParityBits(input.length());
        System.out.println("Number of parity bits are required : " + r);

        // Step no 03 : Insert -1 placeholders on parity bits ....
        int[] code = insertParityBits(data, r);

        // Step no 04 : Calculate parity bits value ...
        calculateParity(code, r);

        // Step no 05 : Display Final Hamming Code....
        displayCode(code);

        System.out.println("This program is developed by Engr. Muhammad Javed");
    }
}