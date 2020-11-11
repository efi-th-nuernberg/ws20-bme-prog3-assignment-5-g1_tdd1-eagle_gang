public class Life implements ILife {
  int width;
  int height;
  int[][] board;
  
  public static void main(String[] args) {
  }

  public Life(int width, int height) {
    this.width = width;
    this.height = height;
    this.board = new int[width][height];
  }

  // Just for testing the Graphical output of the Game of Life
  public void printBoard(){
    for(int y = 0; y < height; y++){
      String line = "|";
      for(int x = 0; x < width; x++){
        if(this.board[x][y] == 0){
          line += ".";
        }else{
          line += "*";
        }
      }
      line += "|";
      System.out.println(line);
    }
    System.out.println("\n");
  }


  @Override
  public void nukeAll() {
    for(int y = 0; y < height; y++){
      for(int x = 0; x < width; x++){
        this.board[x][y] = 0;
      }
    }
  }

  @Override
  public void setAlive(int x, int y) {
    this.board[x][y] = 1;
  }

  @Override
  public void setDead(int x, int y) {
    this.board[x][y] = 0;
  }

  @Override
  public int isAlive(int x, int y) {
    if(x < 0 || x >= width){
      return 0;
    }
    if(y < 0 || y >= height){
      return 0;
    }
    return this.board[x][y];
  }

  @Override
  public ILife nextGeneration() {
    int[][] tempBoard = new int[width][height];
    for(int y = 0; y < height; y++){
      for (int x = 0; x < width; x++){
        int aliveNeighbours = countAliveNeughbours(x, y);
        if(isAlive(x, y) == 0 && aliveNeighbours == 3){
          tempBoard[x][y] = 1;
        }
        else if(isAlive(x, y) == 1 && aliveNeighbours < 2){
          tempBoard[x][y] = 0;
        }
        else if(isAlive(x, y) == 1 && (aliveNeighbours == 2 || aliveNeighbours == 3)){
          tempBoard[x][y] = 1;
        }
        else if (isAlive(x, y) == 1 && (aliveNeighbours > 3)){
          tempBoard[x][y] = 0;
        }
      }
    }
    this.board = tempBoard;
    return null;
  }

  public int countAliveNeughbours(int x, int y){
    int count = 0;
    count += isAlive(x-1,y-1);
    count += isAlive(x,y-1);
    count += isAlive(x+1,y-1);
    count += isAlive(x-1,y);
    count += isAlive(x+1,y);
    count += isAlive(x-1,y+1);
    count += isAlive(x,y+1);
    count += isAlive(x+1,y+1);
    return count;
  }
}