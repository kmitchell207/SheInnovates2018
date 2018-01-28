package com.example.kmitchell.meal_plannerpro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class ThirdPage extends SecondPage {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_page);


        TextView ingredients = (TextView) findViewById(R.id.textView4);

        final ArrayList<String> al = (ArrayList<String>) getIntent().getSerializableExtra("StringKey");
        final TreeSet<String> thets = (TreeSet<String>) getIntent().getSerializableExtra("ts");


        Iterator iterator2 = thets.iterator();
        while (iterator2.hasNext()) {
            ingredients.setText(ingredients.getText() + iterator2.next().toString() + " , ");

        }



    }

}
