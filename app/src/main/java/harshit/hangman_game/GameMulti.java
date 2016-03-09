package harshit.hangman_game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GameMulti extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
  String mWord;
    int counter=0;
    int mGuessedLetter=0;
    String letter;
    int mPoints=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_game);
         mWord=getIntent().getStringExtra("WORD_ID");
        Log.d("MYLOG", mWord);
        creatTextViews(mWord);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[]items={"_","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        // Creating adapter for spinner
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
// attaching data adapter to spinner
        spinner.setAdapter(adapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();


        letter=item;

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }





 private void creatTextViews(String word) {
        LinearLayout layoutLetter=(LinearLayout)findViewById(R.id.layoutLetters);
        for(int i=0 ;i < word.length();i++){
            TextView newTextView= (TextView) getLayoutInflater().inflate(R.layout.display,null);
            layoutLetter.addView(newTextView);


        }
    }

    public void introduceLetter(View v){

     if(letter.length()>0){
         checkLetter(letter.toUpperCase());
     }
        else{
         Toast.makeText(this,"Please Introduce letter",Toast.LENGTH_LONG).show();
     }
    }

    public void checkLetter(String introducedLetter ){
        boolean letterGuessed=false;
        char charIntroduced=introducedLetter.charAt(0);
          for(int i=0;i<mWord.length();i++){
              char charFromtheWord=mWord.charAt(i);
              Log.d("My Log","There letter we are checking is"+charFromtheWord);
              if(charFromtheWord==charIntroduced){
                  Log.d("MyLog", "There was one match");
                  letterGuessed=true;
                  showLettersAtIndex(i,charIntroduced);
                  mGuessedLetter++;
              }

          }

        if(letterGuessed==false){

            letterFailed(Character.toString(charIntroduced));
        }
        if(mGuessedLetter==mWord.length()){
            mPoints++;
            TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

            winnerMessage.setText("You won!");

            LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

            layout.setVisibility(View.VISIBLE);

        }
    }
    public void playAgain(View view) {

        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);
        finish();
    }
      public void letterFailed(String letterFailed) {
          counter++;
          ImageView imageView = (ImageView) findViewById(R.id.imageView);

          TextView textViewFailed=(TextView)findViewById(R.id.textView7);
          String previousFailed=textViewFailed.getText().toString();
          textViewFailed.setText(previousFailed+letterFailed);
          if (counter == 1) {
              imageView.setImageResource(R.drawable.hangdroid_1);
          } else if (counter == 2) {
              imageView.setImageResource(R.drawable.hangdroid_2);
          } else if (counter == 3) {
              imageView.setImageResource(R.drawable.hangdroid_3);
          }    else if(counter==4){
                 imageView.setImageResource(R.drawable.hangdroid_4);
               } else if(counter==5){
              imageView.setImageResource(R.drawable.hangdroid_5);
          }

          else if(counter==6){
                 //Game over
              Intent gameover=new Intent(this,GameOver.class);
              gameover.putExtra("POINTS",mPoints);
              gameover.putExtra("WNAME",mWord);
              startActivity(gameover);
              finish();
                  }  }
    public void showLettersAtIndex(int position,char letterGuessed){
        LinearLayout layoutLetter=(LinearLayout)findViewById(R.id.layoutLetters);
        TextView textView=(TextView)layoutLetter.getChildAt(position);
        textView.setText(Character.toString(letterGuessed));
    }

}
