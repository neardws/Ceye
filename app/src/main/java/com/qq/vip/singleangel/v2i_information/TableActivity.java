package com.qq.vip.singleangel.v2i_information;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TableActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;/**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        try {
            tabLayout.getTabAt(0).setText(getString(R.string.table_packageName));
            tabLayout.getTabAt(1).setText(getString(R.string.table_information));
            tabLayout.getTabAt(2).setText(getString(R.string.table_control));
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0:
                    return FragmentPackageName.newInstance(0);
                case 1:
                    return FragmentInformation.newInstance(1);
                case 2:
                    return FragmentControl.newInstance(2);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        String title;
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    title = getString(R.string.table_packageName);
                    break;
                case 1:
                    title = getString(R.string.table_information);
                    break;
                case 2:
                    title = getString(R.string.table_control);
                    break;
                default:
                    break;
            }
            return null;
        }


    }

}
