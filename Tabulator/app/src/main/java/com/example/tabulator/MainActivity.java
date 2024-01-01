package com.example.tabulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tabulator.ui.main.MenuFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.menu_fragment_container, MenuFragment.class, null)
                    .setReorderingAllowed(true)
                    .commit();
        }
    }
}