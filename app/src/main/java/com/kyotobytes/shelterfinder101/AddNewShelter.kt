package com.kyotobytes.shelterfinder101

import android.content.Intent
import android.content.SharedPreferences
import android.location.Address
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.Toast

class AddNewShelter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_shelter)
    }

    fun Save(view: View) {

        //linking the variables in the xml file
        val shelterName: EditText =findViewById(R.id.shelterName)
        val shelterAddress: EditText =findViewById(R.id.shelterAddress)
        val shelterSuburb: EditText =findViewById(R.id.shelterSuburb)
        val shelterPhone: EditText =findViewById(R.id.shelterPhone)
        val shelterEmail: EditText =findViewById(R.id.shelterEmail)
        var name = shelterName.text
        var address = shelterAddress.text
        var suburb = shelterSuburb.text
        var phone = shelterPhone.text
        var email = shelterEmail.text

        if(name!=null && address!=null && suburb!=null&& phone!=null &&email!=null){
            val sp = getSharedPreferences("LoginInfos", 0)
            val editor: SharedPreferences.Editor = sp.edit()
            editor.putString("NewShelterName", name.toString())
            editor.putString("NewShelterAddress", address.toString())
            editor.putString("NewShelterSuburb", suburb.toString())
            editor.putString("NewShelterPhone", phone.toString())
            editor.putString("NewShelterEmail", email.toString())
            editor.commit()
        }
        Toast.makeText(this, "A new shelter has been added to the database. :)", Toast.LENGTH_SHORT).show()
    }
    fun BackToHome(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}