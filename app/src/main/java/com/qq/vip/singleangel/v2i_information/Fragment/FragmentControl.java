package com.qq.vip.singleangel.v2i_information.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qq.vip.singleangel.v2i_information.R;

/**
 * Created by singl on 2018/4/16.
 */

public class FragmentControl extends Fragment{
    private static final String ARG_SECTION_NUMBER = "section_number";

    public FragmentControl(){

    }

    public static FragmentControl newInstance(int sectionNumber) {
        FragmentControl fragment = new FragmentControl();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_control, container, false);


        return rootView;

    }
}
