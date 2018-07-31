package com.google.firebase.quickstart.database.fragment;
import com.google.firebase.quickstart.database.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * Created by Belal on 2/3/2016.
 */

//Our class extending fragment
public class Tab2 extends Fragment  {
//    public static final String CONTACT_ID = "_ID";
//    public static final String DISPLAY_NAME = "DISPLAY_NAME";
//    public static final String PHOTO_THUMBNAIL_URI = "PHOTO_THUMBNAIL_URI";
//    public static final String NUMBER = "NUMBER";
//    //	public static final String DISPLAY_NAME = "DISPLAY_NAME";
//    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
//    private static final String[] PROJECTION = new String[]{"display_name", "_id", "lookup"};
//    private GridView listView;
   Button myButton;
    public WebView mWebView;

//    public WebView mWebView;
//   // private LazyAdapter adapter;
//    private Tab2 me = this;

    //Overriden method onCreateView

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.tab2, container, false);
        mWebView = (WebView) v.findViewById(R.id.webview);
        mWebView.loadUrl("https://www.youtube.com/playlist?list=PLmXe0lvPfBbdyUeq0o8QP6_pxb8on6lTk");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        return v;
    }



//    public void onClick(View v) {
//
//        PackageManager pm = getActivity().getPackageManager();
//// Get all methods on the PackageManager
//        Method[] methods = pm.getClass().getDeclaredMethods();
//        for (Method m : methods) {
//            if (m.getName().equals("freeStorage")) {
//                // Found the method I want to use
//                try {
//                    long desiredFreeStorage = 8 * 1024 * 1024 * 1024; // Request for 8GB of free space
//                    m.invoke(pm, desiredFreeStorage, null);
//                } catch (Exception e) {
//                    // Method invocation failed. Could be a permission problem
//                }
//                break;
//            }
//        }
//    }
@Override
public void setMenuVisibility(final boolean visible) {
    super.setMenuVisibility(visible);
    if (visible) {
       Log.d("tab2","shown");
    }
}
}