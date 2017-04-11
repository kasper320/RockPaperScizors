package com.sdaacademy.kasperek.andrzej.rock_paper_scizors;

import android.graphics.drawable.Drawable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.paperButton)
    Button paperButton;
    private Drawable scissorsDrawable;
    private Drawable tieDrawable;
    private Drawable winDrawable;
    private Drawable lostDrawable;
    private Drawable paperDrawable;
    private Drawable rockDrawable;
    private ImageView myAction;
    private Action myActionEnum;
    private ImageView computerAction;
    private Random random;
    private Action computerActionEnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        random = new Random();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scissorsDrawable = ContextCompat.getDrawable(this, R.drawable.scissors);
        paperDrawable = ContextCompat.getDrawable(this, R.drawable.paper);
        rockDrawable = ContextCompat.getDrawable(this, R.drawable.rock);
        tieDrawable = ContextCompat.getDrawable(this, R.drawable.tie);
        winDrawable = ContextCompat.getDrawable(this, R.drawable.win);
        lostDrawable = ContextCompat.getDrawable(this, R.drawable.lost);


        myAction = (ImageView) findViewById(R.id.myPick);
        computerAction = (ImageView) findViewById(R.id.compPick);

    }

    public void myClick(View view) {
        switch (view.getId()) {
            case R.id.scissorButton:
                myAction.setImageDrawable(scissorsDrawable);
                myActionEnum = Action.CUT;
                break;
            case R.id.paperButton:
                myAction.setImageDrawable(paperDrawable);
                myActionEnum = Action.PAPER;
                break;
            case R.id.rockButton:
                myAction.setImageDrawable(rockDrawable);
                myActionEnum = Action.STONE;
                break;
        }

        computerActionEnum = getRandomAction();
        setComputerAction();
        checkTheWinner();
    }

    public void setComputerAction() {
        switch (getRandomAction()) {
            case CUT:
                computerAction.setImageDrawable(scissorsDrawable);
                computerActionEnum = Action.CUT;
                break;
            case STONE:
                computerAction.setImageDrawable(paperDrawable);
                computerActionEnum = Action.PAPER;
                break;
            case PAPER:
                computerAction.setImageDrawable(rockDrawable);
                computerActionEnum = Action.STONE;
                break;
        }
    }

    private Action getRandomAction() {
        int randomAction = random.nextInt(3);
        if (randomAction == 0)
            return Action.STONE;
        if (randomAction == 1)
            return Action.PAPER;
        return Action.CUT;
    }


    enum Action {
        STONE,
        PAPER,
        CUT;
    }

    private void checkTheWinner() {
        if (myActionEnum.equals(computerActionEnum)) {
            findViewById(R.id.resultImage).setBackground(tieDrawable);
        } else if (
                (myActionEnum.equals(Action.STONE) && computerActionEnum.equals(Action.CUT)) ||
                        (myActionEnum.equals(Action.CUT) && computerActionEnum.equals(Action.PAPER)) ||
                        (myActionEnum.equals(Action.PAPER) && computerActionEnum.equals(Action.STONE))) {
            findViewById(R.id.resultImage).setBackground(winDrawable);

        } else findViewById(R.id.resultImage).setBackground(lostDrawable);
    }
}

