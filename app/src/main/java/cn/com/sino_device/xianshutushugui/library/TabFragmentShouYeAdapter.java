package cn.com.sino_device.xianshutushugui.library;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import cn.com.sino_device.xianshutushugui.MainPagerAdapter;

public class TabFragmentShouYeAdapter extends MainPagerAdapter {

    private Context context;
    private List<Fragment> fragments;
    private List<String> strings;
    public TabFragmentShouYeAdapter(List<Fragment> fragments, List<String> strings, FragmentManager fragmentManager, Context context){
        super(fragmentManager);
        this.strings = strings;
        this.context = context;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}