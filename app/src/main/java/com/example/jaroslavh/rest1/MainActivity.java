package com.example.jaroslavh.rest1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void sayHello(View view) throws IOException {
        Thread httpThread = new Thread(new Runnable() {
            public void run() {
                Log.d("xxxxxxxxxxxxxxxxxxxxx3", sendGet());
            }
        });
        httpThread.start();
    }

    private String sendGet() {
        try {
            String mystr = "http://192.168.0.96/veres/hs/our-services/list";
            URL obj = new URL(mystr);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            final String username = "admin";
            final String password = "321";

            Authenticator.setDefault(new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password.toCharArray());
                }
            });

            int status = con.getResponseCode();
            Log.d("STATUS", String.valueOf(status));

            InputStream response = con.getInputStream();
            Scanner s = new Scanner(response).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";
            Log.d("xxxxxxxxxxxxxxxxxxxxx1", result);
            return result;
        } catch (Exception e) {
            Log.d("xxxxxxxxxxxxxxxxxxxxx2", e.toString());
            return e.toString();
        }
    }

}
