package ua.com.kathien.donorua.activities;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import ua.com.kathien.donorua.R;

public class HotlineActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotline);

        initComponents();
    }

    public void initComponents() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initNavigationDrawer();
    }

    public void initNavigationDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();

                switch(id) {

                    case R.id.centers_list:
                        Toast.makeText(getApplicationContext(), "Center list", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.centers_map:
                        Toast.makeText(getApplicationContext(), "Center map", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.recipients:
                        Toast.makeText(getApplicationContext(), "Recipients", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.hotline:
                        Toast.makeText(getApplicationContext(), "Hot line", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.news:
                        Toast.makeText(getApplicationContext(), "News", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Menu item unknown", Toast.LENGTH_SHORT).show();
                        break;

                }


                return true;
            }
        });

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);


        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                        R.string.drawer_open, R.string.drawer_close){
                    @Override
                    public void onDrawerClosed(View v){
                        super.onDrawerClosed(v);
                    }

                    @Override
                    public void onDrawerOpened(View v) {
                        super.onDrawerOpened(v);
                    }
                };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hotline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
