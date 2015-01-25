package material.lp20.org.materialtest;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    private Toolbar mToolBar;
    private CharSequence mTitleName1, mTitleName2;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerMenu;
    private String[] mDrawerItemDescriptions;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ToolBar (ActionBar)
        mToolBar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolBar);

        mTitleName1 = getSupportActionBar().getTitle();
        mTitleName2 = getResources().getString(R.string.drawer_open);

        // DrawerLayout (Left menu)
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerMenu = (ListView) findViewById(R.id.left_drawer);
        mDrawerItemDescriptions = getResources().getStringArray(R.array.drawer_items);

        mDrawerMenu.setAdapter(new ArrayAdapter<String>(
                this, R.layout.drawer_list_item, mDrawerItemDescriptions));


        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolBar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mTitleName2);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(mTitleName1);
                invalidateOptionsMenu();
            }

        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);


        // Floating button
        final int size = getResources().getDimensionPixelSize(R.dimen.fab_size);
        findViewById(R.id.fab).setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, size, size);
            }
        });
        findViewById(R.id.fab).setClipToOutline(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerMenu);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
