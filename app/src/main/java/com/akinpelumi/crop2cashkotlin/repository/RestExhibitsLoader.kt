package com.akinpelumi.crop2cashkotlin.repository

import android.os.AsyncTask
import android.util.Log
import com.akinpelumi.crop2cashkotlin.model.ExhibitModel
import com.akinpelumi.crop2cashkotlin.model.ExhibitsLoader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import java.util.ArrayList
import java.util.concurrent.TimeUnit


class RestExhibitsLoader(callBack: ExhibitsLoader) :
    AsyncTask<String?, Void?, List<ExhibitModel>?>() {
    private val callBack: ExhibitsLoader
    private fun fetchAllExhibitAsyncTask(): List<ExhibitModel>? {
        try {
            val url = "https://my-json-server.typicode.com/Reyst/exhibit_db/list"
            val client = OkHttpClient()
                .newBuilder()
                .connectTimeout(0, TimeUnit.SECONDS)
                .build()
            val request = Request.Builder()
                .url(url)
                .method("GET", null) //.addHeader("Authorization", appToken)
                .build()
            val response = client.newCall(request).execute()
            assert(response.body() != null)
            val callResponse = response.body()!!.string()
            val statusCode = response.code()
            val gson = Gson()
            //return gson.fromJson(callResponse, TicketModel.class);
            val userListType =
                object : TypeToken<ArrayList<ExhibitModel?>?>() {}.type

            //return gson.fromJson(callResponse, (Type) ExhibitModel[].class);
            return gson.fromJson<ArrayList<ExhibitModel>>(callResponse, userListType)
        } catch (ex: Exception) {
            Log.d("NotifyException::::", ex.message!!)
        }
        return null
    }

//    protected override fun doInBackground(vararg strings: String): List<ExhibitModel>? {
//        try {
//            return fetchAllExhibitAsyncTask()
//        } catch (ex: Exception) {
//            Log.d("NotifyException::::", ex.message!!)
//        }
//        return null
//    }

    override fun onPostExecute(exhibits: List<ExhibitModel>?) {
        callBack.getExhibitList(exhibits)
    }

    init {
        this.callBack = callBack
    }

    override fun doInBackground(vararg p0: String?): List<ExhibitModel>? {
        try {
            return fetchAllExhibitAsyncTask()
        } catch (ex: Exception) {
            Log.d("NotifyException::::", ex.message!!)
        }
        return null
    }
}

