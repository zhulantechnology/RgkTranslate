package com.ragentek.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.ragentek.activity.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jun.wang on 2018/9/13.
 */

public class PageTwoFragment extends Fragment {
    private GridView secondGridView;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pagetwo_layout, container, false);
        secondGridView = (GridView) view.findViewById(R.id.second_grid_view);

        //初始化数据
        initData();

        String[] from={"img","text"};
        int[] to={R.id.img,R.id.text};
        adapter=new SimpleAdapter(getActivity(), dataList, R.layout.gridview_item, from, to);
        secondGridView.setAdapter(adapter);

        return view;
    }



    void initData() {
        //图标
        int icno[] = { R.drawable.fontmanager,
                R.drawable.compass,R.drawable.calculator,R.drawable.club };
        //图标下的文字
        String name[]={
                getContext().getResources().getString(R.string.photo_translation),
                getContext().getResources().getString(R.string.map),
                getContext().getResources().getString(R.string.exanchange),
                getContext().getResources().getString(R.string.voice_wizard)
        };
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i <icno.length; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("img", icno[i]);
            map.put("text",name[i]);
            dataList.add(map);
        }
    }
}
