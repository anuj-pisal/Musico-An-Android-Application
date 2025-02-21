package com.example.musico;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tablayout;
    ConstraintLayout conslayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tablayout = (TabLayout) findViewById(R.id.tab_layout);
        conslayout = (ConstraintLayout) findViewById(R.id.constraint);

        getSupportFragmentManager().beginTransaction().replace(R.id.constraint, new Home())
                .addToBackStack(null)
                .commit();

        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Fragment frag = null;

                switch (tab.getPosition()) {
                    case 0:
                        frag = new Home();
                        break;
                    case 1:
                        frag = new Search();
                        break;
                    case 2:
                        frag = new Library();
                        break;
                }

                assert frag != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.constraint, frag)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }
}