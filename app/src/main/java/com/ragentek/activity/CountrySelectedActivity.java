package com.ragentek.activity;

import android.os.Bundle;

import com.ragentek.common.ModuleBaseActivity;
import com.ragentek.view.fragment.CountrySelectedFragment;

/**
 * Created by Jun.wang on 2018/9/20.
 */

public class CountrySelectedActivity extends ModuleBaseActivity {
    @Override
    protected void onInit(Bundle savedInstanceState) {
        showTitleBar();
        setTitle("Country Select");
        replaceFragment(new CountrySelectedFragment());
    }
}
