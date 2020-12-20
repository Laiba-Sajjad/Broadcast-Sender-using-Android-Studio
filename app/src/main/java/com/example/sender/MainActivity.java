package com.example.sender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String message;
    EditText send_msg;
    Button btn_send;
    private BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            if ("com.example.sender.ACTION_SEND".equals(intent.getAction())) {
                String extra = intent.getStringExtra("com.example.sender.EXTRA");

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send_msg = (EditText) findViewById(R.id.msg);
        btn_send = (Button) findViewById(R.id.button_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message= String.valueOf(send_msg.getText());
                send_msg.setText("");
                Intent intent = new Intent("com.example.sender.ACTION_SEND");
                intent.putExtra("com.example.sender.EXTRA",message);
                sendBroadcast(intent);

            }
        });
    }
    protected void onStart() {

        super.onStart();
        IntentFilter intentFilter = new IntentFilter("com.example.sender.ACTION_SEND");
        registerReceiver(myBroadcastReceiver,intentFilter);
    }
    protected void onStop()
    {
        super.onStop();
        unregisterReceiver(myBroadcastReceiver);
    }

}
