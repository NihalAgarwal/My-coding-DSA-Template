package Helper;

import java.util.Arrays;

public class Print2dArray {
    public static void print2DArrayInt(int[][] res){
        for(int[] i: res) System.out.println(Arrays.toString(i));
        System.out.println();
    }
    public static void print2DArrayChar(char[][] res){
        for(char[] i: res) System.out.println(Arrays.toString(i));
        System.out.println();
    }
    public static void print2DArrayBool(boolean[][] res){
        for(boolean[] i: res) System.out.println(Arrays.toString(i));
        System.out.println();
    }
    public static <T> void print2DArrayObj(T[][] res){
        for(T[] i: res) System.out.println(Arrays.toString(i));
        System.out.println();
    }
}
