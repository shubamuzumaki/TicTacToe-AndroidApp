package com.example.tictacgame;

public class TicTacToe
{
    Player player1,player2;
    int[][] board = new int[3][3];
    boolean isDraw = false,isFinished = false;
    Player winner = null;

    public TicTacToe(Player player1,Player player2)
    {
        this.player1 = player1;
        this.player2 = player2;
//        current = player1;
    }

    public boolean isDraw()
    {
        return isDraw;
    }

    public boolean isFinished()
    {
        return isFinished;
    }

    public void resetGame()
    {
        isDraw = isFinished = false;
//        current = player1;
        winner = null;

        //@Rahul Sir
        board = new int[3][3];
    }

    public Player getWinner()
    {
        return winner;
    }
    //return whether it is a valid move or not

    public boolean play(int row,int col,Player player)
    {
        if(isFinished) return false;
        //invalid row & Col
        if(row>=3 || row<0 || col>=3 || col<0) return false;

        //board is not empty at that location
        if(board[row][col] != 0) return false;

        board[row][col] = player.getId();
        checkWin(player);
        return true;
    }

    private void checkWin(Player player)
    {
        //check draw
        boolean draw = true;

        for(int i=0; i<3 && draw; ++i)
            for(int j=0; j<3; ++j)
                if(board[i][j] == 0)
                    draw = false;

        if(draw)
        {
            isDraw = isFinished = true;
            return;
        }

        int curId = player.getId();
        boolean won = false;
        //check rows
        for(int i=0; i<3 && !won; ++i)
            if(curId == board[i][0] &&
               curId == board[i][1] &&
               curId == board[i][2])
                won = true;

        //check cols
        for(int i=0; i<3 && !won; ++i)
            if(curId == board[0][i] &&
               curId == board[1][i] &&
               curId == board[2][i])
                won = true;

        //check diagonal
        if(curId == board[0][0] &&
           curId == board[1][1] &&
           curId == board[2][2])
            won =true;

        if(curId == board[0][2] &&
           curId == board[1][1] &&
           curId == board[2][0])
            won =true;

        if(won)
        {
            winner = player;
            isFinished = true;
        }
    }
}
