package com.my.crack4399;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void load_encrypt_so(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Login login = new Login();
                login.login_func();
            }
        }).start();
    }

}