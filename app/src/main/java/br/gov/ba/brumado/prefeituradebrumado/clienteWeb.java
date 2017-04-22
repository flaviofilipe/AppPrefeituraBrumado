package br.gov.ba.brumado.prefeituradebrumado;

import android.support.v7.app.AlertDialog;
import android.webkit.JsResult;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by fx2 on 20/04/17.
 */

public class clienteWeb extends WebViewClient{
    @Override
    public boolean shouldOverrideUrlLoading(WebView v,String url){
        v.loadUrl(url);
        return true;
    }

    public boolean onJsAlert(WebView view, String url, String message,
                             JsResult result) {
// TODO Auto-generated method stub
        new AlertDialog.Builder(view.getContext()).setMessage(message).setCancelable(true).show();
        result.confirm();
        return true;



    }
}
