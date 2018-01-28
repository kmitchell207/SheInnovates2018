package com.example.kmitchell.meal_plannerpro;

import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.util.JsonWriter;
import java.lang.Iterable;
import java.util.Iterator;

import java.util.ArrayList;
import java.util.TreeSet;




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


    String input1 ="";
    String input2 = "";
    String input3 = "";

    boolean noneOfThese = false;
    boolean bInput = false;
    boolean bInput2 = false;
    boolean bInput3 = false;



    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creates instance of database
        mFirebaseInstance = FirebaseDatabase.getInstance();
        //get reference to user node
        mFirebaseDatabase = mFirebaseInstance.getReference();
//meal-planner-pro-331c1
        Button btn = (Button) findViewById(R.id.button3);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {


                mEdit = (EditText) findViewById(R.id.editText);
                mEdit2 = (EditText) findViewById(R.id.editText2);
                mEdit3 = (EditText) findViewById(R.id.editText3);

                 input1 = mEdit.getText().toString();
                 input2 = mEdit2.getText().toString();
                 input3 = mEdit3.getText().toString();

                 noneOfThese = false;
                 bInput = false;
                 bInput2 = false;
                 bInput3 = false;

                if (!(input1.equals("Ingredients") || input1.equals(""))) {
                    bInput = true;
                    noneOfThese = true;
                }
                if ((!(input2.equals("Recipe") || input2.equals(""))) && (noneOfThese == false)) {
                    bInput2 = true;
                    noneOfThese = true;
                }
                if ((!(input3.equals("Cost") || input3.equals(""))) && (noneOfThese == false)) {
                    bInput3 = true;
                    noneOfThese = true;
                }

                if (noneOfThese == false) {
                    //print to app. please enter one of the values?

                }


                mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String i = "0";
                        boolean eStop = false;
                        TreeSet<String> ingredientList = new TreeSet<String>();
                        ArrayList<String> theFinalRecipe = new ArrayList<String>();
                        //  Log.d("Print", dataSnapshot.getValue().toString()  );



                        //this is going to find the recipes we need
                        //recipe
                        for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                            String ingred = dataSnapshot2.getKey().toString();
                            String item = "";
                            String anotherOne = "";
                            String thelast = "";


                            //numbers
                            for (DataSnapshot dataSnapshot3 : dataSnapshot2.getChildren()) {
                                item = dataSnapshot3.getValue().toString();
                                //   item = dataSnapshot3.getChildren().toString();
                                if (eStop == true) {
                                    break;
                                }
                                //type
                                for (DataSnapshot dataSnapshot4 : dataSnapshot3.getChildren()) {
                                    anotherOne = dataSnapshot4.getKey().toString();
                                    //   item = dataSnapshot3.getChildren().toString();

                                    if (eStop == true) {
                                        break;
                                    }

                                    //ingredient
                                    for (DataSnapshot dataSnapshot5 : dataSnapshot4.getChildren()) {
                                        thelast = dataSnapshot5.getKey().toString();
                                        //   item = dataSnapshot3.getChildren().toString();

                                        if (bInput) {
                                            if (input1.toUpperCase().equals(thelast.toUpperCase())) {
                                                theFinalRecipe.add(ingred.toUpperCase());
                                            }

                                        }
                                    }
                                }
                            }
                        }//end first for loop



                            //this is going to add teh correct ingredients with the recipes we have
                            //recipes
                            for (DataSnapshot dataSnapshot12 : dataSnapshot.getChildren()) {
                                 String ingred = dataSnapshot12.getKey().toString();
                               String  item = "";
                                String anotherOne = "";
                                String thelast = "";
                                 boolean addIt = false;

                                Iterator iterator = theFinalRecipe.iterator();
                                while (iterator.hasNext()) {
                                    if (ingred.toUpperCase().equals(iterator.next())) {
                                        addIt = true;
                                        break;
                                    }
                                }


                                //numbers
                                for (DataSnapshot dataSnapshot13 : dataSnapshot12.getChildren()) {
                                    item = dataSnapshot13.getValue().toString();
                                    //   item = dataSnapshot3.getChildren().toString();
                                    if (eStop == true) {
                                        break;
                                    }
                                    //type
                                    for (DataSnapshot dataSnapshot14 : dataSnapshot13.getChildren()) {
                                        anotherOne = dataSnapshot14.getKey().toString();
                                        //   item = dataSnapshot3.getChildren().toString();

                                        if (eStop == true) {
                                            break;
                                        }

                                        //ingredient
                                        for (DataSnapshot dataSnapshot15 : dataSnapshot14.getChildren()) {
                                            thelast = dataSnapshot15.getKey().toString();
                                            //   item = dataSnapshot3.getChildren().toString();

                                            if(addIt ){
                                                ingredientList.add(thelast);


                                            }
                                        }
                                        addIt = false;
                                    }
                                }


                  //          Log.d("Ingreg", ingred);
                    //        Log.d("item", item);
                      //      Log.d("!!!!!", anotherOne);
                        //    Log.d("last", thelast);
                        }//end for loop

                        Iterator iterator = theFinalRecipe.iterator();
                        while (iterator.hasNext()) {
                            Log.d("Recipes", iterator.next().toString() );

                        }

                        Iterator iterator2 = ingredientList.iterator();
                        while (iterator2.hasNext()) {
                            Log.d("Ingredients", iterator2.next().toString() );

                        }

                        nextPage(theFinalRecipe, ingredientList);

                    }//end ondata change

                    @Override
                    public void onCancelled(DatabaseError databaseerror){

                    }//end oncancelled
                });//and add listener


            }//on click


        });//end setclick listener
    }//on create


    protected void nextPage(ArrayList<String> al, TreeSet <String>ts){
        Intent i =new Intent(MainActivity.this,SecondPage.class);
        i.putExtra("StringKey",al);
        i.putExtra("ts",ts);

        startActivity(i);


        //startActivity(new Intent(MainActivity.this, SecondPage.class));

    }





}//end activity

