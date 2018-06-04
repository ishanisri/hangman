package com.example.ishanisrivastava.hangman;

import android.app.Activity;
import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {
    StringBuilder guessed = new StringBuilder("");
    //StringBuilder wrong = new StringBuilder("");
    int wrongNo=0;
    int leastGuesses;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView wordbox = findViewById(R.id.wordbox);
        final Button button1=findViewById(R.id.button);
        //final ListView wrongList = findViewById(R.id.wrong);
        final TextView wrongList=findViewById(R.id.wrong);
        final EditText letter=findViewById(R.id.letter);
        final TextView scorebox=findViewById(R.id.scorebox);
        final TextView yourScore=findViewById(R.id.yourScore);
        yourScore.setText("Your wrong guesses : 0");

        final ImageView img=findViewById(R.id.imageView);
        final ArrayList wrong = new ArrayList();
        img.setImageResource(R.drawable.stage0);
        final String list[]={"mango","apple","banana","papaya","guava","pineapple","watermelon","kiwi","strawberry","cherry","raspberry","orange","grapes","apricot","blueberry"};
        Random rand = new Random();
        int n = rand.nextInt(15);
        final String word=list[n];
        final SharedPreferences settings = getSharedPreferences("PREFS", Activity.MODE_PRIVATE);
         leastGuesses= settings.getInt("score",9);
        scorebox.setText("Least guesses:"+String.valueOf(leastGuesses));





        for(int i=0;i<word.length();i++)
        {
            guessed.append(".");
        }
          wordbox.setText(guessed);


        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(letter.getText().length()>1)
                    Toast.makeText(getApplicationContext(),"just 1 letter allowed", LENGTH_LONG).show();
                else{
                    char alphabet=letter.getText().charAt(0);
                int c=0;

                for(int i=0;i<word.length();i++)
                {
                   if(word.charAt(i)==alphabet){

                           c++;
                           guessed.setCharAt(i,alphabet);



                   }


                }


                wordbox.setText(guessed) ;
                if(c==0)
                { wrongNo +=1;
                 wrong.add(alphabet);
                 wrongList.setText("");
                 for(int i=0;i<wrong.size();i++){
                     String s=wrong.get(i).toString()+" ";
                     wrongList.append(s);
                 }

                    switch(wrong.size())
                    {
                        case 1:
                            img.setImageResource(R.drawable.stage1);
                            break;
                        case 2:
                            img.setImageResource(R.drawable.stage2);
                            break;
                        case 3:
                            img.setImageResource(R.drawable.stage3);
                            break;
                        case 4:
                        img.setImageResource(R.drawable.stage4);
                        break;
                        case 5:
                            img.setImageResource(R.drawable.stage5);
                            break;
                        case 6:
                            img.setImageResource(R.drawable.stage6);
                            break;
                        case 7:
                            img.setImageResource(R.drawable.stage7);
                            break;
                        case 8:
                            img.setImageResource(R.drawable.stage8);
                            break;
                        case 9:
                            img.setImageResource(R.drawable.stage9);
                            break;

                 }




                    //ListAdapter adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, wrong);
                    //wrongList.setAdapter(adapter);
                }
                letter.setText("");
                yourScore.setText("Your wrong guesses :" + String.valueOf(wrongNo));

                 if(wrong.size()<9) {

                     if (word.equals(guessed.toString()) == true) {
                         wordbox.setText("You win!!");
                         letter.setEnabled(false);
                         letter.setInputType(InputType.TYPE_NULL);
                         letter.setEnabled(false);
                         button1.setEnabled(false);

                         if (wrongNo<leastGuesses)
                             leastGuesses = wrongNo;

                             //yourScore.setText("Your wrong guesses :" + String.valueOf(wrongNo));
                             //scorebox.setText("Least guesses :" + String.valueOf(leastGuesses));

                         scorebox.setText("least guesses :" + String.valueOf(leastGuesses));

                             //leastGuesses = wrongNo;
                             //yourScore.setText("Your Score :" + String.valueOf(wrongNo));
                             //scorebox.setText("least guesses :" + String.valueOf(leastGuesses));

                     }
                 }else{
                     letter.setEnabled(false);
                     letter.setInputType(InputType.TYPE_NULL);
                     wordbox.setText("You lose the game");
                     letter.setEnabled(false);
                     button1.setEnabled(false);
                 }

                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("score", leastGuesses);
                editor.commit();



            }}


            });


    }}

