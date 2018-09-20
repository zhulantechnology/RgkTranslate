package com.ragentek.common;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by Jun.wang on 2018/9/20.
 */

public class ModuleBaseFragment extends Fragment {
    private ModuleBaseActivity mModuleActivity;

    public ModuleBaseFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModuleActivity = (ModuleBaseActivity) getActivity();
    }

    public void jumpToFragment(ModuleBaseFragment frag) {
        mModuleActivity.jumpToFragment(frag);
    }

    public void jumpToFragment(Fragment frag) {
        mModuleActivity.jumpToFragment(frag);
    }

    public void setTitle(int resId) {
        mModuleActivity.setTitle(resId);
    }

    public void showTitleBar() {
        mModuleActivity.showTitleBar();
    }

    public void hideTitleBar() {
        mModuleActivity.hideTitleBar();
    }

    public void setTitle(String title) {
        mModuleActivity.setTitle(title);
    }
}





















