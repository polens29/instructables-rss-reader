package com.example.panasco.instructablesrss;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.panasco.instructablesrss.RSSFeedTask;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        this.fade_logo();

        RSSFeedTask rss = new RSSFeedTask();
        rss.searchByKeyword("android");


    }

    public void fade_logo(){
        final Animation fade_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        final ImageView logo = (ImageView) findViewById(R.id.fadeout_logo);

        new CountDownTimer(1500, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish(){
                logo.startAnimation(fade_out);

                new CountDownTimer(1000,1000){

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        setContentView(R.layout.activity_main);
                    }
                }.start();
            }

        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.search_menu);

        SearchView sv = (SearchView)item.getActionView();
        sv.setQueryHint(getResources().getString(R.string.search_hint));
        sv.setMaxWidth(Integer.MAX_VALUE);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                setContentView(R.layout.search_results);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        sv.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                setContentView(R.layout.activity_main);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}
