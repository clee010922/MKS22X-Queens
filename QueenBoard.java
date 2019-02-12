public class QueenBoard {

  private int[][] board;

  public QueenBoard(int size) {
    board = new int[size][size]; //creates board with given size.
    clear(); //sets every value in 2d array as 0.
  }

  private boolean addQueen(int r, int c) {
    if (board[r][c] == 0) { //if not being threatend
      for (int i = 1; i < board.length - c; i++) { //loops through to check bounds.
        board[r][c+i] += 1; //adds a threat number horizontally.
        if (r-i >= 0) //checks upper right corner bound.
          board[r-i][c+i] += 1; //adds a threat number towards upper right.
        if (r+i < board.length) //checks bound for lower right corner.
          board[r+i][c+i] += 1; //adds a threat number towards lower right.
      }
      board[r][c] = -1; //if queen is placed, set the value as -1.
      return true; //true because queen can be added to given coordinates.
    }
    return false; //false if queen cannot be added.
  }

  private boolean removeQueen(int r, int c) {
    if (board[r][c] == -1) { //if it is a queen
      for (int i = 1; i < board.length - c; i++) { //loops through to check bounds.
        board[r][c+i] -= 1; //subract a threat number horizontally.
        if (r-i >= 0) //checks upper right corner bound.
          board[r-i][c+i] -= 1; //subtracts a threat nubmer towards upper right.
        if (r+i < board.length) //checks bound for lower right corner.
          board[r+i][c+i] -= 1; //subtracts a threat nu7mber towards lower right.
      }
      board[r][c] = 0; //removes queen and reset the given coordinates to 0.
      return true; //true if a given coordinates is a queen and can be removed.
    }
    return false; //false otherwise.
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
    String result = ""; //empty string.
    for (int r = 0; r < board.length; r++) { //loop through row.
      for (int c = 0; c < board[0].length; c++) { //loop through column.
        if (board[r][c] == -1) { //if specified value is a queen,
          result += "Q "; //then add Q to the result Stringt to show that it's a queen.
        }
        else result += "_ "; //if it is not a queen, then it's an underscore.
      }
      result += "\n"; //new line after every row.
    }
    return result; //returns the board as String.
  }



  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;

  *        true when the board is solveable, and leaves the board in a solved state

  *@throws IllegalStateException when the board starts with any non-zero value

  */
  public boolean solve(){
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        if (board[r][c] != 0)
          throw new IllegalStateException();
      }
    }
    if (solve(0))
      return true;
    else clear();
    return false;
  }

  private boolean solve(int col) {
    if (col >= board.length)
      return true;
    for (int r = 0; r < board.length; r++) {
      if (addQueen(r, col)) {
        if (solve(col+1)) {
          return true;
        }
        else removeQueen(r, col);
      }
    }
    return false;
  }

  public void clear() {
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        board[r][c] = 0;
      }
    }
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        if (board[r][c] != 0)
          throw new IllegalStateException();
      }
    }
    return countSolutions(0);
  }

  private int countSolutions(int col) {
    int numSolutions = 0;
    if (col >= board.length)
      return 1;
    for (int r = 0; r < board.length; r++) {
      if (addQueen(r, col)) {
        numSolutions += countSolutions(col+1);
        removeQueen(r, col);
      }
    }
    return numSolutions;
  }

  public static void main(String[] args) {
    QueenBoard trial = new QueenBoard(8);
    QueenBoard q = new QueenBoard(4);
    QueenBoard a = new QueenBoard(10);
    QueenBoard b = new QueenBoard(9);
    QueenBoard c = new QueenBoard(5);
    QueenBoard d = new QueenBoard(4);
    System.out.println(trial.countSolutions());
    System.out.println(q.countSolutions());
    System.out.println(a.countSolutions());
    System.out.println(b.countSolutions());
    System.out.println(c.countSolutions());
    c.solve();
    System.out.println(c);
    System.out.println(d.countSolutions());
    d.solve();
    System.out.println(d);
    System.out.println(trial);

  }


}
