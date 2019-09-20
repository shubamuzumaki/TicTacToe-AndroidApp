package com.example.tictacgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
{
    TicTacToe ttt;
    EditText player1Input,player2Input;
    Player player1,player2;
    Player current;
    TextView curPlayerTextView;

    public void onClickPlayAgain(View view)
    {
        ttt.resetGame();
        LinearLayout playAgainLayout = (LinearLayout)findViewById(R.id.playAgainLayout);
        playAgainLayout.setVisibility(View.INVISIBLE);

        //resetting board
        androidx.gridlayout.widget.GridLayout boardGridLayout = (androidx.gridlayout.widget.GridLayout)findViewById(R.id.boardGridLayout);
        for(int i=0; i<boardGridLayout.getChildCount(); ++i)
            ((ImageView)boardGridLayout.getChildAt(i)).setImageResource(0);

        Log.i("info","Game Resetted to original state");
    }


    public void onClickBoard(View view)
    {
        String[] tag = view.getTag().toString().split(":");
        int row = Integer.parseInt(tag[0]);
        int col = Integer.parseInt(tag[1]);
        if(!ttt.play(row,col,current))
            return;

        ImageView imageView = (ImageView)view;
        //animate image
        imageView.setTranslationY(-1000f);
        imageView.setImageResource(current.getSrcImageId());
        imageView.animate().translationYBy(1000f).rotationBy(720).setDuration(150);

        current = (current==player1)?player2:player1;

        curPlayerTextView.setText(current.getName().toUpperCase());

        //check for gamewin or Draw
        if(ttt.isFinished())
        {
            Player winner = null;
            LinearLayout playAgainLayout = (LinearLayout)findViewById(R.id.playAgainLayout);
            playAgainLayout.setVisibility(View.VISIBLE);

            String winnerInfo ="";

            Log.i("info","Game Finished");
            if(ttt.isDraw())
                winnerInfo = "GAME DRAW";
            else if((winner=ttt.getWinner()) != null)
                winnerInfo = "Winner is: "+winner.getName();

            ((TextView)findViewById(R.id.winnerMessageTextView)).setText(winnerInfo);
        }
    }//onClick Board:Scene2

    //Transtion to Scene 2
    public void onClickLetsPlay(View view)
    {
        String player1Name = player1Input.getText().toString();
        String player2Name = player2Input.getText().toString();


        if(player1Name.isEmpty() || player2Name.isEmpty())
        {
            Toast.makeText(this,"Bhau naam to dena padhega... \n    Please",Toast.LENGTH_LONG).show();
            return;
        }
        if(player1Name.equalsIgnoreCase(player2Name))
        {
            Toast.makeText(this,"Naam ek jaisa...\n Ye kaise chalega bhai",Toast.LENGTH_LONG).show();
            return;
        }

        player1 = new Player(player1Name,R.drawable.cross1);
        player2 = new Player(player2Name,R.drawable.zero1);

        ttt = new TicTacToe(player1,player2);
        current = player1;

        setContentView(R.layout.activity_main);

        //setting scene2
        curPlayerTextView = (TextView)findViewById(R.id.currentPlayerTextView);
        curPlayerTextView.setText(current.getName().toUpperCase());
        ((TextView)findViewById(R.id.player1LogoTextView)).setText(player1.getName().toUpperCase());
        ((ImageView)findViewById(R.id.player1LogoImageView)).setImageResource(player1.getSrcImageId());

        ((TextView)findViewById(R.id.player2LogoTextView)).setText(player2.getName().toUpperCase());
        ((ImageView)findViewById(R.id.player2LogoImageView)).setImageResource(player2.getSrcImageId());

    }//onClickLetsPlay:Scene1->Scene2

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);

        //cache resources
        player1Input = (EditText)findViewById(R.id.player1EditText);
        player2Input = (EditText)findViewById(R.id.player2EditText);
    }
}
