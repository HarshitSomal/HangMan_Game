package harshit.hangman_game;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {
int mpoints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        int points=getIntent().getIntExtra("POINTS", 0);
        String name=getIntent().getStringExtra("WNAME");
        TextView textt=(TextView)findViewById(R.id.word);
        textt.setText(name);
        TextView textpoints=(TextView)findViewById(R.id.textViewPoints);
        textpoints.setText(String.valueOf(points));
       mpoints=points;
    }


    public void SaveScore(View v){
        SharedPreferences preferences=getSharedPreferences("MYPREFERENCES",Context.MODE_PRIVATE);
        //Name x points \n Name2 y points
        EditText editText=(EditText)findViewById(R.id.editTextName);
        String name=editText.getText().toString();
        SharedPreferences.Editor editor=preferences.edit();
        String previousScores=preferences.getString("SCORES"," ");
        editor.putString("SCORES",name+" "+ mpoints +previousScores);
        editor.commit();
        finish();
    }
}
