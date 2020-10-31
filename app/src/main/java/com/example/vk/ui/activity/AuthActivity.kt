package com.example.vk.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.vk.Constants
import com.example.vk.R
import kotlinx.android.synthetic.main.activity_auth.*
import java.net.URLEncoder
import java.util.regex.Pattern

class AuthActivity : AppCompatActivity() {

    companion object {
        val KEY_USER_ID = "user_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        auth.settings.javaScriptEnabled = true
        auth.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        auth.isVerticalScrollBarEnabled = false

        auth.webViewClient = VKWebViewClient()
        auth.loadUrl(
            getUrl(
                Constants.api_key,
                getSettings()
            )
        )
    }

    val redirectUrl = "https://oauth.vk.com/blank.html"

    private fun getUrl(api: Int, settings: String): String {
        val url =
            getString(R.string.oauth_url).format(
                api,
                settings,
                URLEncoder.encode(redirectUrl),
                Constants.auth_ver
            )
        return url
    }

    private fun getSettings(): String {
        return getString(R.string.auth_settings)
    }

    private fun parseRedirectUrl(url: String): String? {
        val accessToken =
            extractPattern(url)
        if (accessToken.isNullOrBlank()) {
            return null
        } else {
            return accessToken
        }
    }

    private fun extractPattern(url: String): String? {
        val pattern = Pattern.compile("access_token=(.*?)&")
        val matcher = pattern.matcher(url)
        if (!matcher.find()) {
            return null
        } else {
            return matcher.toMatchResult().group(1)
        }
    }

    private inner class VKWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            if (url?.contains("oauth.vk.com/blank.html#") == true) {
                parseURL(url)
            }
        }

        private fun parseURL(url: String) {
            if (!url.contains("error")) {
                val intent = Intent()
                intent.putExtra(
                    KEY_USER_ID,
                    parseRedirectUrl(url)
                )
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }

}