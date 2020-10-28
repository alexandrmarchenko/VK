package com.example.vk

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        auth.settings.javaScriptEnabled = true
        auth.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        auth.isVerticalScrollBarEnabled = false

        auth.webViewClient = VKWebViewClient()
        auth.loadUrl(Auth.getUrl(Constants.api_key, Auth.getSettings()))
    }

    private inner class VKWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            if (url?.contains("oauth.vk.com/blank.html#") == true) {
                parseURL(url)
            }
        }
    }

    private fun parseURL(url: String) {
        if (!url.contains("error")) {
            val intent = Intent()
            intent.putExtra("user_id", Auth.ParseRedirectUrl(url))
            setResult(Activity.RESULT_OK, intent)
        }
        finish()
    }
}