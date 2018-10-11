package com.appsflyer.support.newspicker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebViewClient
import com.appsflyer.AppsFlyerLib
import com.appsflyer.support.newspicker.R
import kotlinx.android.synthetic.main.activity_web_view.*

class ArticleActivity: AppCompatActivity() {
    companion object {
        val ARTICLE_ID = "ARTICLE_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        setTitle(R.string.artile_detail)
        webView.webViewClient = ArticleWebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.loadUrl(getUrl())
        AppsFlyerLib.getInstance().sendDeepLinkData(this)
    }


    private fun getUrl(): String? {
        val newsHost = "https://support.appsflyer.com/hc/ja/articles/"
        intent.getStringExtra(ARTICLE_ID)?.let {
            title = getString(R.string.artile_detail)+ " ARTICLE_ID: " + it;
            return newsHost + it }
        intent.data?.getQueryParameter("aid")?.let {
            title = getString(R.string.artile_detail)+ " aid: " +it;
            return newsHost + it }
        intent.data?.getQueryParameter("url")?.let {
            title = getString(R.string.artile_detail)+ " url:" +it;
            return it }
        return null
    }
    class ArticleWebViewClient: WebViewClient() {

    }
}