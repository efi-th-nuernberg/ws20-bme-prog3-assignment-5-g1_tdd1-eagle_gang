public class Life implements ILife {
  private int width;
  private int height;
  private int[][] board;
  
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
  public boolean isAlive(int x, int y) {
    if(x < 0 || x >= width){
      return false;
    }
    if(y < 0 || y >= height){
      return false;
    }
    if(this.board[x][y] == 1){
      return true;
    }
    return false;
  }

  @Override
  public ILife nextGeneration() {
    int[][] tempBoard = new int[width][height];
    for(int y = 0; y < height; y++){
      for (int x = 0; x < width; x++){
        int aliveNeighbours = countAliveNeughbours(x, y);
        if(isAlive(x, y) == false && aliveNeighbours == 3){
          tempBoard[x][y] = 1;
        }
        else if(isAlive(x, y) == true && aliveNeighbours < 2){
          tempBoard[x][y] = 0;
        }
        else if(isAlive(x, y) == true && (aliveNeighbours == 2 || aliveNeighbours == 3)){
          tempBoard[x][y] = 1;
        }
        else if (isAlive(x, y) == true && (aliveNeighbours > 3)){
          tempBoard[x][y] = 0;
        }
      }
    }
    this.board = tempBoard;
    return null;
  }

  public int countAliveNeughbours(int x, int y){
    int counter = 0;
		if (isAlive(x-1, y-1))
			counter++;
		if (isAlive(x, y-1))
			counter++;
		if (isAlive(x+1, y-1))
			counter++;
		if (isAlive(x-1, y))
			counter++;
		if (isAlive(x+1, y))
			counter++;
		if (isAlive(x-1, y+1))
			counter++;
		if (isAlive(x, y+1))
			counter++;
		if (isAlive(x+1, y+1))
			counter++;
    return counter;
  }
}