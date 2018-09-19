package com.ragentek.view.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ragentek.activity.R;
import com.ragentek.database.SPManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jun.wang on 2018/9/13.
 */

public class PageOneFragment extends Fragment {

    private List<Map<String, Object>> dataList;
    private TextView timeView;
    private TextView dateView;
    private Spinner countrySpinner;
    private updateBg listerner;
    private Context mContext;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pageone_layout, container, false);
        mContext = getActivity();
        initSpinnerData();
     
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                mContext, initSpnnerData(), R.layout.spinner_item_layout,
                new String[] { "img", "text" }, new int[] {
                R.id.image_view, R.id.text_view });

        timeView = view.findViewById(R.id.time_view);
        dateView = view.findViewById(R.id.date_view);

        countrySpinner = view.findViewById(R.id.country_spinner_view);
        // 声明一个ArrayAdapter用于存放简单数据
       /* ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                mContext, android.R.layout.simple_spinner_item, getSpinnerData());
        countrySpinner.setAdapter(spinnerAdapter);*/

        countrySpinner.setAdapter(simpleAdapter);

        int currentCountry = SPManager.getInstance().getInt(SPManager.CURRENT_COUNTRY, 0);
        countrySpinner.setSelection(currentCountry);
        countrySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // 在选中之后触发
                Toast.makeText(mContext,
                        parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
                //listerner.changeWallpaper(position);   // don't change
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        getDate();
        new TimeThread().start();

        return view;
    }

    public void setListerner(updateBg listerner) {
        this.listerner = listerner;
    }

    private List<String> getSpinnerData() {
        // 数据源
        List<String> dataList = new ArrayList<String>();
        dataList.add("美国");
        dataList.add("中国");
        dataList.add("英国");
        dataList.add("俄罗斯");
        return dataList;
    }


    public List<Map<String, Object>> initSpnnerData() {
        //生成数据源
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        //每个Map结构为一条数据，key与Adapter中定义的String数组中定义的一一对应。
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

    private void initSpinnerData() {
        final String[] codeArray = getContext().getResources()
                .getStringArray(R.array.country_string_array);
        countryStrArray = new ArrayList<>();
        for (String str : codeArray) {
            countryStrArray.add(str);
        }
        countryIconArray = mContext.getResources().obtainTypedArray(R.array.country_icons_array);
    /*    TypedArray profileArray = mContext.getResources().obtainTypedArray(R.array.language_profile_picture_array);
        if (position >= 0 && position < profileArray.length()) {
            TextView tv = (TextView) v;
            int id = profileArray.getResourceId(position, 0);
            tv.setCompoundDrawablesWithIntrinsicBounds(mContext.getDrawable(id), null, null, null);
            tv.setCompoundDrawablePadding(80);
        }*/
    }

    private void getDate() {
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String data = format.format(date);
        String dateStr = data.substring(0, 10);
        String timeStr = data.substring(11);

        dateView.setText(dateStr);
        timeView.setText(timeStr);
    }


    class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;  //消息(一个整型值)
                    mHandler.sendMessage(msg);// 每隔1秒发送一个msg给mHandler
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    long time = System.currentTimeMillis();
                    Date date = new Date(time);
                    // SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 EEEE");
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                    String data = format.format(date);
                    timeView.setText(data); //更新时间
                    break;
                default:
                    break;

            }
        }
    };

    public interface updateBg {
        public void changeWallpaper(int index);
    }
}
