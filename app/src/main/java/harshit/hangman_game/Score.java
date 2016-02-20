package harshit.hangman_game;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Score extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        SharedPreferences preferences=getSharedPreferences("MYPREFERENCES",MODE_PRIVATE);
        String scores=preferences.getString("SCORES","NO SCORES");
        TextView textView=(TextView)findViewById(R.id.textS);
        textView.setText(scores);
    }
}
