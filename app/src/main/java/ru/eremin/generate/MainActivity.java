package ru.eremin.generate;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.appbar.AppBarLayout;

import java.util.Objects;

import ru.eremin.generate.data.generate.Generator;
import ru.eremin.generate.interfaces.InterfaceList;

public class MainActivity extends AppCompatActivity {
    private NavController mNavController;
    private Generator generator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        setSupportActionBar(toolbar);
        NavigationUI.setupActionBarWithNavController(this, mNavController);
        // Запуск потока генерации данных
    }

    @Override
    protected void onStart() {
        super.onStart();
        generator = new Generator(this);
        generator.startGenerator();
    }

    @Override
    protected void onStop() {
        super.onStop();
        generator.stopGenerator();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:
                mNavController.navigate(R.id.SettingFragment);
                return true;
            case android.R.id.home:
                if(Navigation.findNavController(this, R.id.nav_host_fragment).getCurrentDestination().getId() == R.id.PeopleFragment){
                    Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(false);
                    Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
                }else{
                    Navigation.findNavController(this, R.id.nav_host_fragment).popBackStack();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
