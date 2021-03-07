package com.colemichaels.giantbomb.data.repositories

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.colemichaels.giantbomb.API_KEY
import com.colemichaels.giantbomb.LOG_TAG
import com.colemichaels.giantbomb.R
import com.colemichaels.giantbomb.WEB_SERVICE_URL
import com.colemichaels.giantbomb.data.models.GiantBombResponse
import com.colemichaels.giantbomb.data.services.GiantBombService
import com.colemichaels.giantbomb.utilities.FileHelper
import com.colemichaels.giantbomb.utilities.network.NetworkOperationsImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class GiantBombRepository(val app: Application) {

    val giantBombData = MutableLiveData<GiantBombResponse>()

    init {
        refreshData("mario")
    }

    @WorkerThread
    suspend fun callWebService(query: String) {
        if (NetworkOperationsImpl().networkAvailable(app)) {
            Log.i(LOG_TAG, "Calling web service")
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

            val service = retrofit.create(GiantBombService::class.java)

            val serviceData = service.getGiantBombData(
                API_KEY,
                "name:$query",
                "json"
            ).body() ?: GiantBombResponse()

            Log.i(LOG_TAG, "Post Value: $serviceData")

            giantBombData.postValue(serviceData)
        }
    }

    fun refreshData(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService(query)
        }
    }

}