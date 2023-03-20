package com.example.lamthuasm_duanmau.Menu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.lamthuasm_duanmau.LoginActivity;
import com.example.lamthuasm_duanmau.R;
import com.example.lamthuasm_duanmau.databinding.ActivityMenuNguoidungBinding;

public class MenuNguoiDung extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuNguoidungBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityMenuNguoidungBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMenuNguoidung.toolbar1);
        setTitle(R.id.nav_thoat);
        DrawerLayout drawer = binding.drawerLayoutNguoidung;
        NavigationView navigationView = binding.navViewnguoidung;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_homenguoidung, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_taotaikhoan,R.id.nav_thoat)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_nguoidung);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(NavController controller, NavDestination destination, Bundle arguments) {
                if(destination.getId() == R.id.nav_thoat){
                    SharedPreferences.Editor editor=getSharedPreferences("login_statustv",MODE_PRIVATE).edit();
                    editor.putBoolean("isLoggedIntv",false);
                    editor.apply();
                    Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_nguoidung);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
