import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Tabuleiros {
    public char[][] reader() throws IOException{
        Scanner in = new Scanner(System.in);

        System.out.print("Nome do arquivo de teste (colocar .txt no final): ");
        String file = in.next();
        Scanner scanner = new Scanner(new File(file));
        List<String> lines = new ArrayList<String>();
        char table[][];
        int size = 0;

        scanner.useDelimiter("\n");

        while(scanner.hasNext()){
            String next = scanner.next();
            lines.add(next);
            size++;
        }
        scanner.close();
        in.close();

        table = new char[size][size];
        for (int i = 0; i<size; i++){
            for (int j = 0; j<size; j++){
                String line = lines.get(i);
                table[i][j] = line.charAt(j);
            }
        }

        System.out.println(size);
        
        return table;
    }

    // public static void main(String[] args) throws IOException{
    //     char test[][] = reader();

    //     for (int i = 0; i<test.length; i++){
    //         for (int j = 0; j<test.length; j++){
    //             System.out.print(test[i][j]);
    //         }
    //         System.out.print("\n");
    //     }
    // }


}