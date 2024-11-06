package com.example.myapplication;

import android.os.Bundle;
<<<<<<< HEAD
import android.view.View;

import androidx.activity.EdgeToEdge;
=======
>>>>>>> e079266fb6246d45237403f51237619325154796
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

<<<<<<< HEAD
        FloatingActionButton btnCamera = findViewById(R.id.btnCamera);
        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);
=======
        replaceFragment(new HomeFragment());  // Khởi tạo fragment mặc định
>>>>>>> e079266fb6246d45237403f51237619325154796

        btnCamera.setOnClickListener(v -> {
            Intent intentCamera = new Intent(MainActivity.this, CameraActivity.class);
            startActivity(intentCamera);
        });

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                replaceFragment(new HomeFragment());
            } else if (itemId == R.id.navigation_map) {
                replaceFragment(new MapFragment());
            } else if (itemId == R.id.navigation_history) {
                replaceFragment(new HistoryFragment());
            } else if (itemId == R.id.navigation_settings) {
                replaceFragment(new SettingFragment());
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
