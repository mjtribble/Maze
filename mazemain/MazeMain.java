/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazemain;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author melodytribble
 */
public class MazeMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        char[][] mazeArray = new char[12][12];

        try {
            Scanner sc = new Scanner(new File(args[0]));
            int i = 0;
            while (sc.hasNextLine()) {

                char[] in = sc.nextLine().replaceAll(" ", "").toCharArray();

                for (int j = 0; j < in.length; j++) {

                    mazeArray[i] = in;
                }

                i++;
            }

        } catch (IOException e1) {
            System.out.println("Error during reading/writing");
            System.out.println(e1);
        }

//        char[][] mazeArray = {  {'#','#','#','#','#','#','#','#','#','#','#','#'},
//                                {'#','.','.','.','#','.','.','.','.','.','.','#'},
//                                {'O','.','#','.','#','.','#','#','#','#','.','#'},
//                                {'#','#','#','.','#','.','.','.','.','#','.','#'},
//                                {'#','.','.','.','.','#','#','#','.','#','.','#'},
//                                {'#','#','#','#','.','#','F','#','.','#','.','#'},
//                                {'#','.','.','#','.','#','.','#','.','#','.','#'},
//                                {'#','#','.','#','.','#','.','#','.','#','.','#'},
//                                {'#','.','.','.','.','.','.','.','.','#','.','#'},
//                                {'#','#','#','#','#','#','.','#','#','#','.','#'},
//                                {'#','.','.','.','.','.','.','#','.','.','.','#'},
//                                {'#','#','#','#','#','#','#','#','#','#','#','#'}
//                                };
        MazeSetup myMaze = new MazeSetup(mazeArray);
        myMaze.print(mazeArray);
        myMaze.run(mazeArray, 0, 2, 0, 3);
        System.out.println(myMaze.run(mazeArray, 0, 2, 0, 3));
    }
}
