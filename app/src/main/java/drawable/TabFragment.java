package drawable;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import edu.temple.betterbrowser.MyWebViewClient;
import edu.temple.betterbrowser.R;

public class TabFragment extends Fragment {
    private OnFragmentInteractionListener URL_Listener;
    private WebView webView;
    private String url = "";


    private static final String URL_ARGUMENT = "URL_ARGUMENT";

    public TabFragment() {
        // Required empty public constructor
    }




    public static TabFragment newInstance(String url) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();

        args.putString(URL_ARGUMENT, url);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tab, container, false);
        this.webView = v.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient(this));
        return v; //inflating the layout holding the fragment
    }




    @Override
    public void onStart(){
        super.onStart();
        if(url.isEmpty())
            url = getArguments().getString(URL_ARGUMENT);

        if(url.isEmpty())
            url = "http://google.com";

        reloadPage();
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            URL_Listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + ">>>OnFragmentInteractionListener");
        }
    }




    @Override
    public void onDetach() {
        super.onDetach();
        URL_Listener = null;
    }




    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String newUrl);
    }




    public void setUrl(String url){
        Log.d("WebTab_Tag", ""+url);
        this.url = url;

        if(URL_Listener != null)
            URL_Listener.onFragmentInteraction(url);
    }




    public String getUrl(){ return url; }
    public void loadURL(String url){
        this.url = url;
        Log.d("WebTab_Tag", ""+url);
        webView.loadUrl(url);
    }




    public void reloadPage(){
        Log.d("WebTab_Tag", ""+url);
        if(!url.isEmpty())
            webView.loadUrl(url);
    }
}
