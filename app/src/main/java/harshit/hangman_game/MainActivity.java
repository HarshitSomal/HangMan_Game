package harshit.hangman_game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Single(View v){
        Intent i=new Intent(this,Game.class);
        startActivity(i);
    }
    public void Multi(View v){
        Intent p=new Intent(this,MultiPlayer.class);
        startActivity(p);
    }
    public void Score(View v){
        Intent p1=new Intent(this,Score.class);
        startActivity(p1);
    }
}
