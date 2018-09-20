package com.ragentek.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ragentek.activity.R;
import com.ragentek.view.fragment.base.BaseFragment;

/**
 * Created by Jun.wang on 2018/9/13.
 */

public class PageTwoFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pagetwo_layout, container, false);
        return view;
    }


}
