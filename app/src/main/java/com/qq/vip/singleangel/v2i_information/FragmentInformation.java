package com.qq.vip.singleangel.v2i_information;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by singl on 2018/4/16.
 */

public class FragmentInformation extends Fragment{
    private static final String ARG_SECTION_NUMBER = "section_number";

    public FragmentInformation(){

    }

    public static FragmentInformation newInstance(int sectionNumber) {
        FragmentInformation fragment = new FragmentInformation();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_information, container, false);


        return rootView;

    }
}
