package com.whell.evgeny.lifewhell2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import utils.MyDBHandler;
import utils.ViewPagerAdapter;
import utils.categoryItem;
import utils.categoryItemList;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private MyDBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Live Wheel");

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.menu, menu);
        // Return true to display menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.add_element:    addElement();
                break;
            case R.id.delete:    deleteAll();
                break;
            case R.id.about:       about();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void about() {
        Toast.makeText(getApplicationContext(), "Coming soon",
                Toast.LENGTH_LONG).show();
    }
    private void deleteAll()
    {
        db = new MyDBHandler(this, null, null, 1);
        categoryItemList list = new categoryItemList();
        list=db.findAllItems();

        for(categoryItem item : list.getList()) // go through data list form db and assign value for graph
        {
            boolean result =false;
            result = db.deleteCategoryItem(item.getCategoryName().toString());
            Log.d("DB","Value is: " + result);
        }
        super.recreate();
    }
    private void addElement()
    {
        // fire another activity
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

}
