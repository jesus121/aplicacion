package com.teaching.android.miprimeraapp.Webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.teaching.android.miprimeraapp.R;

public class webViewActivity extends AppCompatActivity {

     private WebView webView = findViewById(R.id.web_view);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Toolbar myToolbar = findViewById(R.id.toolbar5);
        setSupportActionBar(myToolbar);
        WebView myWebView = findViewById(R.id.web_view);
        myWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        String urlToLoad = getIntent().getStringExtra("url");
        myWebView.loadUrl(urlToLoad);


    }

}
