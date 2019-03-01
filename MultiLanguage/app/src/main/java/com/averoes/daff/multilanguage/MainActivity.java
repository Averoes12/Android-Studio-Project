package com.averoes.daff.multilanguage;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView goodMorning, plural, xliff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goodMorning = findViewById(R.id.morning);
        plural = findViewById(R.id.plural);
        xliff = findViewById(R.id.xliff);

        int pokeAccount = 3;
        String hello = String.format(getResources().getString(R.string.hello_world),"Daff",pokeAccount,"Averoes");

        goodMorning.setText(hello);

        int songAccount = 5;

        String pluralText  = getResources().getQuantityString(R.plurals.numberOfSongsAvailable, songAccount, songAccount);
        plural.setText(pluralText);

        xliff.setText(getResources().getString(R.string.app_homeurl));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
