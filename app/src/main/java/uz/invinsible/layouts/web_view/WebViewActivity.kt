package uz.invinsible.layouts.web_view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.R

class WebViewActivity : AppCompatActivity() {

    var webView: WebView? = null

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view_layout)
        webView = findViewById(R.id.web_view_id)
        webView?.webViewClient = WebViewClient()

        val editText = findViewById<EditText>(R.id.web_view_edit_id)
        findViewById<ImageView>(R.id.web_view_search_id)
            .setOnClickListener {
                var url = editText.text.toString()
                if (!url.contains("https://"))
                    url = "https://".plus(url)

                webView?.apply {
                    loadUrl(url)
                    settings.javaScriptEnabled = true
                    settings.safeBrowsingEnabled = true
                }

                findViewById<LinearLayout>(R.id.web_view_search_layout_id)
                    .visibility = View.GONE
            }
    }

    override fun onBackPressed() {
        if (webView?.canGoBack() == true)
            webView?.goBack()
        else
            super.onBackPressed()
    }
}