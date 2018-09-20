package com.ragentek.view.fragment.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.ragentek.activity.R;
import com.ragentek.database.SPManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jun.wang on 2018/9/20.
 */

public class BaseFragment extends Fragment {
    public static List<String> codeArray;
    private static Context mContext;
    public static List<String> countryNameArray;
    public static TypedArray countryIconArray;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity().getApplicationContext();
        initData();
    }

    public static int getCurrentCountryIndex() {
        return SPManager.getInstance().getInt(SPManager.CURRENT_COUNTRY,1);
    }

    private void initData() {
        final String[] codeArray = getContext().getResources()
                .getStringArray(R.array.country_name_array);
        countryNameArray = new ArrayList<>();
        for (String str : codeArray) {
            countryNameArray.add(str);
        }
        countryIconArray = mContext.getResources().obtainTypedArray(R.array.country_icons_array);

    }

    public static String getCurrentCountryName() {
        return countryNameArray.get(SPManager.getInstance().getInt(SPManager.CURRENT_COUNTRY,1));
    }

    public static String getCurrentCountryCode() {
        return getCountryCodeArray(mContext).get(getCurrentCountryIndex());
    }

    public static List<String> getCountryCodeArray(Context context) {
        final String[] tempArray = context.getResources()
                .getStringArray(R.array.country_code_array);
        codeArray = new ArrayList<>();
        for (String str : tempArray) {
            codeArray.add(str);
        }
        return codeArray;
    }

}
