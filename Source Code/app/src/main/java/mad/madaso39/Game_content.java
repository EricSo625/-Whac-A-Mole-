package mad.madaso39;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game_content extends AppCompatActivity {
    int buttonCount = 0;
    int score = 0;
    MediaPlayer myMus = null; // a field of MediaPlayer
    boolean button1Pressed = false;
    boolean button2Pressed = false;
    boolean button3Pressed = false;
    boolean button4Pressed = false;
    boolean button5Pressed = false;
    boolean button6Pressed = false;

    protected void onResume(){ // callback method, when interacting with user
        super.onResume(); // always call superclass
        if (myMus != null) myMus.start(); // start playing
    }
    @Override
    protected void onPause(){ // callback method, inactive: when no interacting
        super.onPause(); // always call superclass
        if (myMus != null) myMus.pause(); // pause playing
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_content);
        buttonInitialization();
        gamecontrol();
        Switch soundSw = (Switch) findViewById(R.id.swSound);
        soundSw.setChecked(true); // set the switch as Checked (ON) initially
        soundSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) { // is enabled
                    if (myMus != null) myMus.start(); // start playing
                } else { // is disabled
                    if (myMus != null) myMus.pause(); // pause playing
                }
            }
        });
        myMus = MediaPlayer.create(this, R.raw.game_music);//sound file “bs” in raw folder
        myMus.setLooping(true); // set loop-playing mode
    }
    public void gamecontrol(){
        int[] buttonToBePressedL = new int[30];
        for(int i=0 ; i<30 ;i++){
            Random randNum = new Random();
            buttonToBePressedL[i] = randNum.nextInt(6)+1;
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                buttonInitialization();
                buttonControl(buttonToBePressedL[buttonCount]);
                buttonCount = buttonCount + 1;
                String TimeRemain = (30-buttonCount) + " Seconds Left";
                ((TextView)findViewById(R.id.Time)).setText(TimeRemain);
                String scoreString = score + " Score";
                ((TextView)findViewById(R.id.result)).setText(scoreString);
                if (buttonCount == 30){
                    timer.cancel();
                    goToResultPage();
                }
            }
        }, 1000, 1000);

    }

    public void goToResultPage(){
        Intent ResultPage = new Intent(this,Game_Result.class);
        String scoreString = score + "";
        ResultPage.putExtra("TotalScore",scoreString);
        startActivity(ResultPage);
    }
    public void buttonControl(int buttonNum){
        switch (buttonNum){
            case 1:
                Button button1 = ((Button)findViewById(R.id.button1));
                button1.setText("Press Me");
                button1.setTextColor(Color.BLACK);
                button1.setBackgroundColor(Color.CYAN);
                button1Pressed = true;
                break;
            case 2:
                Button button2 = ((Button)findViewById(R.id.button2));
                button2.setText("Press Me");
                button2.setTextColor(Color.BLACK);
                button2.setBackgroundColor(Color.CYAN);
                button2Pressed = true;
                break;
            case 3:
                Button button3 = ((Button)findViewById(R.id.button3));
                button3.setText("Press Me");
                button3.setTextColor(Color.BLACK);
                button3.setBackgroundColor(Color.CYAN);
                button3Pressed = true;
                break;
            case 4:
                Button button4 = ((Button)findViewById(R.id.button4));
                button4.setText("Press Me");
                button4.setTextColor(Color.BLACK);
                button4.setBackgroundColor(Color.CYAN);
                button4Pressed = true;
                break;
            case 5:
                Button button5 = ((Button)findViewById(R.id.button5));
                button5.setText("Press Me");
                button5.setTextColor(Color.BLACK);
                button5.setBackgroundColor(Color.CYAN);
                button5Pressed = true;
                break;
            case 6:
                Button button6 = ((Button)findViewById(R.id.button6));
                button6.setText("Press Me");
                button6.setTextColor(Color.BLACK);
                button6.setBackgroundColor(Color.CYAN);
                button6Pressed = true;
                break;
        }
    }
    public void buttonInitialization(){
        Button button1 = ((Button)findViewById(R.id.button1));
        button1.setBackgroundColor(Color.RED);
        button1.setText("");
        Button button2 = ((Button)findViewById(R.id.button2));
        button2.setBackgroundColor(Color.RED);
        button2.setText("");
        Button button3 = ((Button)findViewById(R.id.button3));
        button3.setBackgroundColor(Color.RED);
        button3.setText("");
        Button button4 = ((Button)findViewById(R.id.button4));
        button4.setBackgroundColor(Color.RED);
        button4.setText("");
        Button button5 = ((Button)findViewById(R.id.button5));
        button5.setBackgroundColor(Color.RED);
        button5.setText("");
        Button button6 = ((Button)findViewById(R.id.button6));
        button6.setBackgroundColor(Color.RED);
        button6.setText("");
        button1Pressed = false;
        button2Pressed = false;
        button3Pressed = false;
        button4Pressed = false;
        button5Pressed = false;
        button6Pressed = false;
    }
    public void cancelButton1(View view){
        if (button1Pressed == true) {
            Button button1 = ((Button) findViewById(R.id.button1));
            button1.setText("");
            button1.setBackgroundColor(Color.RED);
            score = score + 10;
            button1Pressed = false;
        }
    }
    public void cancelButton2(View view){
        if (button2Pressed == true) {
            Button button2 = ((Button) findViewById(R.id.button2));
            button2.setText("");
            button2.setBackgroundColor(Color.RED);
            score = score + 10;
            button2Pressed = false;
        }
    }
    public void cancelButton3(View view){
        if (button3Pressed == true) {
            Button button3 = ((Button) findViewById(R.id.button3));
            button3.setText("");
            button3.setBackgroundColor(Color.RED);
            score = score + 10;
            button3Pressed = false;
        }
    }
    public void cancelButton4(View view){
        if (button4Pressed == true) {
            Button button4 = ((Button) findViewById(R.id.button4));
            button4.setText("");
            button4.setBackgroundColor(Color.RED);
            score = score + 10;
            button4Pressed = false;
        }
    }
    public void cancelButton5(View view){
        if (button5Pressed == true) {
            Button button5 = ((Button) findViewById(R.id.button5));
            button5.setText("");
            button5.setBackgroundColor(Color.RED);
            score = score + 10;
            button5Pressed = false;
        }
    }
    public void cancelButton6(View view){
        if (button6Pressed == true) {
            Button button6 = ((Button) findViewById(R.id.button6));
            button6.setText("");
            button6.setBackgroundColor(Color.RED);
            score = score + 10;
            button6Pressed = false;
        }
    }
}