package com.example.vk.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vk.Constants
import com.example.vk.api.VKRetrofitImpl
import com.example.vk.responseData.ProfileInfo
import com.example.vk.responseData.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserModel(
    private val accessToken: String?,
    private val liveDataForViewToObserve: MutableLiveData<ProfileInfo?> = MutableLiveData()
) : ViewModel() {

    fun getData(): LiveData<ProfileInfo?> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    private var pInfo: ProfileInfo? = null

    private fun sendServerRequest() {

        VKRetrofitImpl.instance.getProfileInfo(accessToken, Constants.api_ver).enqueue(object :
            Callback<ProfileInfo> {
            override fun onFailure(call: Call<ProfileInfo>, t: Throwable) {
                liveDataForViewToObserve.value = null
            }

            override fun onResponse(call: Call<ProfileInfo>, response: Response<ProfileInfo>) {
                if (response.isSuccessful && response.body() != null) {
                    pInfo = response.body()
                    getUserInfo()
                } else {
                    liveDataForViewToObserve.value = null
                }
            }
        })
    }

    private fun getUserInfo() {
        val fields = "photo_id, verified, sex, bdate, city, country, home_town, has_photo, photo_50, photo_100, photo_200_orig, photo_200, photo_400_orig, photo_max, photo_max_orig, online, domain, has_mobile, contacts, site, education, universities, schools, status, last_seen, followers_count, common_count, occupation, nickname, relatives, relation, personal, connections, exports, activities, interests, music, movies, tv, books, games, about, quotes, can_post, can_see_all_posts, can_see_audio, can_write_private_message, can_send_friend_request, is_favorite, is_hidden_from_feed, timezone, screen_name, maiden_name, crop_photo, is_friend, friend_status, career, military, blacklisted, blacklisted_by_me, can_be_invited_group"
        VKRetrofitImpl.instance.getUsers(accessToken, Constants.api_ver, pInfo?.response?.id.toString(), fields).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                liveDataForViewToObserve.value = pInfo
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful && response.body() != null) {
                        pInfo?.response?.userInfo = response.body()!!.response?.get(0)
                        liveDataForViewToObserve.value = pInfo
                    } else {
                        liveDataForViewToObserve.value = pInfo
                    }
            }
        })
    }

}


