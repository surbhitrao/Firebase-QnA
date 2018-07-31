/*
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.firebase.quickstart.database;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.quickstart.database.fragment.MyPostsFragment;
import com.google.firebase.quickstart.database.fragment.MyTopPostsFragment;
import com.google.firebase.quickstart.database.fragment.RecentPostsFragment;
import com.google.firebase.quickstart.database.fragment.Tab2;

public class  MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private Chronometer chronometer,chronometer2;
    private long pauseOffset;
    private boolean running;

    private long pauseOffset2;
    private boolean running2;
    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = findViewById(R.id.simpleChronometer);
        chronometer2 = findViewById(R.id.simpleChronometer2);

        // Create the adapter that will return a fragment for each section
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[] {
                    new Tab2(),
                    new RecentPostsFragment()
//                    new MyTopPostsFragment(),


            };
            private final String[] mFragmentNames = new String[] {
                   "Playlist",
                    "All Posts"
            };
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }
            @Override
            public int getCount() {
                return mFragments.length;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentNames[position];
            }
        };
        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("tabselected", String.valueOf(position));

                if (position==0){
                    startChronometer();
                    pauseChronometer2();
                }
                else{
                    startChronometer2();
                    pauseChronometer();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("tabunselected", String.valueOf(position));

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("tabreselected", String.valueOf(position));

            }
        });







        // Button launches NewPostActivity
        findViewById(R.id.fab_new_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewPostActivity.class));
            }
        });


       chronometer.setFormat("Session Time Youtube: %s");
        chronometer.setBase(SystemClock.elapsedRealtime());

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
//                if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 10000) {
//                    chronometer.setBase(SystemClock.elapsedRealtime());
//                   // Toast.makeText(MainActivity.this, "Bing!", Toast.LENGTH_SHORT).show();
//                }
            }
        });

       // startChronometer();

        chronometer2.setFormat("Session Time QnA: %s");
        chronometer2.setBase(SystemClock.elapsedRealtime());

        chronometer2.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
//                if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 10000) {
//                    chronometer.setBase(SystemClock.elapsedRealtime());
//                   // Toast.makeText(MainActivity.this, "Bing!", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        //startChronometer2();
        startChronometer();
    }


    public void startChronometer() {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    public void pauseChronometer() {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    public void resetChronometer() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }







    public void startChronometer2() {
        if (!running2) {
            chronometer2.setBase(SystemClock.elapsedRealtime() - pauseOffset2);
            chronometer2.start();
            running2 = true;
        }
    }

    public void pauseChronometer2() {
        if (running2) {
            chronometer2.stop();
            pauseOffset2 = SystemClock.elapsedRealtime() - chronometer2.getBase();
            running2 = false;
        }
    }

    public void resetChronometer2() {
        chronometer2.setBase(SystemClock.elapsedRealtime());
        pauseOffset2 = 0;
    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, GoogleSignInActivity.class));
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}
