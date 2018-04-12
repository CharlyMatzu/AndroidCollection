package com.example.charly.backgroundtasks.AsyncTask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.charly.backgroundtasks.R;

public class LoginAsync extends AppCompatActivity {

    private Button btnSingin;
    private EditText txtUser, txtPass;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_async);

        btnSingin = (Button) findViewById(R.id.btnSignin);
        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPass = (EditText) findViewById(R.id.txtPass);

        progress = (ProgressBar) findViewById(R.id.progress);
    }

    public void singin(View v){
        Toast.makeText(getApplicationContext(), "Iniciando", Toast.LENGTH_SHORT).show();
        new SinginTask().execute( txtUser.getText().toString() );
    }



    class SinginTask extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

            return strings[0];
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progress.setVisibility(ProgressBar.GONE);
            Toast.makeText(getApplicationContext(), "Inicio como: "+s, Toast.LENGTH_LONG).show();
        }
    }
}
