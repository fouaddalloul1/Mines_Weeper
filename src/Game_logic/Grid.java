package Game_logic;

import Screen.game;

import java.util.ArrayList;
import java.util.Random;


public class Grid implements java.io.Serializable{
    static public Cell[][] cells;
    public  static ArrayList<Cell> flagedCells = new ArrayList<Cell>();
    public static ArrayList<Cell> minedCells =  new ArrayList<Cell>();
    static int dx[] = {1,1,1,0,0,-1,-1,-1};
    static int dy[] = {1,0,-1,1,-1,1,0,-1};
    static public  int noOfBombs;
    static public int numOfRows;
    static public int numOfCol;
    static public int size;
    public static int score;


///////////////////////////////////////////
    static public void initCells(){
        for(int i = 0;i<numOfRows;i++)
        {
            for(int j = 0;j<numOfCol;j++) {
                cells[i][j] = new Cell(i, j);
                cells[i][j].setValue('#');
            }
        }
    }
    ///////////////////////////////constructers
    public Grid()
    {
         cells= new Cell[10][10];
        numOfCol =10;
        numOfRows=10;
        noOfBombs=14*(numOfCol*numOfRows)/100;//-(numOfRows+numOfCol)/4;
        score=0;//for count player score when floodFill
        for(int i = 0;i<numOfRows;i++)
        {
            for(int j = 0;j<numOfCol;j++) {
                cells[i][j] = new Cell(i, j);
                cells[i][j].setValue('#');
            }
        }
    }
    public  Grid(int numOfRowAndCol){
        cells =  new Cell[numOfRowAndCol][numOfRowAndCol];
        this.numOfRows=numOfRowAndCol;
        this.numOfCol=numOfRowAndCol;
        this.noOfBombs=14*(numOfCol*numOfRows)/100;
        initCells();
    }
    public  Grid(int numOfRowAndCol,int noOfBombs){
        cells  = new Cell[numOfRowAndCol][numOfRowAndCol];
        this.numOfRows=numOfRowAndCol;
        this.numOfCol=numOfRowAndCol;
        if(noOfBombs>numOfRowAndCol*numOfRowAndCol-numOfRowAndCol*2)
            noOfBombs=numOfRowAndCol*numOfRowAndCol-numOfRowAndCol*2;
        this.noOfBombs=14*(numOfCol*numOfRows)/100;
        initCells();
    }

    public Grid(int numOfRow,int numOfCol, int noOfBombs) {
        score=0;//for count player score when floodFill
        cells = new Cell[numOfRow][numOfCol];
        this.numOfRows=numOfRow;
        this.numOfCol=numOfCol;
        this.noOfBombs=noOfBombs;
        initCells();
    }


    ///////////// create init
    static  public  void  init(int numbOfRow,int numbOfCol, int numOfBombs){
        numOfRows=numbOfRow;
        numOfCol=numbOfCol;
        size = numOfRows*numOfCol;
        noOfBombs=numOfBombs;
        cells = new Cell[numOfRows][numOfCol];
        initCells();
    }

    static public boolean isValid(int x,int y)
    {
        return x>=0 && y >=0 && x<numOfRows && y<numOfCol;
    }
    static public void mineCnt()
    {
        int cnt ,nx,ny;
        for(int i = 0 ; i < numOfRows ; i++) {
            for(int j = 0 ; j < numOfCol ; j++) {
                cnt = 0;
                for(int k = 0;k<8;k++)
                {
                    nx = i  + dx[k];
                    ny = j + dy[k];
                    if(isValid(nx,ny) && cells[nx][ny].getValue() == 'B')
                        cnt++;
                }
                if(cells[i][j].getValue()!='B') {
                    if(cnt==0)cells[i][j].setValue(' ');
                    else
                    cells[i][j].setValue((char) (cnt + '0'));

                }
                }
        }
    }

    static public void floodFill(int x,int y)
    {
        if(cells[x][y].getValue() == 'B' || cells[x][y].isVisible())
            return;
        score++;
        cells[x][y].setVisible(true);//important before second condation
        if(!Main.console)
        game.btn[x][y].setOpen(true);
        if(cells[x][y].getValue() > '0' && cells[x][y].getValue() <'9')
            return ;
        for(int i = 0;i<8;i++)
        {
            if(isValid(x + dx[i],y+dy[i]))
                floodFill(x+dx[i],y+dy[i]);
        }
    }
    static public void bombInit() {
        Random r1 = new Random();

        for(int i = 0 ; i < noOfBombs ; i++) {
            int x = r1.nextInt(numOfRows);
            int y = r1.nextInt(numOfCol);
            if(cells[x][y].getValue() != 'B'&&(game.startx!=x||game.starty!=y)){
                cells[x][y].setValue('B');
//                minedCells.add(cells[x][y]);
            }
            else i--;

        }
        mineCnt();
    }

    static public void visibleAll()
    {
        for(int i = 0;i<numOfRows;i++)
        {
            for(int j = 0;j<numOfCol;j++){
                cells[i][j].setVisible(true);
//            game.btn[i][j].setOpen(true);
            }
        }


    }

    static public int unCheckedCells()
    {
        int cnt = 0;
        for(int i = 0;i<numOfRows;i++)
        {

            for(int j = 0;j<numOfCol;j++)
            {
                if(!cells[i][j].isVisible())
                    cnt++;
            }
        }
        return cnt;
    }

    static public int visableCells()
    {
        int cnt = 0;
        for(int i = 0;i<numOfRows;i++)
        {

            for(int j = 0;j<numOfCol;j++)
            {
                if(cells[i][j].isVisible()&&cells[i][j].getValue()!='B')
                    cnt++;
            }
        }
        return cnt;
    }

    public int flags(){
        int cnt = 0;
        for(int i = 0;i<numOfRows;i++)
        {

            for(int j = 0;j<numOfCol;j++)
            {
                if(!cells[i][j].isFlag())
                    cnt++;
            }
        }
        return cnt;
    }


    public int getNumOfRows() {
        return numOfRows;
    }

    public int getNumOfCol() {
        return numOfCol;
    }
    public void print()
    {
        System.out.println("    0   1   2   3   4   5   6   7   8   9");
        System.out.println("    -------------------------------------");
        for(int i = 0 ; i < Grid.numOfRows ; i++) {
            System.out.print(i + " : ");
            for(int j = 0 ; j < Grid.numOfCol; j++) {
                if(cells[i][j].isFlag())
                    System.out.print("P   ");
                else if(cells[i][j].isVisible())
                    System.out.print(cells[i][j].getValue() + "   ");
                else {
                    System.out.print("#   ");
                }
            }
            System.out.print( "\b\b: "+ i );

            System.out.println();
        }
        System.out.println("    -------------------------------------");
        System.out.println("    0   1   2   3   4   5   6   7   8   9");
    }

}
