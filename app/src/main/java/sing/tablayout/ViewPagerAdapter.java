package sing.tablayout;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<ViewPagerBean> list = new ArrayList<>();
    public ViewPagerAdapter(FragmentManager fm,List<ViewPagerBean> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public MyFragment getItem(int position) {
        return list.get(position).fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).title;
    }
}