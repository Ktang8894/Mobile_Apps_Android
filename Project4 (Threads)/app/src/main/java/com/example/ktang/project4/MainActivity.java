package com.example.ktang.project4;

import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static java.lang.Thread.sleep;


public class MainActivity extends AppCompatActivity {

    boolean gameComplete = false;
    int pieceCount = 6;

    public void endCheck (){
        Toast.makeText(this, "Player 1 Wins", Toast.LENGTH_LONG);
        int[] images = {R.id.p1, R.id.p2, R.id.p3, R.id.p4, R.id.p5, R.id.p6, R.id.p7, R.id.p8, R.id.p9};
        ImageView[] image = {null, null, null, null, null, null, null, null, null};
        for (int i=0; i<images.length; i++) {
            image[i] = (ImageView) findViewById(images[i]);
        }

        TextView status = (TextView) findViewById(R.id.status);
        pieceCount--;
        //Player 1 Win conditions (Black)
        if ((image[0].getTag() == "black" && image[1].getTag() == "black" && image[2].getTag() == "black")
                || (image[3].getTag() == "black" && image[4].getTag() == "black" && image[5].getTag() == "black")
                || (image[6].getTag() == "black" && image[7].getTag() == "black" && image[8].getTag() == "black")
                || (image[0].getTag() == "black" && image[3].getTag() == "black" && image[6].getTag() == "black")
                || (image[1].getTag() == "black" && image[4].getTag() == "black" && image[7].getTag() == "black")
                || (image[2].getTag() == "black" && image[5].getTag() == "black" && image[8].getTag() == "black")) {
            status.setText("Player 1 Wins!");
            gameComplete = true;
        }

        //Player 2 Win conditions (White)
        else if ((image[0].getTag() == "white" && image[1].getTag() == "white" && image[2].getTag() == "white")
                || (image[3].getTag() == "white" && image[4].getTag() == "white" && image[5].getTag() == "white")
                || (image[6].getTag() == "white" && image[7].getTag() == "white" && image[8].getTag() == "white")
                || (image[0].getTag() == "white" && image[3].getTag() == "white" && image[6].getTag() == "white")
                || (image[1].getTag() == "white" && image[4].getTag() == "white" && image[7].getTag() == "white")
                || (image[2].getTag() == "white" && image[5].getTag() == "white" && image[8].getTag() == "white")) {
            status.setText("Player 2 Wins!");
            gameComplete = true;
        }

        else if (image[0].getTag() != "" && image[1].getTag() != "" && image[2].getTag() != "" &&
                image[3].getTag() != "" && image[4].getTag() != "" && image[5].getTag() != "" &&
                image[6].getTag() != "" && image[7].getTag() != "" && image[8].getTag() != ""){
            status.setText("Game Tied");
            gameComplete = true;
        }

        else if (pieceCount == 0) {
            status.setText("Game Tied");
            gameComplete = true;
        }

    }

    int flag = 0; //Controls turns
    int counter = 0; //Player 1 Counter
    //Player 1 Handler
    Handler h = new Handler(){
        public void handleMessage(Message m) {
            int sentInt = m.getData().getInt("what");
            int[] images = {R.id.p1, R.id.p2, R.id.p3, R.id.p4, R.id.p5, R.id.p6, R.id.p7, R.id.p8, R.id.p9};
            ImageView image = (ImageView) findViewById(images[sentInt]);
            image.setImageResource(R.drawable.pblack);
            image.setTag("black");
            endCheck();
        };
    };

    //Random Number Generator - Player 1 uses RNG
    Random rNum = new Random();
    int randNum = rNum.nextInt(9);

    //Player 1 Runnable
    Runnable r = new Runnable(){
        @Override
        public void run() {
            int[] images = {R.id.p1, R.id.p2, R.id.p3, R.id.p4, R.id.p5, R.id.p6, R.id.p7, R.id.p8, R.id.p9};
            while(true) {
                if (gameComplete == false) {
                    try {
                        sleep(1000);
                    } catch (Exception e) {
                    }
                    if (flag == 0) {
                        ImageView image = (ImageView) findViewById(images[randNum]);
                        if (image.getDrawable() == null) {
                            Message msg = new Message();
                            Bundle b = new Bundle();
                            b.putInt("what", randNum);
                            msg.setData(b);
                            h.sendMessage(msg);
                            flag = 1;
                        } else {
                            //counter++;
                            randNum = rNum.nextInt(9);
                        }
                    }
                }
            }
        }
    };

    //Player 2 uses the next available space on it's right, then moves next row
    //Player 2 Handler
    Handler h2 = new Handler(){
        public void handleMessage(Message m) {
            int sentInt = m.getData().getInt("what");
            int[] images = {R.id.p1, R.id.p2, R.id.p3, R.id.p4, R.id.p5, R.id.p6, R.id.p7, R.id.p8, R.id.p9};
            ImageView image = (ImageView) findViewById(images[sentInt]);
            image.setImageResource(R.drawable.pwhite);
            image.setTag("white");
            endCheck();
        };
    };

    int counter2 = 0; //Player 2 Runnable
    //Player 2 runnable
    Runnable r2 = new Runnable(){
        @Override
        public void run() {
            int[] images = {R.id.p1, R.id.p2, R.id.p3, R.id.p4, R.id.p5, R.id.p6, R.id.p7, R.id.p8, R.id.p9};
            while(true){

                if (gameComplete == false) {
                    try {
                        sleep(1000);
                    } catch (Exception e) {
                    }
                    if (flag == 1 && counter2 < 9) {
                        ImageView image = (ImageView) findViewById(images[counter2]);
                        if (image.getDrawable() == null) {
                            Message msg = new Message();
                            Bundle b = new Bundle();
                            b.putInt("what", counter2);
                            msg.setData(b);
                            h2.sendMessage(msg);
                            flag = 0;
                        } else {
                            counter2++;
                        }
                    }
                }
            }
        }
    };

    //P1 and P2 threads
    Thread player1 = new Thread(r);
    Thread player2 = new Thread(r2);


    //Initialize Button, which will start a new game
    public void newGame(){
        Button play = (Button) findViewById(R.id.playButton);
        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Clear the board
                boardInit();
                try {
                    player1.start();
                    player2.start();
                }catch(Exception e) {
                    //Do nothing, thread started already
                }
                //Reset flag to start with player1, and counters
                h.removeCallbacksAndMessages(null);
                h2.removeCallbacksAndMessages(null);
                flag = 0;
                counter = 0;
                counter2 = 0;
                gameComplete = false;
                pieceCount = 6;
                TextView status = (TextView) findViewById(R.id.status);
                status.setText("Game In Progress");
            }
        });
    }


    //Set all temporary images as 0 (nothing), load button
    public void boardInit() {
        int[] images = {R.id.p1, R.id.p2, R.id.p3, R.id.p4, R.id.p5, R.id.p6, R.id.p7, R.id.p8, R.id.p9};
        int i;
        for (i=0; i<9;i++) {
            ImageView image = (ImageView) findViewById(images[i]);
            image.setImageResource(0);
            image.setTag("");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boardInit();
        newGame();
    }
}
