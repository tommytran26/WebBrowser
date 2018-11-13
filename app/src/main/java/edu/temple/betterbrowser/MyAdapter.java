package edu.temple.betterbrowser;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import drawable.TabFragment;


public class MyAdapter extends FragmentStatePagerAdapter {

    FragmentArray Fragment_Array;
    FragmentManager Fragment_Manager;




    public MyAdapter(FragmentManager Fragment_Manager, FragmentArray Fragment_Array) {
        super(Fragment_Manager);
        this.Fragment_Manager = Fragment_Manager;
        this.Fragment_Array = Fragment_Array;
    }




    public void newTab(String url) {
        TabFragment tab = TabFragment.newInstance(url);
        Fragment_Array.add(tab);
        notifyDataSetChanged();
    }




    @Override
    public Fragment getItem(int position) {
        Log.d("WebTab_Tag", "Tab Location: "+position);
        TabFragment tab = (TabFragment)Fragment_Array.getFragment(position);
        return tab;
    }




    @Override
    public int getCount() {
        return Fragment_Array.getSize();
    }
}
