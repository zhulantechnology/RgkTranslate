package com.ragentek.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.ragentek.activity.base.BaseActivity;
import com.ragentek.adapter.PagesAdapter;
import com.ragentek.database.SPManager;
import com.ragentek.view.fragment.PageOneFragment;
import com.ragentek.view.fragment.PageTwoFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.ragentek.constant.TranConstant.countryBgArray;

public class HomeActivity extends BaseActivity implements ViewPager.OnPageChangeListener,
        PageOneFragment.updateBg {

    private ViewPager viewPager;
    private PagesAdapter adapter;
    private List<Fragment> list;
    private RelativeLayout mainLayout;

    // 提示icon
    private ImageView[] tips;
    private ViewGroup indicationsGroup;
    private LinkedList<String> linkedList = new LinkedList<String>();

    private SPManager spManager;


private Map map;
    private void initBgs() {
        map=new HashMap();
        map.put("R.drawable.bg_american",R.drawable.bg_american);
        map.put("R.drawable.bg_american",R.drawable.bg_china);
        map.put("R.drawable.bg_american",R.drawable.bg_england);
        map.put("R.drawable.bg_american",R.drawable.bg_russian);
        getResources().getIdentifier("bg_american","drawable","com.ragentek.activity");

       // menu.bitmap = BitmapFactory.decodeResource(getResources(),map.get("R.drawable.menu_"+01));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);
        mainLayout = findViewById(R.id.main_layout);
        spManager = SPManager.getInstance();
       int currentCountry = spManager.getInt(SPManager.CURRENT_COUNTRY, 0);
        Log.e("XXX", "ONCREATE---currentCountry-----: "+ currentCountry);
        mainLayout.setBackgroundResource(countryBgArray[currentCountry]);

        indicationsGroup= (ViewGroup) findViewById(R.id.indication_layout);
        viewPager = (ViewPager) findViewById(R.id.home_view_pager);

        list = new ArrayList<Fragment>();
        PageOneFragment pageOneFragment = new PageOneFragment();
        pageOneFragment.setListerner(this);
        list.add(pageOneFragment);
        list.add(new PageTwoFragment());

        adapter = new PagesAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);

       // addIndications();
    }

    // 将提示当前页面的点加入到ViewGroup中
    private void addIndications() {
        tips = new ImageView[2];
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(this);
            // 设置“点点”的宽高
            imageView.setLayoutParams(new ViewGroup.LayoutParams(30, 30));
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    new ViewGroup.LayoutParams(30, 30));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            layoutParams.bottomMargin = 50;
            indicationsGroup.addView(imageView, layoutParams);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        
    }

    @Override
    public void onPageSelected(int position) {
       // hightlightCurrIndication(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    // 高亮当前页提示icon
    private void hightlightCurrIndication(int selectItems) {
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }
    enum country_list {
        china,
        usa,
        russian,
        england
    }
    @Override
    public void changeWallpaper(int index) {
        
        mainLayout.setBackgroundResource(countryBgArray[index]);
        spManager.putInt(SPManager.CURRENT_COUNTRY, index);
    }
}
