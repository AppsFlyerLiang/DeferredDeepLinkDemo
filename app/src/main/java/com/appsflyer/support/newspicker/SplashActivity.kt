package com.appsflyer.support.newspicker

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.appsflyer.support.newspicker.R

class SplashActivity : AppCompatActivity() {
    private lateinit var timerHandler: Handler

    var count: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startChecking()
    }

    private fun checkReady() : Boolean {
        if(MyApplication.isAttributed){
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
            return true
        }
        return false
    }
    private fun startChecking() {
        timerHandler = Handler(Handler.Callback {
            if(checkReady() == false) {
                count++
                timerHandler.sendEmptyMessageDelayed(1, 500)
            }
            return@Callback true
        })
        timerHandler.sendEmptyMessage(1)
    }

    override fun onStop() {
        super.onStop()
        Log.d("SplashActivity","[onResume]")
        timerHandler.removeMessages(1)
    }
    override fun onResume() {
        super.onResume()
        Log.d("SplashActivity","[onResume]")
        if(MyApplication.isDeferredDeepLink || MyApplication.isAttributed){
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
