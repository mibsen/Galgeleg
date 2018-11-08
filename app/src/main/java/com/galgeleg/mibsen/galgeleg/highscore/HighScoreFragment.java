package com.galgeleg.mibsen.galgeleg.highscore;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.galgeleg.mibsen.galgeleg.R;

public class HighScoreFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewPager viewPager = new ViewPager(getContext());

        viewPager.setId(R.id.viewPager);

        viewPager.setAdapter(new HighscoreViewPagerAdapter(getChildFragmentManager()));
        viewPager.setPageTransformer(false, new ZoomOutPageTransformer());

        PagerSlidingTabStrip pagerSlidingTabStrip = new PagerSlidingTabStrip(getContext());
        pagerSlidingTabStrip.setViewPager(viewPager);

        LinearLayout ll = new LinearLayout(getContext());
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(pagerSlidingTabStrip);
        ll.addView(viewPager);
        ((LinearLayout.LayoutParams) viewPager.getLayoutParams()).weight = 1;

        return ll;
    }

    private class HighscoreViewPagerAdapter extends FragmentPagerAdapter {
        public HighscoreViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0:
                    return "Score";
                case 1:
                    return "Level";
            }
            return "Error";
        }

        @Override
        public Fragment getItem(int position) {

            Fragment f = new HighScoreList();

            Bundle b = new Bundle();
            b.putInt("position", position);
            f.setArguments(b);

            return f;
        }
    }
}
