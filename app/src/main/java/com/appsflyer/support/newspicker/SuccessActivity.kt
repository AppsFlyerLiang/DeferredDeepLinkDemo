package com.appsflyer.support.newspicker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.appsflyer.support.newspicker.R

class SuccessActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member)
        setTitle(R.string.success)
    }

}