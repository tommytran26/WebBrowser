package edu.temple.betterbrowser;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import drawable.TabFragment;

public class MainActivity extends FragmentActivity implements TabFragment.OnFragmentInteractionListener {

    TextView textView;
    Button backButton;
    Button nextButton;
    Button goButton;
    MyAdapter myAdapter;
    ViewPager myViewPager;
    FragmentManager fragment_manager;
    FragmentArray fragment_array;

    int currentTab = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragment_manager = getSupportFragmentManager();
        fragment_array = new FragmentArray();
        myAdapter = new MyAdapter(fragment_manager, fragment_array);

        myViewPager = (ViewPager)findViewById(R.id.tabFrame);
        myViewPager.setAdapter(myAdapter);
        myViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("WebTab_Tag", ""+position);
                currentTab = position;
                updateButtons();
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        Button bNew = (Button)findViewById(R.id.buttonNewTab);
        bNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewTab("");
                updateButtons();
            }
        });



        backButton = (Button)findViewById(R.id.buttonPrev);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hasPrev())
                    myViewPager.setCurrentItem(myViewPager.getCurrentItem() - 1);
                updateButtons();
            }
        });



        nextButton = (Button)findViewById(R.id.buttonNext);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hasNext())
                    myViewPager.setCurrentItem(myViewPager.getCurrentItem() + 1);
                updateButtons();
            }
        });




        textView = (TextView)findViewById(R.id.editText);
        goButton = (Button)findViewById(R.id.buttonGo);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = textView.getText().toString();
                if(fragment_array.getSize() > 0) {
                    int currentTab = myViewPager.getCurrentItem();
                    ((TabFragment) fragment_array.getFragment(currentTab)).loadURL(url);
                }
            }
        });
        updateButtons();
    }




    @Override
    protected void onResume(){
        super.onResume();
        Log.d("WebTab_Tag", "");
        handleIntent(getIntent());
    }




    @Override
    protected void onNewIntent(Intent intent){
        handleIntent(intent);
    }




    public void handleIntent(Intent intent){
        Uri uri = intent.getData();
        Log.d("WebTab_Tag-intent", "intent: "+(uri != null));

        if(uri != null){
            String url = uri.toString();
            Log.d("WebTab_Tag-intent", "URL: "+url);
            createNewTab(url);
        }
    }



    public void createNewTab(String url){
        currentTab = myAdapter.getCount();
        myAdapter.newTab(url);
        myViewPager.setCurrentItem(currentTab);
    }




    public void updateButtons(){
        backButton.setEnabled(hasPrev());
        nextButton.setEnabled(hasNext());

        boolean atLeastOneTab = fragment_array.getSize() > 0;
        textView.setEnabled(atLeastOneTab);
        goButton.setEnabled(atLeastOneTab);
    }




    public boolean hasPrev(){
        return myViewPager.getCurrentItem() > 0;
    }

    public boolean hasNext(){
        return myViewPager.getCurrentItem() < myAdapter.getCount() - 1;
    }




    @Override
    public void onFragmentInteraction(String newUrl) {
        textView.setText(newUrl);
    }
}
