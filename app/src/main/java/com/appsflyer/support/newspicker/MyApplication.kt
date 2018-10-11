package com.appsflyer.support.newspicker

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib

class MyApplication: Application(), AppsFlyerConversionListener {
    companion object {
        var isAttributed = false
        var isDeferredDeepLink = false
    }
    override fun onCreate() {
        super.onCreate()
        AppsFlyerLib.getInstance().setCollectIMEI(false)
        AppsFlyerLib.getInstance().setDebugLog(true)
        AppsFlyerLib.getInstance().init("SC6zv6Zb6N52vePBePs5Xo", this)
        AppsFlyerLib.getInstance().startTracking(this)
    }
    override fun onAppOpenAttribution(conversionData: MutableMap<String, String>?) {
    }

    override fun onAttributionFailure(p0: String?) {
    }

    override fun onInstallConversionDataLoaded(conversionData: MutableMap<String, String>?) {
        Log.d(AppsFlyerLib.LOG_TAG, "[onInstallConversionDataLoaded]")
        conversionData?.let { data ->
            data.map{
                Log.d(AppsFlyerLib.LOG_TAG,"key: ${it.key} Value: ${it.value}")

            }
            if(data.get("is_first_launch")=="true"){
                isDeferredDeepLink = true

                data.get("campaign")?.let {c ->
                    if(startActivityByCampaign(c))
                    return@onInstallConversionDataLoaded
                }
                data.get("aid")?.let {aid ->
                    startActivity(Intent(this, ArticleActivity::class.java).also { it.putExtra(ArticleActivity.ARTICLE_ID, aid) })
                    return@onInstallConversionDataLoaded
                }
                data.get("af_dp")?.let { scheme ->
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(scheme)))
                    return@onInstallConversionDataLoaded
                }
                data.get("target_url")?.let { url->
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    return@onInstallConversionDataLoaded
                }
                isDeferredDeepLink = false
            }
            isAttributed = true
        }
    }

    private fun startActivityByCampaign(c: String): Boolean {
        when(c){
            "dp" -> { startActivity(Intent(this, SuccessActivity::class.java)); return true }
            else -> { return false }
        }
    }

    override fun onInstallConversionFailure(p0: String?) {
        Log.d(AppsFlyerLib.LOG_TAG, "[onInstallConversionFailure] ${p0}")
    }
}