package br.com.molezinif.appcellep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        //Habilitar a execução de códigos javascript
        wbv.settings.javaScriptEnabled = true

        //Carregando o site web
        wbv.loadUrl("http://br.cellep.com/estacaohack/")

        //definir webView como cliente web padrão
        wbv.webViewClient = WebViewClient()
    }
}