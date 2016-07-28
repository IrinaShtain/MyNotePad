package shtainyky.com.mynotepad.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import shtainyky.com.mynotepad.Fragments.FragmentForNew;
import shtainyky.com.mynotepad.Fragments.FragmentListForCreated;
import shtainyky.com.mynotepad.R;

public class TabPagerFragmentAdapter extends FragmentPagerAdapter {
    private String[] tabs;
    private Context context;
    public TabPagerFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        tabs = context.getResources().getStringArray(R.array.tabs_array);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new FragmentForNew();
            case 1:
                return new FragmentListForCreated();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
