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

public class Game extends AppCompatActivity implements AdapterView.OnItemSelectedListener   {
  String mWord;
    int counter=0;
    String letter;
    int mGuessedLetter=0;
    int mPoints=0;
    Spinner spin;
    ArrayAdapter<CharSequence> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRandomword();
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

        // Showing selected spinner item

        letter=item;

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
            //To Do score one point
            mPoints++;
            //clear the creen
            //start the game
            clearScreen();
            setRandomword();
        }
    }
    public void setRandomword(){
        //To set a value to mWord
        String words="barb bass bear bird boar boto bull calf carp cavy clam colt coot crab crow deer degu dove duck eeve erne eyra fawn fish foal fowl frog gnat goat gull hake hare hart hawk hind hoki huia ibex ibis joey kite kiwi kudu lamb lark lice ling lion lobo loon lynx mara mare mice mink mite mole moth mule myna nene newt orca oryx oxen pika pike  pony puma rail  rhea roan rook  seal slug toad topi wolf worm wren zebu zubu";
        String[] arrayWords=words.split(" ");
        Log.d("MYLOG","The array length="+arrayWords.length);
        int randomnumber=(int)(Math.random() *arrayWords.length);
        String randomWord=arrayWords[randomnumber];
        mWord=randomWord;
        Log.d("MYLOG","Word is"+mWord);


    }
    public void clearScreen(){
        TextView textViewFailed =(TextView)findViewById(R.id.textView7);
        textViewFailed.setText("");
        mGuessedLetter=0;
        counter=0;
        LinearLayout layoutletter=(LinearLayout)findViewById(R.id.layoutLetters);
        for(int i=0;i<layoutletter.getChildCount();i++) {
            TextView currentTextView = (TextView) layoutletter.getChildAt(i);
            currentTextView.setText("_");
        }

        ImageView imageView=(ImageView)findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.hangdroid_0);
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
