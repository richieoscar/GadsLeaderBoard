package com.example.gadspracticeproject;

import android.content.Context;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public ViewPagerAdapter(Context context, @NonNull FragmentManager fm, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                LearnerFragment learnerFragment = new LearnerFragment();
                return learnerFragment;
            case 1:
                SkillIqFragment skillIqFragment = new SkillIqFragment();
                return  skillIqFragment;
            default:
                return  null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
