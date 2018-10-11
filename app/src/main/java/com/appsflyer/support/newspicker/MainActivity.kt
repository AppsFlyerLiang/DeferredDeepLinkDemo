package com.appsflyer.support.newspicker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.appsflyer.support.newspicker.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnGo.setOnClickListener {
            inputAId?.text?.let {aid ->
                val intent = Intent(this@MainActivity, ArticleActivity::class.java)
                intent.putExtra(ArticleActivity.ARTICLE_ID, aid.toString())
                startActivity(intent)
            } ?: alertNoInput()
        }
    }

    private fun alertNoInput() {
        AlertDialog.Builder(this)
                .setMessage(R.string.article_id_not_set)
                .setPositiveButton(android.R.string.ok) { d, _ -> d.dismiss() }
                .create().show()
    }
}