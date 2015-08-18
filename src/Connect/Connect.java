package Connect;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Connect four is a two-player board game in which the players alternately drop
 * colored disks into a seven-column, six-row vertically suspended grid, as
 * shown below. The objective of the game is to connect four same-colored disks
 * in a row, a column, or a diagonal before your opponent can do likewise. The
 * program prompts two players to drop a red or yellow disk alternately. In the
 * preceding figure, the red disk is shown in a dark color and the yellow in a
 * light color. Whenever a disk is dropped, the program redisplays the board on
 * the console and determines the status of the game (win, draw, or continue).
 */
public class Connect {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		// char 2D array that will store our "disks"
		char[][] matrix = new char[6][7];
		// if odd player letter is Y
		int player = 1;
		// printing out the matrix
		printTable(matrix);
		int index;
		boolean win = false;
		// loop that iterates until player wins
		while (!win) {
			// if the player number is even Red player is on
			if (player % 2 == 0) {
				System.out.println("Red player chose column 0 - 6");
				index = input.nextInt();
				if (!indexOk(index)) {
					while (!indexOk(index)) {
						System.out.println("Red player chose column 0 - 6");
						index = input.nextInt();
					}
				}
				// if dropDisc method fails first time it's invoked again else
				// player is incremented
				if (dropDisc(matrix, index, player % 2 == 0)) {
					player++;
				} else {
					if (dropDisc(matrix, input.nextInt(), player % 2 == 0)) {
						player++;
					}
				}
				//
			} else {
				//same proces as previous loop for other player
				System.out.println("Yellow player chose column 0 - 6");
				index = input.nextInt();
				if (!indexOk(index)) {
					while (!indexOk(index)) {
						System.out.println("Yellow player chose column 0 - 6");
						index = input.nextInt();
					}
				}
				if (dropDisc(matrix, index, player % 2 == 0)) {
					player++;
				} else {
					if (dropDisc(matrix, input.nextInt(), player % 2 == 0)) {
						player++;
					}
				}
			}
			//printing table again after droping disk
			printTable(matrix);
			//checking for win condition
			win = win(matrix);
			//checking for draw condition
			if (!spaceLeft(matrix)) {
				System.out.println("Game is draw");
				System.exit(1);
			}
		}

	}

	/**
	 * method that checks if index that player is trying to enter is within
	 * allowed range
	 * 
	 * @param index
	 *            - users choice
	 * @return true if it's ok and false if it's not
	 */
	public static boolean indexOk(int index) {
		if (index >= 0 && index <= 6)
			return true;
		else
			return false;
	}

	/**
	 * method that prints out table
	 * 
	 * @param a
	 *            2D array of chars
	 */
	public static void printTable(char[][] a) {
		// printing out with nested loop and adding "|" lines
		for (int i = 0; i < a.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j] + " | ");
			}
			System.out.println();
		}
		System.out.println("-----------------------------");
		System.out.println("  0   1   2   3   4   5   6  ");

	}

	/**
	 * method that checks if the method managed to drop the disk
	 * 
	 * @param a
	 *            - 2D array of chars
	 * @param index
	 *            - column index we're trying to drop the disk
	 * @param even
	 *            - determines number color
	 * @return true if disk is droped and false if it's not
	 */
	public static boolean dropDisc(char[][] a, int index, boolean even) {
		boolean success = false;
		if (posible(a, index)) {
			if (even) {
				// loop is used to find first clear space in the column
				for (int i = 5; i >= 0; i--) {
					if (a[i][index] != 'R' && a[i][index] != 'Y') {
						a[i][index] = 'R';
						success = true;
						break;
					}
				}
			} else {
				// loop is used to find first clear space in the column
				for (int i = 5; i >= 0; i--) {
					if (a[i][index] != 'R' && a[i][index] != 'Y') {
						a[i][index] = 'Y';
						success = true;
						break;
					}

				}

			}
		}
		return success;

	}

	/**
	 * method that checks if it's posible to drop the disk into the column
	 * 
	 * @param a
	 *            - 2D array of chars
	 * @param index
	 *            - index of column
	 * @return true if it's posible and false if it's not
	 */
	public static boolean posible(char[][] a, int index) {
		int count = 0;
		// loop counts free spaces in the column
		for (int i = 5; i >= 0; i--) {
			if (a[i][index] == 'R' && a[i][index] == 'Y') {
				count++;
			}
		}
		// if count is >=6 there are no free spaces in the column
		if (count >= 6)
			return false;
		else
			return true;

	}

	/**
	 * method that determines if there is a winner in the game by searching 4
	 * connected disks
	 * 
	 * @param a
	 *            - 2D array of chars
	 * @return true if there is a winner and false if there isn't
	 */
	public static boolean win(char[][] a) {
		boolean res = false;
		// loop counts disks of the same color in rows
		for (int i = 0; i < a.length; i++) {
			int countRed = 0;
			int countYellow = 0;
			// if subsequent disks are of the same color counter is incremented
			for (int j = 0; j < a[0].length; j++) {
				if (a[i][j] == 'R') {
					countRed++;
					// else it's reseted to 0
				} else {
					countRed = 0;
				}
				if (a[i][j] == 'Y') {
					countYellow++;
				} else {
					countYellow = 0;
				}

			}
			// if counter reaches 4 player of that color wins
			if (countRed >= 4) {
				System.out.println("Red player wins!");
				return true;
			}
			if (countYellow >= 4) {
				System.out.println("Yellow player wins!");
				return true;
			}
		}
		// same proces but this counts disks in columns
		for (int i = 0; i < 7; i++) {
			int countRed = 0;
			int countYellow = 0;
			for (int j = 0; j < 6; j++) {
				if (a[j][i] == 'R') {
					countRed++;
				} else {
					countRed = 0;
				}
				if (countRed >= 4) {
					System.out.println("Red player wins!");
					return true;
				}
				if (a[j][i] == 'Y') {
					countYellow++;
				} else {
					countYellow = 0;
				}
				if (countYellow >= 4) {
					System.out.println("Yellow player wins!");
					return true;
				}

			}
		}
		res = diagonalWin(a);
		return res;
	}

	/**
	 * method that checks for winning condition for diagonals
	 * 
	 * @param a
	 *            - 2D char array
	 * @return true if there is a winner and false if there isn't
	 */
	public static boolean diagonalWin(char[][] a) {
		boolean res = false;
		// loop that checks four spaces diagonaly from current tile to top-right
		for (int i = 0; i < 4; i++) {
			for (int j = 5; j >= 3; j--) {
				if (a[j][i] == 'R' && a[j - 1][i + 1] == 'R'
						&& a[j - 2][i + 2] == 'R' && a[j - 3][i + 3] == 'R') {
					System.out.println("Red player wins!");
					return true;
				} else if (a[j][i] == 'Y' && a[j - 1][i + 1] == 'Y'
						&& a[j - 2][i + 2] == 'Y' && a[j - 3][i + 3] == 'Y') {
					System.out.println("Yellow player wins!");
					return true;
				}
			}
		}
		// loop that checks four spaces diagonaly from current tile to top-left
		for (int i = 5; i >= 3; i--) {
			for (int j = 5; j >= 3; j--) {
				if (a[j][i] == 'R' && a[j - 1][i - 1] == 'R'
						&& a[j - 2][i - 2] == 'R' && a[j - 3][i - 3] == 'R') {
					System.out.println("Red player wins!");
					return true;
				} else if (a[j][i] == 'Y' && a[j - 1][i - 1] == 'Y'
						&& a[j - 2][i - 2] == 'Y' && a[j - 3][i - 3] == 'Y') {
					System.out.println("Yellow player wins!");
					return true;
				}
			}
		}
		return res;
	}

	/**
	 * method that checks if there are any spaces left and determines if the
	 * game is draw
	 * 
	 * @param a
	 *            - 2D array of chars
	 * @return true if there are no more free tiles
	 */
	public static boolean spaceLeft(char[][] a) {
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] != 'Y' && a[i][j] != 'R') {
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
