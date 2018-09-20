package com.ragentek.view.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.ragentek.activity.R;
import com.ragentek.common.ModuleBaseFragment;
import com.ragentek.database.SPManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jun.wang on 2018/9/20.
 */

public class CountrySelectedFragment extends ModuleBaseFragment
        implements AdapterView.OnItemClickListener {

    private ListView countryListView;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_counselecte_layout, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        countryListView = view.findViewById(R.id.country_listview);

        initData();

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                mContext, initSpnnerData(), R.layout.spinner_item_layout,
                new String[] { "img", "text" }, new int[] {
                R.id.image_view, R.id.text_view });
        countryListView.setAdapter(simpleAdapter);
        countryListView.setOnItemClickListener(this);
        
    }

    public List<Map<String, Object>> initSpnnerData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0;i < countryStrArray.size();i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", countryIconArray.getResourceId(i, 0));
            map.put("text", countryStrArray.get(i));
            list.add(map);
        }
        return list;
    }

    private List<String> countryStrArray;
    private TypedArray countryIconArray;

    private void initData() {
        final String[] codeArray = mContext.getResources()
                .getStringArray(R.array.country_name_array);
        countryStrArray = new ArrayList<>();
        for (String str : codeArray) {
            countryStrArray.add(str);
        }
        countryIconArray = mContext.getResources().obtainTypedArray(R.array.country_icons_array);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       // String text = (String) ((TextView)view.findViewById(R.id.text_view)).getText();
        //String showText = TranConstant.getCountryCodeArray(mContext).get(position);
        //Toast.makeText(mContext, showText, Toast.LENGTH_LONG).show();
        SPManager.getInstance().putInt(SPManager.CURRENT_COUNTRY,position);
        getActivity().finish();
    }

}
