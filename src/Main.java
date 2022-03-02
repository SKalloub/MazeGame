import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static ArrayList<boolean[][]> paths = new ArrayList<>();
    static boolean [][] Output = {{false, false, false, false},{false, false, false, false},{false, false, false, false},{false, false, false, false}};
    public static void main(String[] args) {
        boolean [][] arr = {{true, true, false, false},{true, true, false, true},{false, true, false, false},{true, true, true, true}};




    }
    public static void solveMaze(int[][] arr) {
        Output = new boolean[arr.length][arr.length];
        for (int i = 0; i < Output.length; i++) {
            for (int j = 0; j < Output[i].length; j++) {
                Output[i][j] = false;
            }
        }
        int starti = 0;
        int startj = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j]==2) {
                    starti = i;
                    startj = j;
                }
            }
        }

        paths = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        solveMaze(arr,Output,starti,startj);

    }

    private static boolean solveMaze(int[][] arr, boolean[][] output,int i, int j) {
        System.out.println(i+","+j);
        if (i<0 || i>= arr.length || j<0 || j>= arr.length ) // if it's out of range
            return false;
        if (arr[i][j]==3) {
            System.out.println("....");
            output[i][j] = true;
        paths.add(output);
            return true;

    }


        if (arr[i][j]==0) // if it's not blocked
            return false;


            if (output[i][j]) // prevent cycle
                return false;

                output[i][j] = true;
                boolean[] [] output2,output3,output4;
                output2 = new boolean[output.length][output.length];
                output3 = new boolean[output.length][output.length];
                output4 = new boolean[output.length][output.length];


        for (int k = 0; k < output.length; k++) {
            for (int l = 0; l <output.length ; l++) {
                output2[k][l] = output[k][l];
                output3[k][l] = output[k][l];
                output4[k][l] = output[k][l];

            }
        }
        boolean up = solveMaze(arr,output,i-1,j);
        boolean right = solveMaze(arr,output2,i,j+1);
        boolean down = solveMaze(arr,output3,i+1,j);
        boolean left = solveMaze(arr,output4,i,j-1);
                if (up || right || down || left)
                    return true;

            output[i][j] = false;

    return false;
    }
}
