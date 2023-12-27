package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    boolean playerOneActive;
    TextView player1Score, player2Score, playerstatus;
    private Button[] buttons = new Button[9];
    Button Reset;
    Button PlayAgain;
    int playerOneScoreCount;

    int playerTwoScoreCount;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6},

            {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int rounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1Score = findViewById(R.id.score_Player1);
        player2Score = findViewById(R.id.score_Player2);
        playerstatus = findViewById(R.id.textStatus);
        Reset = findViewById(R.id.btn_reset);
        PlayAgain = findViewById(R.id.btn_play_again);

        buttons[0] = findViewById(R.id.btn0);

        buttons[1] = findViewById(R.id.btn1);

        buttons[2] = findViewById(R.id.btn2);

        buttons[3] = findViewById(R.id.btn3);

        buttons[4] = findViewById(R.id.btn4);

        buttons[5] = findViewById(R.id.btn5);

        buttons[6] = findViewById(R.id.btn6);

        buttons[7] = findViewById(R.id.btn7);

        buttons[8] = findViewById(R.id.btn8);

        for (int i = 0; i < buttons.length; i++) {

            buttons[i].setOnClickListener(this);

        }


        playerOneScoreCount = 0;

        playerTwoScoreCount = 0;

        playerOneActive = true;

        rounds = 0;


    }

    @Override
    public void onClick(View view) {
        if (!(((Button) view).getText().toString().equals(""))) {
            return;
        } else if (checkWinner()) {
            return;
        }
        String buttonId = view.getResources().getResourceEntryName(view.getId());
        int gameStatePointer = Integer.parseInt(buttonId.substring(buttonId.length() - 1, buttonId.length()));

        if (playerOneActive == true) {
            ((Button) view).setText("X");
            // ((Button) view).setTextColor(Color.parseColor(String.valueOf(R.color.black)));
            ((Button) view).setTextColor(Color.parseColor("#FFFFFFFF"));
            gameState[gameStatePointer] = 0;


        } else {

            ((Button) view).setText("0");
             ((Button) view).setTextColor(Color.parseColor("#cc1234"));

            gameState[gameStatePointer] = 1;
        }
        rounds++;

        if (checkWinner()) {
            if (playerOneActive) {
                playerOneScoreCount++;
                updatePlayerScore();
                playerstatus.setText("Player 1 has won!");
                Toast.makeText(this, "Player 1 has won", Toast.LENGTH_SHORT).show();

            } else {
                playerTwoScoreCount++;
                updatePlayerScore();
                Toast.makeText(this, "Player 2 has won", Toast.LENGTH_SHORT).show();
                playerstatus.setText("Player 2 has won!");
            }
        } else if (rounds == 9) {
            playerstatus.setText("Draw!");
            Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
        } else {
            playerOneActive = !playerOneActive;
        }

        PlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
            }
        });
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                updatePlayerScore();
                playAgain();

            }
        });

    }

    private void updatePlayerScore() {
        player1Score.setText(Integer.toString(playerOneScoreCount));
        player2Score.setText(Integer.toString(playerTwoScoreCount));
    }

    private void playAgain() {
        rounds = 0;
        playerOneActive = true;
        for (int i = 0; i < buttons.length; i++) {
            gameState[i] = 2;
            buttons[i].setText("");
        }
       // playerstatus.setText("Status:");
    }

    private boolean checkWinner() {
        boolean winnerResult = false;
        for (int[] winningPost : winningPositions) {
            if (gameState[winningPost[0]] == gameState[winningPost[1]] && gameState[winningPost[1]] == gameState[winningPost[2]] && gameState[winningPost[0]] != 2) {
                winnerResult = true;
            }
        }
        return winnerResult;
    }
}