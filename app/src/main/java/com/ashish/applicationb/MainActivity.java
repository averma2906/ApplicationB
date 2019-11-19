package com.ashish.applicationb;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Switch;
import android.widget.TextView;
import android.os.Bundle;
import android.content.*;
enum OperationType{
    Concatination,
    Addition,
    Substraction,
    Multiplication,
    Division
}
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

            String currentOperationType =  (message[2]);
            Log.d("text",message[0]);
            Log.d("text",message[1]);
            String result = "";
            switch (currentOperationType)
            {
                case "Concatination" :
                    result = message[0]+message[1];
                    break;
                case "Addition":
                    int value1 = Integer.parseInt(message[0]);
                    int value2 = Integer.parseInt(message[1]);
                     result = "Sum "+(value1+value2);
                    break;
                case "Substraction":

                    result = "Substraction "+(Integer.parseInt(message[0])-Integer.parseInt(message[1]));
                    break;

                case "Multiplication":
                    result = "Multiplication "+(Integer.parseInt(message[0])*Integer.parseInt(message[1]));
                    break;
                case "Division":
                    result = "Division "+(Integer.parseInt(message[0])/Integer.parseInt(message[1]));
                    break;
            }
            TextView textView = (TextView)findViewById(R.id.responseTxt);
            textView.setText(result);
        }
        else if(receivedAction.equals(Intent.ACTION_MAIN)){
            //app has been launched directly, not from share list

        }


    }
}
