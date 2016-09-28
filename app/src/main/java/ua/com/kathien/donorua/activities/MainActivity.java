package ua.com.kathien.donorua.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;


import ua.com.kathien.donorua.R;

public class MainActivity extends BaseActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.base_content_frame);
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);

    }


    /*private class CentersMapShower extends AsyncTask<Void, Void, ArrayList<Center>> {

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

            if (result == null) {
                Log.i(LOG_TAG, "center list is null");
                return;
            }
            Intent testIntent = new Intent(MainActivity.this, CenterMapActivity.class);
            testIntent.putExtra("OurCenters", result);

            startActivity(testIntent);
        }

    }*/



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

}

    /*public void showEvents() {
        EventsShower eventsShower = new EventsShower();
        eventsShower.execute();
    }*/

    /*private ArrayList<DonorEvent> parseAllEvents() {

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
     }*/


    /*private class EventsShower extends AsyncTask<Void, Void, ArrayList<DonorEvent>> {

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

            if (result == null) {
                Log.i(LOG_TAG, "events list is null");
                return;
            }
            Intent eventsIntent = new Intent(MainActivity.this, EventsListActivity.class);
            eventsIntent.putExtra("events", result);

            startActivity(eventsIntent);
            Log.i(LOG_TAG, events.toString());
        }

    }*/

