package br.gov.ba.brumado.prefeituradebrumado;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    WebView wv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        wv = (WebView) findViewById(R.id.webView);

        if(verificaConexao()) {

            //WebView
            WebSettings ws = wv.getSettings();
            ws.setJavaScriptEnabled(true);
            ws.setSupportZoom(false);
            wv.loadUrl("http://brumado.ba.gov.br/");

            //faz os redirecionamentos ficaram na webview
            wv.setWebViewClient(new clienteWeb() {
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });


        }else{
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Ops!");
            alertDialog.setMessage("Falha de conexão. Por favor, verifique a sua conexão com a internet");

            alertDialog.show();
        }

    }

    /* Função para verificar existência de conexão com a internet
	 */
    public  boolean verificaConexao() {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }


    //Navegação dentro da webView
    public void onBackPressed() {
        if (wv.canGoBack()) {
            wv.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
