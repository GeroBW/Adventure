import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class CSV {

    public CSV() {
        
    }

    public int[][] readFile(String path, int dx, int dy) {
        int tiles[][] = new int[dx][dy];

        try {
            Scanner in = new Scanner(new FileReader(path));
            int yIndex = 0;
            while(in.hasNext()) {
                String assetClasses = in.next();
                String[] splits = assetClasses.split(",");

                int xIndex = 0;

                for(String asset: splits) {
                    try {
                        tiles[xIndex][yIndex] = Integer.parseInt(asset);
                    } catch (NumberFormatException nfe) {
                        // Do something with 'nfe'
                    }
                    xIndex++;
                }
                yIndex++;
            }

        } catch (FileNotFoundException fnfe) {
            // Do something with 'fnfe'
        }

        return tiles;
    }

    public void debugFile(int[][] file) {
        for(int i = 0; i < file.length; i++) {
            for(int j = 0; j < file[0].length; j++) {
                System.out.println("[" + i + "]" + "[" + j + "]:" + file[i][j]);
            }
        }
    }
}
