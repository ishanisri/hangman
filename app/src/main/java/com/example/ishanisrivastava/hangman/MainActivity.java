package com.example.ishanisrivastava.hangman;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    StringBuilder guessed = new StringBuilder("");
    //StringBuilder wrong = new StringBuilder("");
    int wrongNo=0;
    int bestScore=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView wordbox = findViewById(R.id.wordbox);
        Button button1=findViewById(R.id.button);
        //final ListView wrongList = findViewById(R.id.wrong);
        final TextView wrongList=findViewById(R.id.wrong);
        final EditText letter=findViewById(R.id.letter);
        final TextView scorebox=findViewById(R.id.scorebox);
        final ArrayList wrong = new ArrayList();
        final String list[]={"mango","apple","banana","papaya","guava","pineapple","watermelon","kiwi","strawberry","cherry"};
        Random rand = new Random();
        int n = rand.nextInt(10);
        final String word=list[n];
        final SharedPreferences settings = getSharedPreferences("PREFS", 0);
        String best= settings.getString("score", "");
        if(best.equals("")==false) {
            bestScore = Integer.parseInt(best);
        }


        for(int i=0;i<word.length();i++)
        {
            guessed.append(".");
        }
          wordbox.setText(guessed);


        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
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
                 for(int i=0;i<wrong.size();i++){
                     String s=wrong.get(i).toString()+" ";
                     wrongList.append(s);


                 }
                    //ListAdapter adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, wrong);
                    //wrongList.setAdapter(adapter);
                }


                if(word.equals(guessed))
                    letter.setText("You win!!");
                if(wrongNo<=bestScore)
                    scorebox.setText("Best score :"+String.valueOf(bestScore));
                else {
                    bestScore=wrongNo;
                    scorebox.setText("Best score :" + String.valueOf(bestScore));
                }

                SharedPreferences.Editor editor = settings.edit();
                editor.putString("score", String.valueOf(bestScore));
                editor.commit();


            }


            });


    }}

