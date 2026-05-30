package Screen;

import Game_logic.Grid;

import java.util.Scanner;

public class Console {
    public void start() {
        Scanner in = new Scanner(System.in);

        Grid g = new Grid(10,10,14);


        g.bombInit();
        g.print();
        while (true) {
            System.out.println("Enter Row and Col");
            int x = in.nextInt();
            int y = in.nextInt();
            System.out.println("Enter 1 for flag \n2 for normal :");
            int z = in.nextInt();

            if (z == 1) {
                g.cells[x][y].setFlag(g.cells[x][y].isFlag() ? false : true);
                g.print();
                continue;
            }
            if (g.cells[x][y].getValue() == 'B') {
                g.visibleAll();
                g.print();
                System.out.println("Game over!");
                break;
            }
            if (!g.cells[x][y].isFlag())
                g.floodFill(x, y);
            g.print();
            if (g.unCheckedCells() == Grid.noOfBombs) {
                System.out.println("Congratulations, you won!");
                break;
            }
        }
    }

}
