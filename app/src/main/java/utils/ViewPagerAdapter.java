package utils;

/**
 * Created by Jenya on 21.11.2015.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import fragments.WhellFragment;
import fragments.itemListFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    // Tab Titles
    private String tabTitles[] = new String[] { "Live chart", "Items"};
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // fragment commit
    // move fragment transaction to this page and see how to implement it
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
            {
                return new WhellFragment();
            }
            case 1:
            {
                return new itemListFragment();
            }

        }
        return null;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}