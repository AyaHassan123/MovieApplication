package com.example.moviesapplication.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;


import android.os.Bundle;


import com.example.moviesapplication.R;
import com.example.moviesapplication.adapter.FragmentAdapter;
import com.example.moviesapplication.databinding.ActivityHomeBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        binding = DataBindingUtil.setContentView(HomeActivity.this,R.layout.activity_home);
        setFragment();
    }

    private void setFragment() {
        binding.viewPager.setUserInputEnabled(false);
        binding.viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),getLifecycle()));

        binding.tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());

            }
        });

        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                //sendUserInformation();
                binding.tabs.selectTab(binding.tabs.getTabAt(position));
            }
        });
    }
//    @Override
//    public void onBackPressed() {
//        if (binding.viewPager.getCurrentItem() == 0) {
//            // If the user is currently looking at the first step, allow the system to handle the
//            // Back button. This calls finish() on this activity and pops the back stack.d
//            super.onBackPressed();
//        } else {
//            // Otherwise, select the previous step.
//            binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() - 1);
//        }
//    }
}