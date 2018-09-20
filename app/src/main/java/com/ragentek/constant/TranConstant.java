package com.ragentek.constant;

import android.content.Context;

import com.ragentek.activity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jun.wang on 2018/9/15.
 */

public class TranConstant {
    private static List<String> codeArray;

    public static int[] countryBgArray = new int[]{
            R.drawable.bg_american,
            R.drawable.bg_china,
            R.drawable.bg_england,
            R.drawable.bg_russian
    };

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
