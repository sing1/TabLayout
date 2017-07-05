package sing.tablayout;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.width;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        List<String> tabList = getTab();

        List<ViewPagerBean> list = new ArrayList<>();
        for (int i = 0; i < tabList.size(); i++) {
            ViewPagerBean bean = new ViewPagerBean(new MyFragment(tabList.get(i)),tabList.get(i));
            list.add(bean);
        }

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), list));
        tabLayout.setupWithViewPager(viewPager);

//        tabLayout.removeAllTabs();// 呸！就你会 remove 是吧？
//        for (int i = 0; i < tabList.size(); i++) {
//            View view = LayoutInflater.from(this).inflate(tab,null);
//            ((ImageView)view.findViewById(R.id.iv)).setImageDrawable(ContextCompat.getDrawable(this,R.mipmap.ic_launcher));
//            ((TextView)view.findViewById(R.id.tv)).setText(tabList.get(i));
//            tabLayout.addTab(tabLayout.newTab().setCustomView(view));
//        }

        setIndicator(tabLayout,10,10);

        tabLayout.getTabAt(0).select();
    }

    private List<String> getTab(){
        List<String> list = new ArrayList<>();
        list.add("新闻");
        list.add("体育");
        list.add("科技");
        list.add("直播");
        list.add("汽车");
        list.add("公益");
        list.add("娱乐");
        list.add("财经");
        list.add("时尚");
        list.add("房产");
        list.add("旅游");
        list.add("艺术");
        return list;
    }

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.MATCH_PARENT);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
}