package com.hybrid.tech.revisionapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ShareActionProvider;


public class MainActivity extends Activity {
    private ShareActionProvider shareActionProvider;



    //This is a provider for a share action. It is responsible for creating views that enable
    // data sharing and also to show a sub menu with sharing activities if the hosting item is placed on the overflow men
    // In Activity#onCreateOptionsMenu
    //USING IT
    // public boolean onCreateOptionsMenu(Menu menu) {
    // Get the menu item.
    // MenuItem menuItem = menu.findItem(R.id.my_menu_item);
    // Get the provider and hold onto it to set/change the share intent.
    // mShareActionProvider = (ShareActionProvider) menuItem.getActionProvider();
    // Set history different from the default before getting the action
    // view since a call to MenuItem.getActionView() calls
    // onCreateActionView() which uses the backing file name. Omit this
    // line if using the default share history file is desired.
    // mShareActionProvider.setShareHistoryFileName("custom_share_history.xml");
    //
    // Somewhere in the application.
   // public void doShare(Intent shareIntent) {
        // When you want to share set the share intent.
     //   mShareActionProvider.setShareIntent(shareIntent);
    //}
    private String[] titles;
    private ListView drawerList;
    private DrawerLayout drawerLayout;   //our layout
    private ActionBarDrawerToggle drawerToggle;  //This class provides a handy way to tie together the functionality of DrawerLayout and the
    // framework ActionBar to implement the recommended design for navigation drawers.
    // 	ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, int openDrawerContentDescRes, int closeDrawerContentDescRes)
    //  Construct a new ActionBarDrawerToggle.




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      titles= getResources().getStringArray(R.array.title);  //get the titles in the array
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);  //register
        drawerList=(ListView)findViewById(R.id.drawer);  //this too
        drawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles));
        //providing list with data and style
        drawerList.setOnItemClickListener(new DrawerItemClickListener());  //for click Events

        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open_drawer,R.string.close_drawer)
        {
            public void onDrawerClosed(View view)
            {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView)  //Invoked when the drawer becomes fully open.
            {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();  //this calls onPrepareOptionsMenu
            }


        };
        getActionBar().setDisplayHomeAsUpEnabled(true); //back option
        getActionBar().setHomeButtonEnabled(true);
        drawerLayout.setDrawerListener(drawerToggle);
        if(savedInstanceState==null)
        {
            selectItem(0);
        }

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        boolean drawerOpen=drawerLayout.isDrawerOpen(drawerList);  //is it open
        menu.findItem(R.id.action_share).setVisible(!drawerOpen); //hide the action share if navigation drawer open
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
     //Initialize the contents of the Activity's standard options menu. You should place your menu items in to menu.
    //This is only called once, the first time the options menu is displayed. To update the menu every time it is displayed,
    // see onPrepareOptionsMenu(Menu).
    {

        getMenuInflater().inflate(R.menu.menu_main, menu);  //gets a MenuInflater(Context c) with this context and inflate
        // inflate(int menuRes, Menu menu)
       // Inflate a menu hierarchy from the specified XML resource.

        MenuItem menuItem=menu.findItem(R.id.action_share);  //To access an item from menu we use menu item
        shareActionProvider=(ShareActionProvider)menuItem.getActionProvider();
        setIntent("hello from the other side");
        return super.onCreateOptionsMenu(menu);
    }

    public void setIntent(String str)
    {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,str);
shareActionProvider.setShareIntent(intent);  //Sets an intent with information about the share action.

        // FOR IMAGE
   //     Intent shareIntent = new Intent(Intent.ACTION_SEND);
 //shareIntent.setType("image/*");
 //Uri uri = Uri.fromFile(new File(getFilesDir(), "foo.jpg"));
 //shareIntent.putExtra(Intent.EXTRA_STREAM, uri.toString());
       // shareActionProvider.setShareIntent(shareIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
switch(item.getItemId())
        {

            case R.id.action_settings:
                Intent intent=new Intent(this,Settings.class);
                startActivity(intent);
                return true;

            case R.id.action_exit:
                alertD();
            return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }



    public void alertD()
    {
        // 1. Instantiate an AlertDialog.Builder with its constructor

    //    AlertDialog.Builder builder = new AlertDialog.Builder(this);
// 2. Chain together various setter methods to set the dialog characteristics



// 3. Get the AlertDialog from create()
       // AlertDialog dialog = builder.create();


       // AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);  //Creates a builder for an alert dialog that uses the default alert dialog theme
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this,5);


        builder.setMessage(R.string.dialog_message)                 // 	AlertDialog.Builder(Context context, int themeResId) for themes
                .setTitle(R.string.dialog_title).setIcon(R.drawable.ic_screen_lock_landscape_black_48dp);

// Add the buttons
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
                //    android.os.Process.killProcess(android.os.Process.myPid());﻿
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
// Set other dialog properties

// Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void here(View view)
    {
        alertD();
    }




    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position)
    {
        Fragment fragment;

        switch(position) {

            default:
             //  fragment=new TopFragment();
                break;
        }

    }


    @Override  //used with ActionBarDrawerToggle   Call syncState() from your Activity's
    // onPostCreate to synchronize the indicator with the state of the linked DrawerLayout after onRestoreInstanceState has occurred.
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    //Called by the system when the device configuration changes while your activity is running. Note that this will
    // only be called if you have selected configurations you would like to handle with the configChanges attribute in your
    // manifest. If any configuration change occurs that is not selected to be reported by that attribute, then instead of
    // reporting it the system will stop and restart the activity (to have it launched with the new configuration).

    //  At the time that this function has been called, your Resources object will have
    // been updated to return resource values matching the new configuration Parameters
    //newConfig The new device configuration.

    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);

    }

}
