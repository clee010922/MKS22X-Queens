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
  public boolean solve(){ //wrapper function for solving the board.
    for (int r = 0; r < board.length; r++) { //loop through row.
      for (int c = 0; c < board[0].length; c++) { //loop through column.
        if (board[r][c] != 0) //if board starts with any non-zero values,
          throw new IllegalStateException(); //throw an error.
      }
    }
    if (board.length == 0)
      return true;
    if (solve(0)) //uses the helperfunction.
      return true; //returns true if it is solveable.
    else clear(); //if it is not solveable, clear the board.
    return false; //not solveable.
  }

  private boolean solve(int col) { //helper function for solve. Taken mostly from the pseudocode on Mr. K's website.
    if (col >= board.length) //checking bound.
      return true;
    for (int r = 0; r < board.length; r++) { //loop through row.
      if (addQueen(r, col)) { //add a queen to a specified place.
        if (solve(col+1)) { //recursive part. If you can solve with adding queen at next column,
          return true; //return true;
        }
        else removeQueen(r, col); //if not possible, then remove queen and try again.
      }
    }
    return false; //return false if it is not solveable.
  }

  public void clear() {
    for (int r = 0; r < board.length; r++) { //loop through row.
      for (int c = 0; c < board[0].length; c++) { //loop through column.
        board[r][c] = 0; //sets every value of the board to 0.
      }
    }
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    for (int r = 0; r < board.length; r++) { //loop through row.
      for (int c = 0; c < board[0].length; c++) { //loop through column.
        if (board[r][c] != 0) //if board starts with any non-zero values,
          throw new IllegalStateException(); //throw an error.
      }
    }
    if (board.length == 0)
      return 1;
    int numSolutions = countSolutions(0); //helper function.
    clear(); //clears the board.
    return numSolutions; //number of solutiosn to the board.
  }

  private int countSolutions(int col) {
    int numSolutions = 0; //number of solutions.
    if (col >= board.length) //checking bound.
      return 1;
    for (int r = 0; r < board.length; r++) { //check through row.
      if (addQueen(r, col)) { //if you can add a queen to the index,
        numSolutions += countSolutions(col+1); //recursive part: increment the numSolutions everytime there is a solution.
        removeQueen(r, col); //remove the queen to try every possible cases.
      }
    }
    return numSolutions; //returns the number of solutions to the given board.
  }

  //testcase must be a valid index of your input/output array
public static void runTest(int i){
  QueenBoard b;
  int[]tests =   {1,2,3,4,5,8};
  int[]answers = {1,0,0,2,10,92};
  if(i >= 0 && i < tests.length ){
    int size = tests[i];
    int correct = answers[i];
    b = new QueenBoard(size);
    int ans  = b.countSolutions();

    if(correct==ans){
      System.out.println("PASS board size: "+tests[i]+" "+ans);
    }else{
      System.out.println("FAIL board size: "+tests[i]+" "+ans+" vs "+correct);
    }
  }
}

  public static void main(String[] args) {
    /*
    QueenBoard zero = new QueenBoard(0);
    QueenBoard one = new QueenBoard(1);
    QueenBoard two = new QueenBoard(2);
    QueenBoard three = new QueenBoard(3);
    QueenBoard four = new QueenBoard(4);
    QueenBoard five = new QueenBoard(5);
    QueenBoard six = new QueenBoard(6);
    QueenBoard seven = new QueenBoard(7);
    System.out.println(zero.countSolutions()); //1
    System.out.println(one.countSolutions()); //1
    System.out.println(two.countSolutions()); //0
    System.out.println(three.countSolutions()); //0
    System.out.println(four.countSolutions()); //1
    System.out.println(five.countSolutions()); //10
    System.out.println(six.countSolutions()); //4
    System.out.println(seven.countSolutions()); //40
    */
    runTest(0);
    runTest(1);
    runTest(2);
    runTest(3);
    runTest(4);
    runTest(5);
  }


}
