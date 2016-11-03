package ua.com.kathien.donorua.activities;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.utils.OnlineHelper;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private RelativeLayout relativeLayout;
    private NavigationView navigationView;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        navigationView = (NavigationView) findViewById(R.id.base_navigation_view);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.base_coordinator_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.base_drawer_layout);
       //relativeLayout = (RelativeLayout) findViewById(R.id.base_coordinator_layout);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                        R.string.drawer_open, R.string.drawer_close) {
                    @Override
                    public void onDrawerClosed(View v) {
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
    public boolean onNavigationItemSelected(MenuItem item) {
        drawerLayout.closeDrawer(navigationView);
        Snackbar snackbar;
        if (OnlineHelper.isOnline(this)) {
            switch (item.getItemId()) {
                case R.id.centers_list:
                    Intent centerListIntent = new Intent(getApplicationContext(), CenterListActivity.class);
                    startActivity(centerListIntent);

                    break;
                case R.id.centers_map:
                    Intent centerMapIntent = new Intent(getApplicationContext(), CenterMapActivity.class);
                    startActivity(centerMapIntent);
                    break;
                case R.id.recipients:
                    Intent recipientsListIntent = new Intent(getApplicationContext(), RecipientListActivity.class);
                    startActivity(recipientsListIntent);
                    break;
                case R.id.hotline:
                    Intent hotlineIntent = new Intent(getApplicationContext(), HotlineActivity.class);
                    startActivity(hotlineIntent);
                    break;
                case R.id.news:
                    Intent newsListIntent = new Intent(getApplicationContext(), NewsListActivity.class);
                    startActivity(newsListIntent);
                    break;
                default:
                    snackbar = Snackbar
                            .make(coordinatorLayout, "Unknown option selected", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    break;
            }
        } else {
            snackbar = Snackbar.make(coordinatorLayout, "Turn on internet connection", Snackbar.LENGTH_INDEFINITE);
            snackbar.show();
        }

        return false;
    }

}
