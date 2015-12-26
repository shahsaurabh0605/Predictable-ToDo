package com.example.good.todo_app;

/**
 * Created by good on 25/12/15.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Listitemseen extends Activity{
    TextView name;
    TextView details,date,time;
    Button changecolor;

    @Override
    protected void onCreate(Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listitem);
        name=(TextView)findViewById(R.id.listitemhead);
        details=(TextView)findViewById(R.id.listitemdetails);
        date=(TextView)findViewById(R.id.listitemdate);
        time=(TextView)findViewById(R.id.listitemtime);


        //thing_des.setText(getIntent().getExtras().getString("ThingDes"));
        name.setText(getIntent().getExtras().getString("n"));
        details.setText(getIntent().getExtras().getString("det"));
        date.setText(getIntent().getExtras().getString("date"));
        time.setText(getIntent().getExtras().getString("t"));


        changecolor=(Button)findViewById(R.id.colorbutton);
        changecolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //color picker here..

            }
        });
    }
}