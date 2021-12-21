package com.kyotobytes.shelterfinder101

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


//shelterDetails scene
class ShelterDetails : AppCompatActivity() {
    //name of a shelter
    var Shelname = ""
    //email of the shelter mentioned above
    var shelEmail=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelter_details)
        //extracting the data senc inby the previous scene/intent
        var name = intent.getStringExtra("name")
        val address = intent.getStringExtra("address")
        val suburb = intent.getStringExtra("suburb")
        val phone = intent.getStringExtra("phone")
        val email = intent.getStringExtra("email")
        //linking the variables in the xml file
        var shelterName: EditText =findViewById(R.id.shelterName)
        val shelterAddress: EditText =findViewById(R.id.shelterAddress)
        val shelterSuburb: EditText =findViewById(R.id.shelterSuburb)
        val shelterPhone: EditText =findViewById(R.id.shelterPhone)
        val shelterEmail: EditText =findViewById(R.id.shelterEmail)
        //setting the variable with data, extracted from the previous intent
        shelterName.text= Editable.Factory.getInstance().newEditable(name)
        shelterAddress.text= Editable.Factory.getInstance().newEditable(address)
        shelterSuburb.text= Editable.Factory.getInstance().newEditable(suburb)
        shelterPhone.text= Editable.Factory.getInstance().newEditable(phone)
        shelterEmail.text= Editable.Factory.getInstance().newEditable(email)

        Shelname=shelterName.text.toString()
        shelEmail=shelterEmail.text.toString()
    }
    // on Open on Map button click, this function initiated
    fun OpenWebViewMaps(view: View) {
        val intent = Intent(this, OpenMap::class.java)
        intent.putExtra("name", Shelname)
        startActivity(intent)
    }
    // On send Email button click, this function is initiated
    fun sendEmail(view: View) {
        var shelterName: EditText =findViewById(R.id.shelterName)
        val shelterEmail: EditText =findViewById(R.id.shelterEmail)
        //if the shelter name and the shelter email edittext is not empty, then initiate the if part, else go on with the else part.
        //after the variables are checked, the necessary datas are extracted and passed on to a new intent which opens ip the default mailing app and
        //fills the recipient, subject and body of the email and awaits for user confirmation.
        //after the user confirms and email is send to the shelter
        if(!TextUtils.isEmpty(shelterName.text.toString()) && !TextUtils.isEmpty(shelterEmail.text.toString()) ){
            val intent = Intent(Intent.ACTION_SEND)
            val recipients = arrayOf(shelterEmail.text.toString())
            intent.putExtra(Intent.EXTRA_EMAIL, recipients)
            intent.putExtra(Intent.EXTRA_SUBJECT, "Accommodation Request")
            var name = shelterName.text.toString()
            intent.putExtra(Intent.EXTRA_TEXT, "I would like to request accommodation for tonight at $name")
            intent.putExtra(Intent.EXTRA_CC, "mailcc@gmail.com")
            intent.type = "text/html"
            intent.setPackage("com.google.android.gm")
            startActivity(Intent.createChooser(intent, "Send mail"))
        }
        else{
            //continue //do Nothing
        }
    }
//    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
//        var shelterPhoneInDetailsPage: EditText =findViewById(R.id.shelterPhone)
//        var shelterEmailInDetailsPage: EditText =findViewById(R.id.shelterEmail)
//        val shelterPhoneFromMain = intent.getStringExtra("phone")
//        val shelterEmailFromMain = intent.getStringExtra("email")
//        if (shelterEmailFromMain != null) {
//            if (shelterPhoneFromMain != null) {
//                if(!shelterEmailFromMain.equals(shelterEmailInDetailsPage.text) && !shelterPhoneFromMain.equals(shelterPhoneInDetailsPage))
//
//                    Toast.makeText(this, "This is a toast on backpress", Toast.LENGTH_SHORT).show()
//            }
//        }
//        return super.onKeyDown(keyCode, event)
//    }

    override fun onBackPressed() {
        var shelterPhoneInDetailsPage: EditText =findViewById(R.id.shelterPhone)
        var shelterEmailInDetailsPage: EditText =findViewById(R.id.shelterEmail)

        var name = intent.getStringExtra("name")
        val shelterPhoneFromMain = intent.getStringExtra("phone")
        val shelterEmailFromMain = intent.getStringExtra("email")

        if(shelterEmailFromMain?.equals(shelterEmailInDetailsPage) == false && shelterPhoneFromMain?.equals(shelterPhoneInDetailsPage)==false){
            val sp = getSharedPreferences("LoginInfos", 0)
            val editor: SharedPreferences.Editor = sp.edit()
            editor.putString("Phone", shelterPhoneInDetailsPage.text.toString())
            editor.putString("Email", shelterEmailInDetailsPage.text.toString())
            editor.putString("Name",name.toString())
            editor.commit()
        }


        super.onBackPressed()
        finish()
    }

}