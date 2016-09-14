package ua.com.kathien.donorua.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ua.com.kathien.donorua.R;

public class HotlineActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private Button callHotlineButton;
    private CoordinatorLayout coordinatorLayout;

    //private static final String NUMBER = "tel:0672080303";
    private static final String NUMBER = "tel:0666671296";
    private static final String LOG_TAG = HotlineActivity.class.getSimpleName();
    //Code to check whether permission to call was granted
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;


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

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_hotline_activity);

        callHotlineButton = (Button) findViewById(R.id.btn_call_hotline);
        callHotlineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();
            }
        });

        initNavigationDrawer();
    }

    public void initNavigationDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()  {
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

    public void makeCall() {
        int permissionCheck = ContextCompat.checkSelfPermission(HotlineActivity.this,
                Manifest.permission.CALL_PHONE);

        switch(permissionCheck) {
            case PackageManager.PERMISSION_GRANTED:
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(NUMBER));
                startActivity(callIntent);
                break;

            case PackageManager.PERMISSION_DENIED:
                ActivityCompat.requestPermissions(HotlineActivity.this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                break;
            default:

                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        switch(requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE:

                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(NUMBER));
                    startActivity(callIntent);
                } else {
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Call permission needed", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                break;
            default:
                Log.e(LOG_TAG, "Unknown permission request code");
                break;
        }
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
