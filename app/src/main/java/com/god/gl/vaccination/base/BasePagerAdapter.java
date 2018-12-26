package com.god.gl.vaccination.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author gl
 * @date 2018/7/25
 * @desc
 */
public class BasePagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragmentList;
    public BasePagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);    }

    @Override
    public int getCount() {
          return mFragmentList.size();
    }

}
