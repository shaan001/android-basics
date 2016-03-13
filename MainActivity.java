package com.hybrid.tech.revisionapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
}
