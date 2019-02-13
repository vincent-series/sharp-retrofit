package com.coder.zzq.ui.viewpager;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class SaveFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentManager mFragmentManager;

    public SaveFragmentPagerAdapter(FragmentManager fm, int fragmentCount, ViewPager viewPager) {
        super(fm);
        mFragmentManager = fm;
        for (int index = 0; index < fragmentCount; index++) {
            mFragments.add(null);
        }
        viewPager.setOffscreenPageLimit(fragmentCount);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        Fragment fragment = createFragmentIfNecessary(position);
        mFragments.set(position, fragment);
        return fragment;
    }

    public abstract Fragment createFragmentIfNecessary(int position);

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public List<Fragment> getFragments() {
        return mFragments;
    }

    @Override
    public Parcelable saveState() {
        Bundle bundle = new Bundle();
        for (int index = 0; index < mFragments.size(); index++) {
            mFragmentManager.putFragment(bundle, getClass().getSimpleName() + index, mFragments.get(index));
        }
        mFragments.clear();
        return bundle;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        mFragments.clear();
        Bundle bundle = (Bundle) state;
        bundle.setClassLoader(loader);
        Set<String> keys = bundle.keySet();
        for (String key : keys) {
            Fragment fragment = mFragmentManager.getFragment(bundle, key);
            int index = Integer.parseInt(key.substring(getClass().getSimpleName().length()));
            while (mFragments.size() <= index) {
                mFragments.add(null);
            }
            mFragments.set(index, fragment);
        }
    }
}
