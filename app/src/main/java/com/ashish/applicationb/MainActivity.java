package com.ashish.applicationb;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.TextView;
import android.os.Bundle;
import android.content.*;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        //get the action
        String receivedAction = intent.getAction();
        //find out what we are dealing with

        String receivedType = intent.getType();

        if(receivedAction.equals(Intent.ACTION_SEND)){
            //content is being shared
            String[] message = intent.getStringArrayExtra("values");
           Log.d("text",message[0]);
            Log.d("text",message[1]);
            TextView textView = (TextView)findViewById(R.id.responseTxt);

            textView.setText(message[0]+message[1]);
        }
        else if(receivedAction.equals(Intent.ACTION_MAIN)){
            //app has been launched directly, not from share list

        }


    }
}
