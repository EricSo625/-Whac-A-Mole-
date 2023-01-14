package mad.madaso39;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    int direction = 1; // for direction of AnimationDrawable, 1 normal, -1 reverse
    MediaPlayer myMus = null; // a field of MediaPlayer
    @Override
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
        setContentView(R.layout.activity_main);
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
        myMus = MediaPlayer.create(this, R.raw.background_music);//sound file “bs” in raw folder
        myMus.setLooping(true); // set loop-playing mode
        initRotateBgImg(); // call to init rotating bg image
        makeMoveThread();
    }
        // Init image with rotation animation
    public void initRotateBgImg(){
        // animate rotation of background image
        RotateAnimation animR = new RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animR.setInterpolator(new LinearInterpolator());
        animR.setRepeatCount(Animation.INFINITE);
        animR.setDuration(60000);
        // Simple scaling the background image view
        ImageView imgV = ((ImageView) findViewById(R.id.bg_imgView));
        imgV.setScaleX(2); imgV.setScaleY(2);
        // Start animating (rotating) the background image view
        imgV.startAnimation(animR);
    }
    void makeMoveThread(){ // for making a thread to continuously move the image object
        final int DEF_SLEEP_GAP = 1000; // mini-sec
        final int DEF_MAX = 200; // max movement factor
        final int DEF_BASE = - DEF_MAX/2; // base movement factor
        new Thread(new Runnable() { // create a new thread to animate, and then start it.
            @Override
            public void run(){
                while (!Thread.currentThread().isInterrupted()){
                    try {
                        Thread.sleep(DEF_SLEEP_GAP); // interval in ms
                        // move (change location of) the image
                        final ImageButton imgB=(ImageButton) findViewById(R.id.mouse_imgButton);
                        if (imgB==null) break;
                        imgB.post(new Runnable() { // for handling UI with thread in Android
                            public void run() {
                                // animate motion
                                int diffX = (DEF_BASE + (int) (Math.random() * DEF_MAX));
                                int diffY = (DEF_BASE + (int) (Math.random() * DEF_MAX));
                                imgB.setX(imgB.getX() + diffX);
                                imgB.setY(imgB.getY() + diffY);
                                // animate changing transparency
                                imgB.setImageAlpha(128+(int) (Math.random()*128));
                            }
                        });
                    } catch (InterruptedException ie) {}
                }
            }
        }).start();
    }
    public void goToAboutMe(View view){
        Intent aboutMe = new Intent(this,aboutMe.class);
        startActivity(aboutMe);
    }
    public void goToAboutTheApp(View view){
        Intent aboutApp = new Intent(this,aboutTheApp.class);
        startActivity(aboutApp);
    }
    public void goToTheGame(View view){
        Intent game = new Intent(this,game.class);
        startActivity(game);
    }
}