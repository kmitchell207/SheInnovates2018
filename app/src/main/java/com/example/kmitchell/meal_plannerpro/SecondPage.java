package com.example.kmitchell.meal_plannerpro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;


public class SecondPage extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        TextView RecipeName = (TextView) findViewById(R.id.textView2);

        final ArrayList<String> al = (ArrayList<String>) getIntent().getSerializableExtra("StringKey");
        final TreeSet<String> thets = (TreeSet<String>) getIntent().getSerializableExtra("ts");

        for (int i = 0; i < al.size(); i++) {
            RecipeName.setText(RecipeName.getText() + al.get(i) + " , ");
        }


//going to third
        Button btn = (Button) findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPage(al, thets);
            }


            protected void nextPage(ArrayList<String> al, TreeSet<String> ts) {
                Intent i = new Intent(SecondPage.this, ThirdPage.class);
                i.putExtra("StringKey", al);
                i.putExtra("ts", ts);

                startActivity(i);


                //startActivity(new Intent(MainActivity.this, SecondPage.class));

            } //next page
        });//on click}
        //on click listn
        //cretare

    } //main
}
