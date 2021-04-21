package org.jbch.cab;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private Context mContext;
    private Menu mMenuAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                NavController navController = Navigation.findNavController((Activity) mContext, R.id.nav_host_fragment);
                navController.navigate(item.getItemId());

                mMenuAction.setGroupVisible(R.id.register_group, item.getItemId() == R.id.nav_christianregister);
                mMenuAction.setGroupVisible(R.id.christian_group, item.getItemId() == R.id.nav_christiansearch);

                return true;
            }
        });

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_worldmission, R.id.nav_yearplan, R.id.nav_parishguide, R.id.nav_notice, R.id.nav_appinfo,
                R.id.nav_christiansearch, R.id.nav_preacherlist, R.id.nav_executivelist, R.id.nav_christianregister, R.id.nav_carinquiry)
                .setDrawerLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

//        bottomNavigatinView menu dynamic show/hide
//        bottomNavigationView.getMenu().removeItem(R.id.nav_carinquiry);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        mMenuAction = menu;
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }
}