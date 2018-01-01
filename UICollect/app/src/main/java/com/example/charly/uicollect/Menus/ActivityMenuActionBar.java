package com.example.charly.uicollect.Menus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.charly.uicollect.R;

public class ActivityMenuActionBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar_menu);
        setTitle("Menu con ActionBar");
    }

    /**
     * Se sobreescribe este método para inflar el menu (XML) creado con anterioridad
     * dentro de "Res"
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Se agrega el menu (Se infla)
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }

    /**
     * Se sobreescribe para manejar los eventos del menu
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_op1:
                Toast.makeText(getApplicationContext(), "Opcion 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_op2:
                Toast.makeText(getApplicationContext(), "Opcion 2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_op3:
                Toast.makeText(getApplicationContext(), "Opcion 3", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_op4:
                //Cambia el checked del elemento
                item.setChecked(!item.isChecked());
                Toast.makeText(getApplicationContext(), "Opcion 4", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
