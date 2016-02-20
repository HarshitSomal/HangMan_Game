package harshit.hangman_game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MultiPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);
    }
    public void startMultiGame(View v){
        EditText editText=(EditText)findViewById(R.id.editTextWord);
        String wordToGuess=editText.getText().toString();
        editText.setText("");
        Intent intent=new Intent(this,GameMulti.class);
        intent.putExtra("WORD_ID",wordToGuess.toUpperCase());
        startActivity(intent);
    }
}
