package org.iesalandalus.likendero.p07webview_javier_gonzalez_rives;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ActivityWeb extends AppCompatActivity {
    private WebView webv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        // captura del web
        webv = (WebView) findViewById(R.id.wvBuscar);
        String url = getIntent().getStringExtra("url");
        webv.setWebViewClient(new WebViewClient());
        webv.loadUrl("https://" +url);
        // no olvidar de solicitar permiso en android manifest
    }

    /**
     * fin del programa
     * @param view
     */
    public void cerrar(View view){
        // la aplicacion se cierra
        finish();
    }
}
