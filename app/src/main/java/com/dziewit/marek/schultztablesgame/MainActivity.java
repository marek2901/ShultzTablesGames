package com.dziewit.marek.schultztablesgame;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dziewit.marek.schultztablesgame.custom.views.ShultzTableView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout)
    protected DrawerLayout drawerLayout;

    @BindView(R.id.tool_bar)
    protected Toolbar toolbar;

    @BindView(R.id.table_view)
    ShultzTableView tableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();

    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                    this, drawerLayout, toolbar,
                    R.string.drawer_title, R.string.drawer_title
            );
            drawerLayout.addDrawerListener(mDrawerToggle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            mDrawerToggle.syncState();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        for (int i = 0; i < 6; i++) {
            menu.add(0, i, 0, String.format("%sx%s", i + 2, i + 2));
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int option = item.getItemId() + 2;
        tableView.refreshView(option, option);
        return super.onOptionsItemSelected(item);
    }
}
