package com.ashish.applicationb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Switch;
import android.widget.TextView;
import android.os.Bundle;
import android.content.*;
import com.android.volley.*;
//import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.*;

import org.json.JSONArray;
import org.json.JSONObject;

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
                    TextView textView = (TextView)findViewById(R.id.responseTxt);
                    textView.setText(result);
                    Intent resultIntent = new Intent();
                    intent.putExtra("message1",result);
                    setResult(1,intent);
                    finish();
                    break;
                case "Addition":
//                    int value1 = Integer.parseInt(message[0]);
//                    int value2 = Integer.parseInt(message[1]);
//                     result = "Sum "+(value1+value2);
                    RestAPIReqeust(message[0],message[1],"add");
                    break;
                case "Substraction":
                    RestAPIReqeust(message[0],message[1],"sub");
                    // result = "Substraction "+(Integer.parseInt(message[0])-Integer.parseInt(message[1]));
                    break;

                case "Multiplication":
                    RestAPIReqeust(message[0],message[1],"multiply");
                   // result = "Multiplication "+(Integer.parseInt(message[0])*Integer.parseInt(message[1]));
                    break;
                case "Division":
                    RestAPIReqeust(message[0],message[1],"divide");
                    //result = "Division "+(Integer.parseInt(message[0])/Integer.parseInt(message[1]));
                    break;
            }
//            TextView textView = (TextView)findViewById(R.id.responseTxt);
//            textView.setText(result);
//            Intent resultIntent = new Intent();
//            intent.putExtra("message1",result);
//            setResult(1,intent);
//
//           finish();

        }
        else if(receivedAction.equals(Intent.ACTION_MAIN)){
            //app has been launched directly, not from share list

        }


    }



    void RestAPIReqeust(String value1, String value2,String Operation)
    {

        final TextView textView = (TextView) findViewById(R.id.responseTxt);
// ...

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://winter-wood-9450.getsandbox.com:443/operation/"+Operation+"/"+value1+"/"+value2;

        JSONObject parameter = new JSONObject();
// Request a string response from the provided URL.
        final JsonObjectRequest jsonObjectRequest = new  JsonObjectRequest (Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Display the first 500 characters of the response string.
                           // JSONObject jsonObject = response.getJSONObject("Result");
                            String resultValue = response.getString("result");
                            textView.setText("Response is: " +resultValue.toString()) ;
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("message1", resultValue.toString());
                            setResult(1, resultIntent);
                            finish();
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();;
                        }
                    }
                },
               new Response.ErrorListener() {
                     @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("That didn't work!");
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("message1", "That didn't work!!");
                        setResult(1, resultIntent);

                        finish();
                    }
                }
        );

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);

    }
}
