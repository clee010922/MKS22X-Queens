public class QueenBoard {

  private int[][] board;
  private int boardSize;

  public QueenBoard(int size) {
    board = new int[size][size];
    for (int r = 0; r < size; r++) {
      for (int c = 0; c < size; c++) {
        board[r][c] = 0;
      }
    }
    boardSize = size;
  }

  private boolean addQueen(int r, int c) {
    if (board[r][c] == 0) {
      for (int i = 0; i < boardSize; i++) {
        board[r][i] += 1;
        board[i][c] += 1;
      }
      /*
      int row = r;
      int col = c;
      for (int i = 0; row-i != 0 || col-i != 0; i++) {
        row -= i;
        col -= i;
      }
      for (int i = 0; row+i < size || col+i < size; i++) {
        board[row][col] += 1;
      }
      int row2 = r;
      int col2 = c;
      for (int i = 0; row-i != 0 || col+i != 0; i++) {
        row -= i;
        col -= i;
      }
      */
      board[r][c] = -1;
      return true;
    }
    return false;
  }

  private boolean removeQueen(int r, int c) {
    if (board[r][c] == -1) {
      board[r][c] = 0;
      return true;
    }
    return false;
  }

  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  *There are spaces between each symbol:
  *"""_ _ Q _
  *Q _ _ _

  *_ _ _ Q

  *_ Q _ _"""
  *(pythonic string notation for clarity,
  *excludes the character up to the *)
  */
  public String toString(){
    String result = "";
    for (int r = 0; r < boardSize; r++) {
      for (int c = 0; c < size; c++) {
        board[r][c] = 0;

  }



  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;

  *        true when the board is solveable, and leaves the board in a solved state

  *@throws IllegalStateException when the board starts with any non-zero value

  */
  public boolean solve(){

  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){

  }


}
