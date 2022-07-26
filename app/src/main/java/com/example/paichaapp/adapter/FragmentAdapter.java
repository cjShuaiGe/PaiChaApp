package com.example.paichaapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.paichaapp.fragment.FutureFragment;
import com.example.paichaapp.fragment.HistoryFragment;
import com.example.paichaapp.fragment.NowFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new NowFragment();
            case 1:
                return new HistoryFragment();
            default:
                return new FutureFragment();
        }
    }

    public int getItemCount(){
        return 3;
    }
}
