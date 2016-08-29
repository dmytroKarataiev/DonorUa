package ua.com.kathien.donorua.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.models.Center;
import ua.com.kathien.donorua.models.City;
import ua.com.kathien.donorua.models.DonorEvent;
import ua.com.kathien.donorua.models.News;
import ua.com.kathien.donorua.models.Recipient;
import ua.com.kathien.donorua.utils.CentersParser;
import ua.com.kathien.donorua.utils.CityParser;
import ua.com.kathien.donorua.utils.EventsParser;
import ua.com.kathien.donorua.utils.NewsParser;
import ua.com.kathien.donorua.utils.RecipientsParser;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    private ArrayList<Center> centers;
    private ArrayList<Recipient> recipients;
    private ArrayList<News> news;
    private ArrayList<City> cities;
    private ArrayList<DonorEvent> events;

    private boolean recipientsDownloaded;
    private boolean centersDownloaded;
    private boolean newsDownloaded;
    private boolean citiesDownloaded;
    private boolean eventsDownloaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }


    public void showNews() {
        NewsShower newsShower = new NewsShower();
        newsShower.execute();
    }

    public void showCenters() {
        CentersShower centersShower = new CentersShower();
        centersShower.execute();
    }

    public void showCentersMap() {
        CentersMapShower centersMapShower = new CentersMapShower();
        centersMapShower.execute();
    }

    public void showRecipient() {
        RecipientsShower recipientsShower = new RecipientsShower();
        recipientsShower.execute();
    }

    public void showEvents() {
        EventsShower eventsShower = new EventsShower();
        eventsShower.execute();
    }

    public void fetchCities() {

    }

    public boolean isOnline() {
        boolean isOnline;
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        isOnline = networkInfo != null && networkInfo.isConnected();
        return isOnline;
    }

    private ArrayList<Center> parseAllCenters() {

        if(centersDownloaded) {
            return centers;
        }
        //TODO: How to show user that there is no internet connection?
        if (!isOnline()) {
           /* runOnUiThread(new Runnable()
            {
                public void run()
                {
                    Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
                }
            });*/
            return null;
        }
        CentersParser centersParser = new CentersParser();
        centersParser.parseCenters(centers, cities);
        centersDownloaded = true;

        return centers;
    }

    private ArrayList<DonorEvent> parseAllEvents() {

        if(eventsDownloaded) {
            return events;
        }

        if(!isOnline()) {
            return null;
        }

        EventsParser eventsParser = new EventsParser();
        eventsParser.parseEvents(events, cities);
        eventsDownloaded = true;

        return events;
    }

    private ArrayList<News> parseAllNews() {
        if (newsDownloaded) {
            return news;
        }
        if (!isOnline()) {
            /*runOnUiThread(new Runnable()
            {
                public void run()
                {
                    Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
                }
            });*/
            return null;
        }
        NewsParser newsParser = new NewsParser();
        newsParser.parseNews(news);
        newsDownloaded = true;

        return news;
    }

    private ArrayList<City> parseAllCities() {
        if(citiesDownloaded) {
            return cities;
        }
        if(!isOnline()) {
            return null;
        }
        CityParser cityParser = new CityParser();
        cityParser.parseCities(cities);
        citiesDownloaded = true;
        return cities;

    }

    private ArrayList<Recipient> parseAllRecipients() {
        if(recipientsDownloaded) {
            return recipients;
        }
        if (!isOnline()) {
            /*runOnUiThread(new Runnable()
            {
                public void run()
                {
                    Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
                }
            });
*/
            return null;
        }
        RecipientsParser recipientsParser = new RecipientsParser();
        recipientsParser.parseRecipients(recipients);
        recipientsDownloaded = true;

        return recipients;
    }

    private class NewsShower extends AsyncTask<Void, Void, ArrayList<News>> {
        public NewsShower() {
            super();
        }

        @Override
        protected ArrayList<News> doInBackground(Void... params) {
            news = parseAllNews();
            return news;
        }

        @Override
        protected void onPostExecute(ArrayList<News> result) {
            if(result == null) {
                Log.v(LOG_TAG, "news list is null");
                return;
            }
            Log.i(LOG_TAG + "news", news.toString());
        }
    }

    private class CentersShower extends AsyncTask<Void, Void, ArrayList<Center>> {

        public CentersShower() {
            super();
        }

        @Override
        protected ArrayList<Center> doInBackground(Void... params) {
            cities = parseAllCities();
            centers = parseAllCenters();
            return centers;
        }


        @Override
        protected void onPostExecute(ArrayList<Center> result) {

            if(result == null) {
                Log.i(LOG_TAG, "center list is null");
                return;
            }
            Intent testIntent = new Intent(MainActivity.this, CenterListActivity.class);
            testIntent.putExtra("OurCenters", result);

            startActivity(testIntent);
        }

    }



    private class CentersMapShower extends AsyncTask<Void, Void, ArrayList<Center>> {

        public CentersMapShower() {
            super();
        }

        @Override
        protected ArrayList<Center> doInBackground(Void... params) {
            cities = parseAllCities();
            centers = parseAllCenters();
            return centers;
        }


        @Override
        protected void onPostExecute(ArrayList<Center> result) {

            if(result == null) {
                Log.i(LOG_TAG, "center list is null");
                return;
            }
            Intent testIntent = new Intent(MainActivity.this, CenterMapActivity.class);
            testIntent.putExtra("OurCenters", result);

            startActivity(testIntent);
        }

    }


    private class EventsShower extends AsyncTask<Void, Void, ArrayList<DonorEvent>> {

        public EventsShower() {
            super();
        }

        @Override
        protected ArrayList<DonorEvent> doInBackground(Void... params) {
            cities = parseAllCities();
            events = parseAllEvents();
            return events;
        }


        @Override
        protected void onPostExecute(ArrayList<DonorEvent> result) {

            if(result == null) {
                Log.i(LOG_TAG, "events list is null");
                return;
            }
            /*Intent testIntent = new Intent(MainActivity.this, EventsListActivity.class);
            testIntent.putExtra("events", result);

            startActivity(testIntent);*/
            Log.i(LOG_TAG, events.toString());
        }

    }



    private class RecipientsShower extends AsyncTask<Void, Void, ArrayList<Recipient>> {

        public RecipientsShower() {
            super();
        }

        @Override
        protected ArrayList<Recipient> doInBackground(Void... params) {
            recipients = parseAllRecipients();
            return recipients;
        }

        @Override
        protected void onPostExecute(ArrayList<Recipient> result) {
            if(result == null) {
                Log.i(LOG_TAG, "recipient list is null");
            }
            Intent recipientIntent = new Intent(MainActivity.this, RecipientListActivity.class);
            recipientIntent.putExtra("MyRecipients", result);
            startActivity(recipientIntent);
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void initComponents() {

        centers = new ArrayList<>();
        recipients = new ArrayList<>();
        news = new ArrayList<>();
        cities = new ArrayList<>();
        events = new ArrayList<>();

        recipientsDownloaded = false;
        centersDownloaded = false;
        newsDownloaded = false;
        citiesDownloaded = false;
        eventsDownloaded = false;

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
                        showCenters();
                        break;

                    case R.id.centers_map:
                        showCentersMap();
                        break;

                    case R.id.recipients:
                        showRecipient();
                        break;

                    case R.id.hotline:
                        Intent hotlineActivity = new Intent(MainActivity.this, HotlineActivity.class);
                        startActivity(hotlineActivity);
                        break;

                    case R.id.news:
                        showNews();
                        break;

                    case R.id.events:
                        showEvents();
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


}
