package com.example.paichaapp.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.paichaapp.fragment.ControlFragment;
import com.example.paichaapp.fragment.EquipmentFragment;
import com.example.paichaapp.fragment.FutureFragment;
import com.example.paichaapp.fragment.HistoryFragment;
import com.example.paichaapp.fragment.MineFragment;
import com.example.paichaapp.fragment.NowFragment;

import java.util.ArrayList;
import java.util.List;

public class MainFragmentAdapter extends FragmentStateAdapter {
    public MainFragmentAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new EquipmentFragment();
            case 1:
                return new ControlFragment();
            default:
                return new MineFragment();
        }
    }

    public int getItemCount(){
        return 3;
    }

}
