package com.example.kmitchell.meal_plannerpro;

import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;



import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    Button mButton;
    EditText mEdit;
    EditText mEdit2;
    EditText mEdit3;
    TextView mText;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creates instance of database
        mFirebaseInstance = FirebaseDatabase.getInstance();
        //get reference to user node
        mFirebaseDatabase = mFirebaseInstance.getReference("meal-planner-pro-33c1")

        Button btn = (Button)findViewById(R.id.button3);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {



                mEdit   = (EditText)findViewById(R.id.editText);
                mEdit2   = (EditText)findViewById(R.id.editText2);
                mEdit3   = (EditText)findViewById(R.id.editText3);

                String input1 = mEdit.getText().toString();
                String input2 = mEdit2.getText().toString();
                String input3 = mEdit3.getText().toString();

                boolean noneOfThese = false;
                if ( !(input1.equals("Ingredients") || input1.equals("")) ) {
                    //database shit
                    noneOfThese = true;
                    nextPage();
                }
                if ( (!(input2.equals("Recipe") || input2.equals("") ) ) && (noneOfThese ==false) ) {
                    //database shit
                    noneOfThese = true;
                    nextPage();
                }
                if ( (!(input3.equals("Cost") || input3.equals(""))  )   && (noneOfThese ==false)   ) {
                    //database shit
                    noneOfThese = true;
                    nextPage();
                }

                if(noneOfThese == false){
                    //print to app. please enter one of the values?

                }






                //print it
                //String input = mEdit.getText().toString();
                //Log.d("print", input);

            }



        });





    } //end on create

    protected boolean databaseStuff(String key){

        //get instance of child. which would be the different recipes

        //this is going to be the comparison part







    }
    protected void nextPage(){
        startActivity(new Intent(MainActivity.this, SecondPage.class));

    }





}