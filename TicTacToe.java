package basicJavaTutorial;

import java.util.Scanner;

public class TicTacToe {
	
	static char[][] board;
	static Scanner read;
	static char playersTurn;
	static String move, moveDetails[];
	static boolean isCorrect = true;

	public static void main(String[] args) {
		startGame();
	}
	
	public static void startGame() {
		board = new char[3][3];
		read = new Scanner(System.in);
		playersTurn = 'O';
		fillBoard();
		printBoard();
		makeMove();
	}
	
	public static void fillBoard() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = '-';
			}
		}
	}

	public static  char isOver() {
		char piece;
		//Line victory
		for(char[] line : board) {
			if(line[0] != '-') piece = line[0];
			else continue;
			if(line[1] == piece && line[2] == piece) return piece;
		}
		//Column victory
		for(int i = 0; i < 3; i++) {
			if(board[0][i] != '-') piece = board[0][i];
			else continue;
			if(board[1][i] == piece && board[2][i] == piece) return piece;
		}
		//Cross victory
		if(board[0][0] != '-') {
			piece = board[0][0];
			if(board[1][1] == piece && board[2][2] == piece) return piece;
		}
		if(board[0][2] != '-') {
			piece = board[0][2];
			if(board[1][1] == piece && board[2][0] == piece) return piece;
		}
		
		//Are empties
		for(char[] line : board) {
			for(char x : line) {
				if(x == '-') return '-';
			}
		}
		
		//If there ix no winner and all spots are taken it;s tie
		return 'T';
	}

	public static void makeMove() {
		String coordinates;
		int x, y;
		do {
			isCorrect = true;
			System.out.println("Place " + playersTurn);
			move = read.nextLine();
			moveDetails = move.split(" ");
			if(moveDetails.length != 2) {
				isCorrect = false;
				System.out.println("Wrong length of move");
				continue;
			}
			for(int i = 0; i < 2; i++) {
				coordinates = moveDetails[1].trim();
				if(!coordinates.equals("0") && !coordinates.equals("1") && !coordinates.equals("2")) {
					System.out.println("Wrong coordinates in move");
					isCorrect = false;
				}
			}
			if(!isCorrect) continue;
			x = Integer.parseInt(moveDetails[0].trim());
			y = Integer.parseInt(moveDetails[1].trim());
			isCorrect = isFree(x, y);
		}while(!isCorrect);
		
		x = Integer.parseInt(moveDetails[0].trim());
		y = Integer.parseInt(moveDetails[1].trim());
		placePiece(x, y);
		printBoard();
		
		switch (isOver()) {
		case 'O':
			System.out.println("O Wins!");
			break;
		case 'X':
			System.out.println("X Wins!");
			break;
		case 'T':
			System.out.println("Tie! Startring new game");
			startGame();
			break;
		case '-':
			if(playersTurn == 'O') playersTurn = 'X';
			else playersTurn = 'O';
			makeMove();
			break;
		}
			
	}
	
	public static void placePiece(int x, int y) {
		if(board[x][y] != '-') System.out.println("ERROR!!! TROED TO OVERRIDE A PLACE");
		else board[x][y] = playersTurn;
	}
	
	public static void printBoard() {
		for(char line[] : board) {
			for(char x : line) {
				System.out.print(x);
			}
			System.out.println();
		}
	}

	public static boolean isFree(int x, int y) {
		if(board[x][y] == '-') return true;
		else {
			System.out.println("This place is taken");
			return false;
		}
	}
}
