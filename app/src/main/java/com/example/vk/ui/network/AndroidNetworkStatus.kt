package com.example.vk.ui.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkRequest
import android.util.Log
import com.example.vk.VKApplication
import com.example.vk.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

class AndroidNetworkStatus : INetworkStatus {

    private val TAG = AndroidNetworkStatus::class.java.simpleName

    private var statusObject = BehaviorSubject.create<Boolean>()

    init {
        statusObject.onNext(false)
        val connectivityManager =
            VKApplication.INSTANCE.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkRequest = NetworkRequest.Builder().build()

        connectivityManager.registerNetworkCallback(networkRequest, object : NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                if (VKApplication.DEBUG) {
                    Log.d(TAG, "onAvailable")
                }
                statusObject.onNext(true)
            }

            override fun onUnavailable() {
                super.onUnavailable()
                if (VKApplication.DEBUG) {
                    Log.d(TAG, "onUnavailable")
                }
                statusObject.onNext(false)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                if (VKApplication.DEBUG) {
                    Log.d(TAG, "onLost")
                }
                statusObject.onNext(false)
            }
        })
    }

    override fun isOnline(): Observable<Boolean> {
        return statusObject
    }

    override fun isOnlineSingle(): Single<Boolean> {
        return statusObject.first(false)
    }

}