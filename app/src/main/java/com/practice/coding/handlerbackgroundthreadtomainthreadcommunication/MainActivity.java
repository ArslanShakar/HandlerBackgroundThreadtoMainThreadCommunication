package com.practice.coding.handlerbackgroundthreadtomainthreadcommunication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Handler handler;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_horizontal);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progressBar.setProgress(msg.arg1);
            }
        };
        // From background thread to main thread communication
        thread = new Thread(new MyThread());
        thread.start();

    }

    class MyThread implements Runnable {
        @Override
        public void run() {

            for (int i = 0; i < 100; i++) {
                Message message = Message.obtain();
                    message.arg1=i;
                    handler.sendMessage(message);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
