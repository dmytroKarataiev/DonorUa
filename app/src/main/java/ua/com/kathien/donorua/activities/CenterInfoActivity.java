package ua.com.kathien.donorua.activities;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.fragments.CenterInfoFragment;
import ua.com.kathien.donorua.fragments.CenterListFragment;
import ua.com.kathien.donorua.models.Center;

public class CenterInfoActivity extends AppCompatActivity {

    private ImageView img;
    private Bitmap bitmap;
    private ProgressDialog pDialog;
    private Toolbar toolbar;
    private static final String LOG_TAG = CenterInfoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_info);

        img = (ImageView) findViewById(R.id.center_info_image);
        if(img == null) {
            Log.i(LOG_TAG, "img imageView is null");
        }


        LoadImage loadImage = new LoadImage();
        loadImage.execute();

        toolbar = (Toolbar) findViewById(R.id.center_info_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
/*
        Center center = getIntent().getParcelableExtra(CenterListFragment.CENTER_EXTRA_TAG);
        toolbar.setTitle(center.getName());*/

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.your_placeholder, new CenterInfoFragment());
        ft.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_center_info, menu);
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

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CenterInfoActivity.this);
            pDialog.setMessage("Loading Image ....");
            pDialog.show();

        }

        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL("https://pp.vk.me/c626319/v626319162/2b47f/VMhy5BwvwtA.jpg").getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if (image != null) {
                img.setImageBitmap(image);
                pDialog.dismiss();

            } else {

                pDialog.dismiss();
                /*Snackbar snackbar = Snackbar.
                        make(coordinatorLayout, "Image Does Not exist or Network Error", Snackbar.LENGTH_SHORT);
                snackbar.show();*/
                Log.i(LOG_TAG, "Image Does Not exist or Network Error");
            }
        }
    }
}
