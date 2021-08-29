package com.gaspardeelias.exoplayerverticalslider.utils

import android.annotation.SuppressLint
import java.security.cert.X509Certificate
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

/**
 * Class to avoid SSL Cert invalid exceptions. It seems some videos are hosted
 * in self-signed servers
 * taken from:
 * https://stackoverflow.com/questions/54419649/still-getting-trust-anchor-for-certification-path-not-found-after-getting-immedi/54529176
 */

object SSLUtils {
    //Create a trust manager that does not validate certificate chains
    val X509Trust = @SuppressLint("CustomX509TrustManager")
    object: X509TrustManager {
        @SuppressLint("TrustAllX509TrustManager")
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            // no-op
        }

        @SuppressLint("TrustAllX509TrustManager")
        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            // no-op
        }
        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()

    }
    val trustAllCerts = arrayOf(X509Trust)

    fun installCerts() {
        try {
            val sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (e: Exception) {
            e.printStackTrace();
        }
    }
}