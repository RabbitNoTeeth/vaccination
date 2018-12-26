package com.god.gl.vaccination.main.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.god.gl.vaccination.R;
import com.god.gl.vaccination.base.BaseActivity;
import com.god.gl.vaccination.base.BasePagerAdapter;
import com.god.gl.vaccination.main.home.fragment.BabyTimeFragment;
import com.god.gl.vaccination.main.home.fragment.PregnantTimeFragment;
import com.god.gl.vaccination.widget.TitleView;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author gl
 * @date 2018/12/7
 * @desc
 */
public class VacciantionRemindActivity extends BaseActivity {
    @BindView(R.id.tabSegment)
    QMUITabSegment mQMUITabSegment;
    @BindView(R.id.contentViewPager)
    ViewPager mViewPager;
    private ArrayList<Fragment> mFragments;
    private BasePagerAdapter mBasePagerAdapter;

    @Override
    protected int getActivityViewById() {
        return R.layout.activity_remind_vacciantion;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        initTabAndPager();


    }

    @Override
    protected void handleData() {
        mTitleView.setRightTitleListener(new TitleView.RightTitleListener() {
            @Override
            public void rightTitleClick() {
                startActivity(new Intent(mContext,TimeActivity.class));
            }
        });

    }

    private void initTabAndPager() {
        mFragments = new ArrayList<>();
        mFragments.add(BabyTimeFragment.newIntance());
        mFragments.add(PregnantTimeFragment.newIntance());
        mBasePagerAdapter = new BasePagerAdapter(getSupportFragmentManager(), mFragments);
        mQMUITabSegment.reset();
        mQMUITabSegment.setHasIndicator(true);
        mQMUITabSegment.setIndicatorPosition(false);
        mQMUITabSegment.setDefaultNormalColor(mContext.getResources().getColor(R.color.gray1));
        mQMUITabSegment.setDefaultSelectedColor(mContext.getResources().getColor(R.color.blue));
        mQMUITabSegment.setIndicatorWidthAdjustContent(true);
        mViewPager.setAdapter(mBasePagerAdapter);
        mViewPager.setCurrentItem(0, false);
        mQMUITabSegment.addTab(new QMUITabSegment.Tab("婴儿时期"));
        mQMUITabSegment.addTab(new QMUITabSegment.Tab("怀孕时期"));
        mQMUITabSegment.setupWithViewPager(mViewPager, false);
        mQMUITabSegment.setMode(QMUITabSegment.MODE_FIXED);
        mQMUITabSegment.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                mQMUITabSegment.hideSignCountView(index);
            }

            @Override
            public void onTabUnselected(int index) {

            }

            @Override
            public void onTabReselected(int index) {
                mQMUITabSegment.hideSignCountView(index);
            }

            @Override
            public void onDoubleTap(int index) {

            }
        });
    }
}
