package mad.madaso39;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Game_Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);
        ((TextView)findViewById(R.id.score)).setText(getIntent().getStringExtra("TotalScore") + " Score");
    }

    public void playAgain(View view){
        Intent play = new Intent(this,Game_content.class);
        startActivity(play);
    }

    public void backToMain(View view){
        Intent main = new Intent(this,game.class);
        startActivity(main);
    }
}