package com.kyotobytes.shelterfinder101

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
//  Univeral list of shelters, so they can picked up by any function
    val shelters = mutableListOf<Shelter>()
    // On create method, sets up the shelter list using the create list fffunction
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createShelter()


        val listView = findViewById<ListView>(R.id.listView)

        listView.adapter=MyCustomAdapter(this, shelters)
        listView.setOnItemClickListener { parent, view, position, id ->
            val name = shelters[position].name.toString()
            val address = shelters[position].address.toString()
            val suburb = shelters[position].suburb.toString()
            val phone = shelters[position].phone.toString()
            val email = shelters[position].email.toString()

            val intent = Intent(this, ShelterDetails::class.java)
            intent.putExtra("name", name)
            intent.putExtra("address", address)
            intent.putExtra("suburb", suburb)
            intent.putExtra("phone", phone)
            intent.putExtra("email", email)

//            startActivity(intent)

            startActivityForResult(intent, 1);
        }
    }

    fun updateList(){
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter=MyCustomAdapter(this, shelters)
    }

    //on click method for the add new shelter button
    fun AddNewShelter(view: View) {
        val intent = Intent(this, AddNewShelter::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        val sp = getSharedPreferences("LoginInfos", 0)
        val updatedEmail = sp.getString("Email", "No data retrieved from details page")
        val updatedPhone = sp.getString("Phone", "No data retrieved from details page")
        val updatedShelterName = sp.getString("Name", "No data retrieved from details page")
        if(updatedShelterName.equals("No data retrieved from details page")){
            //donothing
        }else{
            for(shelter in shelters){
                if(shelter.name.equals(updatedShelterName)){
                    if (updatedPhone != null) {
                        shelter.phone=updatedPhone
                    }
                    if (updatedEmail != null) {
                        shelter.email=updatedEmail
                    }
                }
            }
        }

        val NewShelterName= sp.getString("NewShelterName", "No data retrieved ")
        val NewShelterAddress= sp.getString("NewShelterName", "No data retrieved ")
        val NewShelterSuburb= sp.getString("NewShelterSuburb", "No data retrieved ")
        val NewShelterPhone= sp.getString("NewShelterPhone", "No data retrieved ")
        val NewShelterEmail= sp.getString("NewShelterEmail", "No data retrieved ")

        if(NewShelterName.equals("No data retrieved ")){
            //do nothing..
        }else{
            for(shelter in shelters){
                if(shelter.name==NewShelterName){
                    updateList()
                    return
                }
            }
            val shelterx = Shelter()
            shelterx.name = NewShelterName.toString()
            shelterx.address = NewShelterAddress.toString()
            shelterx.suburb = NewShelterSuburb.toString()
            shelterx.phone = NewShelterPhone.toString()
            shelterx.email = NewShelterEmail.toString()
            shelters += shelterx
        }

        updateList()
    }

    // creates the shelter list and fills them up with the necessary details Hardcoded....
    fun createShelter(){
        val shelter1 = Shelter()
        shelter1.name = "Cairns Homelessness Services Hub"
        shelter1.address = "149-153 Bunda Street"
        shelter1.suburb = "Parramatta Park"
        shelter1.phone = "07 4046 8050"
        shelter1.email = "hubadmin@anglicare.net"
        shelters += shelter1

        val shelter2 = Shelter()
        shelter2.name = "Vinnies Homeless Hostel"
        shelter2.address = "197 Draper Street"
        shelter2.suburb = "Parramatta Park"
        shelter2.phone = "1800 846 643"
        shelter2.email = "reception@svdpqld.org.au"
        shelters += shelter2

        val shelter3 = Shelter()
        shelter3.name = "Douglas House"
        shelter3.address = "198 Grafton Street"
        shelter3.suburb = "Cairns"
        shelter3.phone = "07 4048 7500"
        shelter3.email = "musumecin@missionaustralia.com.au"
        shelters += shelter3

        val shelter4 = Shelter()
        shelter4.name = "St. John’s Young Men’s Shelter"
        shelter4.address = "Aumuller Street"
        shelter4.suburb = "Cairns"
        shelter4.phone = "07 4032 4971"
        shelter4.email = "stjohns.referrals@anglicare.net"
        shelters += shelter4

        val shelter5 = Shelter()
        shelter5.name = "St Margaret’s Young Women’s Shelter"
        shelter5.address = "6 McGuigan Street"
        shelter5.suburb = "Earlville"
        shelter5.phone = "07 4033 2678"
        shelter5.email = "admin@anglicare.net"
        shelters += shelter5

        val shelter6 = Shelter()
        shelter6.name = "Quigley Street Night Shelter"
        shelter6.address = "6 - 8 Quigley Street"
        shelter6.suburb = "Cairns"
        shelter6.phone = "07 4046 8092"
        shelter6.email = "quigleypm@anglicare.net"
        shelters += shelter6
    }
    // Custom adapter for the listView
    private class MyCustomAdapter(context: Context, shelterlist: List<Shelter>): BaseAdapter(){
        private val mContext: Context
        private val shelterList=shelterlist

        init {
            mContext=context
        }

        //responsible for how many rows are in my list
        override fun getCount(): Int {
            return shelterList.size
        }

        //ignore this, not necessary for our app
        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        //also ignore this
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        //responsible for rendering out rows
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val row_main = layoutInflater.inflate(R.layout.adapter_view_layout, viewGroup, false)

            val shelterName= row_main.findViewById<TextView>(R.id.shelterName)
            val suburb= row_main.findViewById<TextView>(R.id.suburb)
            val phone= row_main.findViewById<TextView>(R.id.phone)
            shelterName.text= shelterList[position].name.toString()
            suburb.text= shelterList[position].suburb.toString()
            phone.text= shelterList[position].phone.toString()
            return row_main
        }
    }

}