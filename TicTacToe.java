import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	//global variables
	static char userChoice = ' ';
	static char compChoice = ' ';
	
	public static void main(String[] args) {
		char[][] grid = {{' ', ' ', ' '}, 
				  		 {' ', ' ', ' '}, 
				  		 {' ', ' ', ' '}};
		Scanner scanner = new Scanner(System.in);
		gameIntro();
		userChoice(scanner);
		
		while(true) {
			userTurn(grid, scanner);
			if(gameOver(grid, userChoice)) {
				System.out.println("\nGame over!\n");
				break;
			}
			compTurn(grid);
			if(gameOver(grid, compChoice)) {
				System.out.println("\nGame over!\n");
				break;
			}
		}
	}
	
	public static void gameIntro() {
		System.out.println("Welcome to TicTacToe.\n\nThis is a single-player version where you play against a randomized computer.\n");
		System.out.println("This game is played on a 3x3 grid and the input for your moves must be an integer between 1-9. Each integer corresponds to a particular box on the grid as shown.\n");
		System.out.println(1 + "|" + 2 + "|" + 3);
		System.out.println("-+-+-");
		System.out.println(4 + "|" + 5 + "|" + 6);
		System.out.println("-+-+-");
		System.out.println(7 + "|" + 8 + "|" + 9 + "\n");
		System.out.println("To get started, choose your symbol.\n");
	}
	
	public static void printBoard(char[][] grid){
		
		for(int i = 0; i<=2; i++) {
			System.out.println(grid[i][0] + "|" + grid[i][1] + "|" + grid[i][2]);
			if(i<2)System.out.println("-+-+-");
		}
	}
	
	public static void userTurn(char[][] grid, Scanner scanner) {
		System.out.println("\nWhere would you like to play? (1-9)\n");
		printBoard(grid);
		String userInput = scanner.nextLine();
		
		boolean valid = false;
		while(!valid) {
			for(int i = 1; i<=9; i++) {
				if(Integer.toString(i).equals(userInput) && isBoxFree(grid, Integer.parseInt(userInput))) {
					placeMove(grid, Integer.parseInt(userInput), userChoice);
					valid = true;
				}
			}
			if(!valid) {
				System.out.println("\nPlease enter a valid choice: \n");
				printBoard(grid);
				userInput = scanner.nextLine();
			}
		}
		printBoard(grid);
		
	}
	
	public static void userChoice(Scanner scanner) {
		System.out.println("Select your character choice (enter X or O)");
		String choice = scanner.nextLine();
		boolean valid = false;
		while(!valid) {
			if(choice.toUpperCase().equals("X")) {
				userChoice = 'X';
				compChoice = 'O';
				valid = true;
			}
			else if(choice.toUpperCase().equals("O")) {
				userChoice = 'O';
				compChoice = 'X';
				valid = true;
			}
			else {
				System.out.println("Please enter a valid choice: ");
				valid = false;
				choice = scanner.nextLine();
			}

		}
	}

	public static void compTurn(char[][] grid) {
		Random rand = new Random();
		int position = 0;
		while(true) {
			int compPlay = rand.nextInt(9) + 1;
			if(isBoxFree(grid, compPlay)) {
				position += compPlay;
				break;
			}
		}
		System.out.format("\nComputer moves at position %d.\n\n", position);
		placeMove(grid, position, compChoice);
		printBoard(grid);
	}
	
	public static boolean isBoxFree(char[][] grid, int position) {
	
		switch(position) {
			case 1:
				return (grid[0][0] == ' '); 
			case 2:
				return (grid[0][1] == ' '); 
			case 3:
				return (grid[0][2] == ' '); 
			case 4:
				return (grid[1][0] == ' '); 
			case 5:
				return (grid[1][1] == ' '); 
			case 6:
				return (grid[1][2] == ' '); 
			case 7:
				return (grid[2][0] == ' '); 
			case 8:
				return (grid[2][1] == ' '); 
			case 9:
				return (grid[2][2] == ' '); 
			default:
				return false;
			}
		}

	public static void placeMove(char[][] grid, int position, char choice) {
		switch(position) {
		case 1:
			grid[0][0] = choice;
			break;
		case 2:
			grid[0][1] = choice;
			break;
		case 3:
			grid[0][2] = choice;
			break;
		case 4:
			grid[1][0] = choice;
			break;
		case 5:
			grid[1][1] = choice;
			break;
		case 6:
			grid[1][2] = choice;
			break;
		case 7:
			grid[2][0] = choice;
			break;
		case 8:
			grid[2][1] = choice;
			break;
		case 9:
			grid[2][2] = choice;
			break;
		}
	}
	
	public static boolean gameOver(char[][] grid, char choice) {
		//basically if rows are same or columns are same or diagonals are same and whichever user does it first. A tie is also a possibility where the board is full but the previous conditions arent met.
		boolean gameOver = false;
			//winning conditions
			
		if((grid[0][0] == choice && grid[0][1] == choice && grid[0][2] == choice)|| //rows
		   (grid[1][0] == choice && grid[1][1] == choice && grid[1][2] == choice)||
		   (grid[2][0] == choice && grid[2][1] == choice && grid[2][2] == choice)||
	
		   (grid[0][0] == choice && grid[1][0] == choice && grid[2][0] == choice)|| //columns
		   (grid[0][1] == choice && grid[1][1] == choice && grid[2][1] == choice)||
		   (grid[0][2] == choice && grid[1][2] == choice && grid[2][2] == choice)||
		   
		   (grid[0][0] == choice && grid[1][1] == choice && grid[2][2] == choice)|| //diagonals
		   (grid[0][2] == choice && grid[1][1] == choice && grid[2][0] == choice))
		   {
					System.out.println("\n"+ choice + " wins!");
					gameOver = true;
			}
		else if (isBoardFull(grid)){
			gameOver = true;
			System.out.println("\nIt's a tie!");
		}
		
		return gameOver;
	}
	
	public static boolean isBoardFull(char[][] grid) {
		for(int i = 0; i<grid.length; i++) {
			for(int j = 0; j<grid[i].length; j++) {
				if(grid[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

}
