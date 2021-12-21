package com.kyotobytes.shelterfinder101

import android.os.Bundle
import android.webkit.GeolocationPermissions
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity


class OpenMap : AppCompatActivity() {
    // a empty variable of string type
    // I had to use google maps using a web view, since the api part was not working, spend about 8 hours on that api part alone and it wont load. tried everything....
    var shelName=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_map)

        shelName= intent.getStringExtra("name").toString()
        // universal google maps link
        var mapUrl = "https://www.google.com/maps"
        // hard coded locations for the shelters
        if(shelName=="Cairns Homelessness Services Hub"){
            mapUrl="https://www.google.com/maps/place/Cairns+Homelessness+Services+Hub/@-16.9273946,145.7697229,17z/data=!3m1!4b1!4m5!3m4!1s0x69786696ff00858f:0xab8fbda675289c5a!8m2!3d-16.927358!4d145.7719649"
        }
        if(shelName=="Vinnies Homeless Hostel"){
            mapUrl="https://www.google.com/maps/place/Vinnies+Homeless+Hostel+(Men)+%26+Support+Services/@-16.9288896,145.7680349,17z/data=!3m1!4b1!4m5!3m4!1s0x69786697c1d95d21:0xf1c0e87527ec2b37!8m2!3d-16.9289086!4d145.7702047"
        }
        if(shelName=="Douglas House"){
            mapUrl="https://www.google.com/maps/place/Douglas+House/@-16.9165186,145.767684,17z/data=!3m1!4b1!4m5!3m4!1s0x6978669957332323:0xacacf17282d2aa85!8m2!3d-16.9165906!4d145.7697781"
        }
        if(shelName=="St. John’s Young Men’s Shelter"){
            mapUrl="https://www.google.com/maps/place/Anglicare+North+Queensland/@-16.9168638,145.7685584,17z/data=!3m1!4b1!4m5!3m4!1s0x6978668f0968d795:0xcc8af5b5b2f3d29c!8m2!3d-16.9167628!4d145.7708832"
        }
        if(shelName=="St Margaret’s Young Women’s Shelter"){
            mapUrl="https://www.google.com/maps/place/6+McGuigan+St,+Earlville+QLD+4870,+Australia/@-16.9452607,145.7417032,19z/data=!4m5!3m4!1s0x697863f60780f5e1:0x800c24eff931b887!8m2!3d-16.9452107!4d145.7411909"
        }
        if(shelName=="Quigley Street Night Shelter"){
            mapUrl="https://www.google.com/maps/place/Quigley+St,+Bungalow+QLD+4870,+Australia/@-16.9290043,145.7601127,17z/data=!3m1!4b1!4m5!3m4!1s0x697866a1e5ed1019:0x67e6f1fd41f49202!8m2!3d-16.9290094!4d145.7623014"
        }
        var webView: WebView = findViewById<WebView>(R.id.WebViewMaps)
        webView.webChromeClient = object : WebChromeClient() {
            override fun onGeolocationPermissionsShowPrompt(
                origin: String,
                callback: GeolocationPermissions.Callback
            ) {
                callback.invoke(origin, true, false)
            }
        }
        //JS needs to be enabled before using anytype of webview, since almost all the websites now a days use JS
        webView.getSettings().setJavaScriptEnabled(true)
        webView.loadUrl(mapUrl)
    }
}