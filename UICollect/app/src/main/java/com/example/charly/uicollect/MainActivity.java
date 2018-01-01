package com.example.charly.uicollect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.charly.uicollect.BackButton.ActivityBackActionBar;
import com.example.charly.uicollect.BackButton.ActivityBackToolbar;
import com.example.charly.uicollect.Menus.ActivityMenuActionBar;

public class MainActivity extends AppCompatActivity {


    //------MENU
    private Button btnMenuAB, btnMenuTB;

    //------BackButton
    private Button btnActivityAB, btnActivityTB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("PRINCIPAL");

        //---------TOOLBAR
        btnMenuAB = (Button) findViewById(R.id.btnMenuAB);
        btnMenuTB = (Button) findViewById(R.id.btnMenuTB);

        //---------BackButton
        btnActivityAB = (Button) findViewById(R.id.btnActivityActionBar);
        btnActivityTB = (Button) findViewById(R.id.btnActivityToolbar);
    }

    //-----------------
    //  MENU
    //-----------------
    public void showMenuAB(View v){
        Intent intent = new Intent(getApplicationContext(), ActivityMenuActionBar.class);
        startActivity(intent);
    }

    public void showMenuTB(View v){
//        Intent intent = new Intent(getApplicationContext(), ActivityMenuActionBar.class);
//        startActivity(intent);
    }





    //-----------------
    //  BACK BUTTON
    //-----------------
    public void showActivityActionbar(View v){
        Intent intent = new Intent(getApplicationContext(), ActivityBackActionBar.class);
        startActivity(intent);
    }


    public void showActivityToolbar(View v){
        Intent intent = new Intent(getApplicationContext(), ActivityBackToolbar.class);
        startActivity(intent);
    }
}
