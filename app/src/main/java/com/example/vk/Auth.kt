package com.example.vk

import java.net.URLEncoder
import java.util.regex.Pattern

class Auth {
    companion object {
        val redirectUrl = "https://oauth.vk.com/blank.html"

        fun getUrl(api: Int, settings: String): String {
            val url="https://oauth.vk.com/authorize?client_id="+api+"&display=mobile&scope="+settings+"&redirect_uri="+URLEncoder.encode(redirectUrl)+"&response_type=token" +
                    "&v="+ Constants.auth_ver
            return url
        }

        fun getSettings(): String {
            //return "notify,friends,photos,audio,video,docs,status,notes,pages,wall,groups,messages,offline,notifications"
            return "friends,notify,photos,photos,audio,video,docs,notes,pages,groups,offline"
        }

        fun ParseRedirectUrl(url: String):String? {
            val accessToken = extractPattern(url)
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
    }
}