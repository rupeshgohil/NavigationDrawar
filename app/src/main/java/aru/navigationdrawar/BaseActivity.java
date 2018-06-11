package aru.navigationdrawar;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
public class BaseActivity extends AppCompatActivity {
     DrawerLayout fullview;
     TextView tv_username,tv_email;
     ImageView imgPig;
     View view;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


    }

    @Override
    public void setContentView(int layoutResID) {

        fullview = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        DrawerLayout drawer = (DrawerLayout) fullview.findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) fullview.findViewById(R.id.nav_view);
        FrameLayout activityContainer = (FrameLayout) fullview.findViewById(R.id.activity_content);
        toolbar = (Toolbar) fullview.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();
                item.setChecked(true);
                if (id == R.id.nav_camera) {
                    Intent mIntent = new Intent(BaseActivity.this,CameraActivity.class);
                    startActivity(mIntent);
                } else if (id == R.id.nav_gallery) {
                    Intent mIntent = new Intent(BaseActivity.this,GalleryActivity.class);
                    startActivity(mIntent);
                } else if (id == R.id.nav_slideshow) {
                    Intent mIntent = new Intent(BaseActivity.this,SlideshowActivity.class);
                    startActivity(mIntent);
                } else if (id == R.id.nav_manage) {
                    Intent mIntent = new Intent(BaseActivity.this,ManageActivity.class);
                    startActivity(mIntent);
                } else if (id == R.id.nav_share) {
                    Intent mIntent = new Intent(BaseActivity.this,ShareActivity.class);
                    startActivity(mIntent);
                } else if (id == R.id.nav_send) {
                    Intent mIntent = new Intent(BaseActivity.this,SendActivity.class);
                    startActivity(mIntent);
                }else{
                    Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_SHORT).show();
                    Intent mIntent = new Intent(BaseActivity.this,HomeActivity.class);
                    mIntent.putExtra("logout","logout");
                    startActivity(mIntent);
                }
                DrawerLayout drawer = (DrawerLayout) fullview.findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        view = navigationView.getHeaderView(0);
        tv_email = (TextView)view.findViewById(R.id.txtemail);
        tv_username = (TextView)view.findViewById(R.id.txtusername);
        imgPig = (ImageView)view.findViewById(R.id.Profilepic);
        tv_email.setText(SecureSharedPreference.getPreference("email","",BaseActivity.this));
        tv_username.setText(SecureSharedPreference.getPreference("UserName","",BaseActivity.this));
        Glide.with(this).load(SecureSharedPreference.getPreference("personalphotourl","",BaseActivity.this)).thumbnail(0.5f).into(imgPig);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullview);
    }
    public void setTitle(String str){
        Log.e("Title",str);
        getSupportActionBar().setTitle(str);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        toggle.onConfigurationChanged(newConfig);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) fullview.findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
