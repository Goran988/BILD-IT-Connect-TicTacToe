package TicTacToe;

import java.util.Arrays;
import java.util.Scanner;

/**
 * In a game of tic-tac-toe, two players take turns marking an available cell in
 * a 3 * 3 grid with their respective tokens (either X or O). When one player
 * has placed three tokens in a horizontal, vertical, or diagonal row on the
 * grid, the game is over and that player has won. A draw (no winner) occurs
 * when all the cells on the grid have been filled with tokens and neither
 * player has achieved a win. Create a program for playing tic-tac-toe. The
 * program prompts two players to enter an X token and O token alternately.
 * Whenever a token is entered, the program redisplays the board on the console
 * and determines the status of the game (win, draw, or continue).
 *
 */
public class TicTacToe {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		char[][] matrix = new char[3][3];
		int player = 1;
		boolean win = false;
		// printing the matrix at the start of the program
		printMatrix(matrix);
		// loop runs until someone wins or is a draw
		while (!win) {
			// checking for win condition
			win = win(matrix);
			// players input X or O depends of who's turn it is
			inputSign(matrix, player);
			// printing matrix again
			printMatrix(matrix);
			// incrementing player(turn number)
			player++;
			// checking if there are free spaces left
			if (!spaceLeft(matrix) && !win) {
				System.out.println("Draw!");
				System.exit(1);
			}

		}
	}

	/**
	 * method that prints 3X3 matrix with lines that separate game tiles
	 * 
	 * @param a
	 *            3x3 matrix
	 */
	public static void printMatrix(char[][] a) {
		for (int i = 0; i < 3; i++) {
			System.out.println("-------------");
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(a[i][j] + " | ");
			}
			System.out.println();
		}
		System.out.println("-------------");
	}

	/**
	 * method that depending of the players turn allows player to set sign on a
	 * tile
	 * 
	 * @param a
	 *            - 3X3 matrix we use as game field
	 * @param player
	 *            - player turn so we can find who playes X or O
	 */
	public static void inputSign(char[][] a, int player) {
		Scanner input = new Scanner(System.in);
		int row;
		int column;
		if (player % 2 == 0) {
			// checking if row index is within range
			do {
				System.out.println("Enter a row (0, 1, or 2) for player X:");
				row = input.nextInt();
				// checking if the column index is within range
			} while (row > 2);
			do {
				System.out.println("Enter a column (0, 1, or 2) for player X:");
				column = input.nextInt();
			} while (column > 2);
			// checking if it's posible to set sign at chosen field
			if (isPosible(a, row, column)) {
				a[row][column] = 'X';
			} else {
				System.out.println("That field is taken, try again: ");
				inputSign(a, player);
			}
			// same proces for O player
		} else {
			do {
				System.out.println("Enter a row (0, 1, or 2) for player O:");
				row = input.nextInt();
			} while (row > 2);
			do {
				System.out.println("Enter a column (0, 1, or 2) for player O:");
				column = input.nextInt();
			} while (column > 2);

			if (isPosible(a, row, column)) {
				a[row][column] = 'O';
			} else {
				System.out.println("That field is taken, try again:");
				inputSign(a, player);
			}
		}
	}

	/**
	 * method that checks if chosen tile is free
	 * 
	 * @param a
	 *            - 3X3 matrix we use as game field
	 * @param row
	 *            - index for setting row
	 * @param column
	 *            - index for setting column
	 * @return true if posible to set sign at chosen tile
	 */
	public static boolean isPosible(char[][] a, int row, int column) {
		if (a[row][column] != 'X' && a[row][column] != 'O')
			return true;
		else
			return false;
	}

	/**
	 * method that checks for win condition
	 * 
	 * @param a
	 *            - 3X3 matrix we use as game field
	 * @return - true if win condition is met
	 */
	public static boolean win(char[][] a) {
		boolean res = false;
		for (int i = 0; i < 3; i++) {
			int countXRow = 0;
			int countORow = 0;
			int countXColum = 0;
			int countOColumn = 0;
			// loop that counts rows and columns for both X and O signs
			for (int j = 0; j < 3; j++) {
				if (a[i][j] == 'X') {
					countXRow++;
				}
				if (a[j][i] == 'X') {
					countXColum++;
				}
				if (a[i][j] == 'O') {
					countORow++;
				}
				if (a[j][i] == 'O') {
					countOColumn++;
				}
			}
			//if there were 3 X signs in either rows or columns X wins
			if (countXRow == 3 || countXColum == 3) {
				System.out.println("Player X wins!");
				return true;
			}
			//if there were 3 O signs in either rows or column O sign wins
			if (countORow == 3 || countOColumn == 3) {
				System.out.println("Player O wins!");
				return true;
			}
		}
		int countX = 0;
		int countO = 0;
		//loop that check "rising" diagonal for win
		for (int i = 0; i < 3; i++) {
			if (a[i][i] == 'X')
				countX++;
			if (a[i][i] == 'O')
				countO++;
			if (countX == 3) {
				System.out.println("Player X wins!");
				return true;
			}
			if (countO == 3) {
				System.out.println("Player O wins!");
				return true;
			}

		}
		countX = 0;
		countO = 0;
		int j = 2;
		//loop that checks "declining" diagonal for win
		for (int i = 0; i < 3; i++, j--) {
			if (a[i][j] == 'X')
				countX++;
			if (a[i][j] == 'O')
				countO++;
			if (countX == 3) {
				System.out.println("Player X wins!");
				return true;
			}
			if (countO == 3) {
				System.out.println("Player O wins!");
				return true;
			}
		}

		return res;
	}

	/**
	 * method that checks if there is any free space left in matrix
	 * 
	 * @param a
	 *            - 3X3 matrix we use as game field
	 * @return - true if there is space left
	 */
	public static boolean spaceLeft(char[][] a) {
		int count = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (a[i][j] != 'X' && a[i][j] != 'O') {
					count++;
				}
			}
		}
		if (count > 0)
			return true;
		else
			return false;
	}
}
