public class TicTacToeGame {
	TicTacToeBoard gameBoard;
	
	public TicTacToeGame() {
		gameBoard = new TicTacToeBoard();		
	}

	public int[] chooseSquare(String pieceType) {
		
		int[] moveLocation = new int[2];
		
		boolean requiresInput = true;
			
		if (pieceType.equals("X") || pieceType.equals("O"))
		{
			while (requiresInput)
			{
				System.out.println("Please enter the row and column numbers (separated by a space) for piece " + pieceType + ":");
				
				String[] coords = (StdIn.readLine().split(" "));
				
				if (coords.length == 2)
				{
				
					int rowNumber = Integer.parseInt(coords[0]) - 1;
					int columnNumber = Integer.parseInt(coords[1]) - 1;
					
					if (!gameBoard.isSquareFull(rowNumber, columnNumber) && gameBoard.move(pieceType, rowNumber, columnNumber))
					{						
						moveLocation[0] = rowNumber + 1;
						moveLocation[1] = columnNumber + 1;	
						requiresInput = false;
					}
					else
					{
						System.out.println("Invalid move, you will have to re-enter the move for this piece.");
						String boardState = gameBoard.toString();
						System.out.println(boardState);
					}
				}
				else
				{
					System.out.println("Invalid move, you will have to re-enter the move for this piece.");
					String boardState = gameBoard.toString();
					System.out.println(boardState);
				}
				
			}			
		}
		else
		{
			System.out.println("Invalid piece type, please enter a valid piece type to make a move");
			String boardState = gameBoard.toString();
			System.out.println(boardState);
			return null;
		}
		
		return moveLocation;	
	}
	
	public void play() {		
		System.out.println("Started new game of Tic-tac-toe:");
		String boardState = gameBoard.toString();
		System.out.println(boardState);
		
		String currentPieceType = "X";		
		
		boolean gameFinished = false;
		
		String winner;
		
		while (!gameFinished)
		{			
			int[] moveLocation = chooseSquare(currentPieceType);
			
			if (currentPieceType.equals("X"))
			{
				currentPieceType = "O";
			}
			else if (currentPieceType.equals("O"))
			{
				currentPieceType = "X";
			}
			
			String move = "";
			
			for (int index = 0; index < moveLocation.length; index++)
			{
				move += moveLocation[index] + " ";				
			}
			
			System.out.println(move);
			
			boardState = gameBoard.toString();
			System.out.println(boardState);
			
			gameFinished = gameBoard.isBoardFull();
			
			winner = gameBoard.winner();
			
			if (winner != null)
			{
				gameFinished = true;
			}
		}				
		
		winner = gameBoard.winner();
		
		if (winner != null)
		{			
			System.out.println(winner);
		}
		else
		{
			System.out.println("The game is a draw");
		}
	}	
}
