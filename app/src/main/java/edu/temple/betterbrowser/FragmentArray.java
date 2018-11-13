package edu.temple.betterbrowser;

import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.ArrayList;

public class FragmentArray {
    private ArrayList<Fragment> list;

    public FragmentArray(){
        list = new ArrayList<>();
    }

    public void add(Fragment frag){
        list.add(frag);
    }

    public Fragment getFragment(int index){
        Log.d("WebTab_Tag", "getting fragment at "+index);
        return list.get(index);
    }

    public int getSize(){
        return list.size();
    }

}
