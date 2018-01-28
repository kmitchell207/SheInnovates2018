package com.example.kmitchell.meal_plannerpro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        ArrayList<String> al = (ArrayList<String>) getIntent().getSerializableExtra("StringKey");
        TreeSet<String> thets = (TreeSet<String>) getIntent().getSerializableExtra("ts");

        for(int i=0; i < al.size(); i ++){
            RecipeName.setText(RecipeName.getText() + al.get(i) + " , ");
        }

    }

}
