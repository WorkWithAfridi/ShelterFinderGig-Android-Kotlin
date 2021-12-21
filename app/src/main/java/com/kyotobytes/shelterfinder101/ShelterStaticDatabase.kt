package com.kyotobytes.shelterfinder101

class ShelterStaticDatabase{
    //static method to use class functions without creating object of the class
    companion object{
        //public list of shelters
        val shelters = mutableListOf<Shelter>()
        //method to fet shelter details
        fun getShelter(): MutableList<Shelter>{
            createShelter()
            return shelters
        }
        private fun createShelter() {
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
        }
    }


}