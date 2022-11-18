package com.example.studentguidance3o

import android.app.ActionBar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class FacultyList : AppCompatActivity() {
    lateinit var department:String
    lateinit var cabin:String
    lateinit var mailID:String
    lateinit var listview:ListView
    lateinit var facultyList:ArrayList<Faculty>
    lateinit var drawerLayout:DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faculty_list)

        drawerLayout=findViewById(R.id.drawerLayout2)

        var toolbar2:Toolbar=findViewById(R.id.toolbar2)
        listview=findViewById(R.id.facultyListView)

        facultyList = ArrayList()
        facultyList.add(Faculty(R.drawable.ashish_t,"Dr. Ashish Tripathi","Professor"))
        facultyList.add(Faculty(R.drawable.dr_sheetal,"Dr. Sheetal Sharma","Professor"))
        facultyList.add(Faculty(R.drawable.dr_debashis_adhikari,"Dr. Debashis Adhikari","Professor"))
        facultyList.add(Faculty(R.drawable.dr_poonkuthran_c,"Dr. S. Poonkuntran","Senior Associate Professor"))
        facultyList.add(Faculty(R.drawable.dr_r_shriram_c,"Dr. R. Shriram","Senior Associate Professor"))
        facultyList.add(Faculty(R.drawable.dr_george,"Dr. George Chellin Chandran","Senior Associate Professor"))
        facultyList.add(Faculty(R.drawable.dr_shishir,"Dr. Shishir Kumar Shandilya","Senior Associate Professor"))
        facultyList.add(Faculty(R.drawable.pushpinder,"Dr. Pushpinder Singh Patheja","Senior Associate Professor"))
        facultyList.add(Faculty(R.drawable.dr_balaguru_c,"Dr.S Balaguru","Senior Associate Professor"))
        facultyList.add(Faculty(R.drawable.lakshmi,"Dr. Lakshmi .D","Senior Associate Professor"))
        facultyList.add(Faculty(R.drawable.manisha_jain,"Dr. Manisha Jain","Associate Professor"))
        facultyList.add(Faculty(R.drawable.vinod_bhatt,"Dr. Vinod Bhatt","Associate Professor"))
        facultyList.add(Faculty(R.drawable.harshvardhanan,"Dr. Pon Harshavardhanan","Associate Professor"))
        facultyList.add(Faculty(R.drawable.venkat_prasad_padhy,"Dr. Venkat Padhy","Associate Professor"))
        facultyList.add(Faculty(R.drawable.mamta,"Dr. Mamta Agrawal","Associate Professor"))
        facultyList.add(Faculty(R.drawable.navneet_kumar_verma,"Dr. Navneet Kumar Verma","Associate Professor"))
        facultyList.add(Faculty(R.drawable.xavier_suresh,"Dr. M. Xavier Suresh","Associate Professor"))
        facultyList.add(Faculty(R.drawable.mahendran,"Dr. Mahendran B","Associate Professor"))
        facultyList.add(Faculty(R.drawable.komarasamy,"Dr Komarasamy G","Associate Professor"))
        facultyList.add(Faculty(R.drawable.ananthakumaran,"Dr. S. Ananthakumaran","Associate Professor"))
        facultyList.add(Faculty(R.drawable.prasad_begde,"Dr. Prasad Begde","Associate Professor"))
        facultyList.add(Faculty(R.drawable.subash_chandra_bose,"Dr. J. Subash Chandra Bose","Associate Professor"))
        facultyList.add(Faculty(R.drawable.asdaque_hussain,"Dr. Md Asdaque Hussain","Associate Professor"))
        facultyList.add(Faculty(R.drawable.sasmita_padhy,"Dr. Sasmita Padhy","Associate Professor"))
        facultyList.add(Faculty(R.drawable.usha_ruby,"Dr. A. Usha Ruby","Associate Professor"))
        facultyList.add(Faculty(R.drawable.periyanayagi,"Dr. S. Periyanayagi","Associate Professor"))
        facultyList.add(Faculty(R.drawable.neha_c,"Dr. Neha Choubey","Associate Professor"))
        facultyList.add(Faculty(R.drawable.aazath_new,"Dr. Azath.H","Associate Professor"))
        facultyList.add(Faculty(R.drawable.bazeera,"Dr. Baseera A","Associate Professor"))
        facultyList.add(Faculty(R.drawable.shweta_m,"Dr. Shweta Mukherjee","Associate Professor"))
        facultyList.add(Faculty(R.drawable.suchismita_patra,"Dr. Suchismita Patra","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.suparna_patowary,"Dr. Suparna Patowary","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.rahul_kumar_chaturvedi,"Dr. Rahul Kumar Chaturvedi","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.tushar_chourushi,"Dr. Tushar Chourushi","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.anil,"Dr. Anil Kumar Shukla","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.udai_kumar,"Dr. Udai Kumar","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.ujjwal_kumar_mishra,"Dr. Ujjwal Kumar Mishra","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.ankur_jain,"Dr. Ankur Jain","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.s_aanjankumar,"Dr. S. Aanjan Kumar","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.karthik_m,"Dr. Karthik Rao M C","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.arpita_baronia,"Dr. Arpita Baronia","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.nilam_venkatakoteswararao,"Dr. Nilam Venkatakoteswararao","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.pazhaniraja_n,"Dr. N. Pazhaniraja","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.kamal_kant_verma,"Dr. Kamal Kant Verma","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.abhilash_sahu,"Dr. Abhilash Sahu","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.satyajit_mahapatra,"Dr. Satyajit Mahapatra","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.hariharan,"Dr. Hariharan R","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.ramesh_saha,"Dr. Ramesh Saha","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.dheresh_soni,"Dr. Dheresh Soni","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.rajdeep_ghosh,"Dr. Rajdeep Ghosh","Assistant Professor Grade"))
        facultyList.add(Faculty(R.drawable.harish_chandra,"Dr. Harish Chandra","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.dev_brat_gupta,"Dr. Dev Brat Gupta","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.siddharth_singh_chouhan,"Dr. Siddharth Singh Chouhan","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.sheerin_kayenat,"Dr. Sheerin Kayenat","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.narendra_babu,"Dr. P. Narendra Babu","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.ab_rouf_khan,"Dr. Ab Rouf Khan","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.g_prabu_kanna,"Dr. G. Prabu Kanna","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.prabu_m,"Dr. Prabu M","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.rahul,"Dr. Rahul Pal","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.ashok_kumar_baral,"Dr. Ashok Kumar Baral","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.gaurav_soni,"Dr. Gaurav Soni","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.pradeep_mishra,"Dr. Pradeep Kumar Mishra","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.mrityunjay_singh,"Dr. Mrityunjay Singh","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.anil_kumar,"Dr. Anil Kumar Yadav","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.varshali_jaiswal,"Dr. Varshali Jaiswal","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.kamlesh_chandravanshi,"Dr. Kamlesh Chandravanshi","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.rudra_kalyan_nayak,"Dr. Rudra Kalyan Nayak","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.thiyagu_priyadharsan,"Dr. M. R. Thiyagu Priyadharsan","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.ankit_pal,"Dr. Ankit Pal","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.abhijit_sarkar,"Dr. Abhijit Sarkar","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.dipankar_sutradhar,"Dr. Dipankar Sutradhar","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.anju_sukla,"Dr. Anju Shukla","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.ashish_kumar_shahu,"Dr. Ashish Kumar Sahu","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.m_thangavel,"Dr. M. Thangavel","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.animesh_bhandari,"Dr. Animesh Bhandari","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.baldev_samy,"Dr. Baldev Swamy","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.trapti_sharme,"Dr. Trapti Sharma","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.soumithra,"Dr. Soumitra Nayak","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.saurabh_bhargava,"Dr. Saurabh Bhargava","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.maragatharajan,"Dr. M. Maragatharajan","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.shweta_saxena,"Dr. Shweta Saxena","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.manikandan_j,"Dr. J. Manikandan","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.justin_joseph,"Dr. Justin Joseph","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.vivek_kumar_yadav,"Dr. Vivek Kumar Yadav","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.suchetana,"Dr. Suchetana Sadhukhan","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.sivasankaran,"Dr. Sivasankaran","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.shiv_manjaree_gopaliya,"Dr. Shiv Manjaree Gopaliya","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.atul_aman,"Dr. Atul Aman","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.satyam_ravi,"Dr. Satyam Ravi","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.rizwan_ur_rahman,"Dr. Rizwan Ur Rahman","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.ashok_kumar_patel,"Dr. Ashok Patel","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.virendra_singh_kushwah,"Dr. Virendra Singh Kushwah","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.abha_trivedi,"Dr. Abha Trivedi","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.vikash_panthi,"Dr. Vikas Panthi","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.d_saravanan,"Dr. D. Saravanan","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.hariharasitaraman_s,"Dr. Hariharasitaraman. S","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.r_rakesh,"Dr. R. Rakesh","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.saurav_prasad,"Dr. Saurav Prasad","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.soumya_sankar_ghosh,"Dr. Soumya Sankar Ghosh","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.ravi,"Dr. Ravi Bhatt","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.rajasoundharan,"DR. S. Rajasoundaran","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.gl_balaji,"Dr. G.L. Balaji","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.susant_kumar_panigrahi,"Dr. Susant Kumar Panigrahi","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.rajdeep_singh_payal,"Dr.Rajdeep Singh Payal","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.pushpdant_jain,"Dr.Pushpdant Jain","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.sayed_mohammed,"Dr.Sayed Mohammed Zeeshan","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.pradeep_kumar_kashyap,"Dr. Pradeep Kumar Kayshap","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.neetu_kalra,"Dr.Neetu Kalra","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.siddhartha_maiti,"Dr.Siddhartha Maiti","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.rohit_sharma,"Dr.Rohit Sharma","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.prashant_g_k,"Dr. Prashant G K","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.ankush,"Dr.Ankush D Tharkar","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.mohammed_ifran_alam,"Dr.Mohammed Ifran Alam","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.subhash_chandra_patel,"Dr. Subhash Chandra Patel","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.bhumika_choksi,"Dr.Bhumika Choksi","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.mayank_sharma,"Dr. Mayank Sharma","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.suresh_dara,"Dr.Suresh Dara","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.pallabi_sarkar,"Dr. Pallabi Sarkar","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.manisha_singh,"Dr. Manisha Singh","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.saravanan,"Dr. Saravanan J","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.chanthankumar,"Dr. Chandan Kumar","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.ankur_beohar,"Dr. Ankur Beohar","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.benevatho_jaison_a,"Dr. Benevatho Jaison A","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.yogesh_shukla,"Dr. Yogesh Shukla","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.sathish,"Dr. Sathish Kumar .L","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.jyoti_badge,"Dr. Jyoti Badge","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.nella_anveshkumar,"Dr. Nella Anveshkumar","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.rabia_musheer,"Dr. Rabia Musheer","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.ajay_kumar_bhurjee,"Dr. Ajay Kumar Bhurjee","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.rajeev_saxena,"Dr. Rajeev Saxena","Assistant Professor"))
        facultyList.add(Faculty(R.drawable.anita_yadav,"Dr. Anita Yadav","Assistant Professor"))

        setSupportActionBar(toolbar2)
        val actionBar: ActionBar? =getActionBar()
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //supportActionBar?.setDisplayShowHomeEnabled(false)

























        val professor="Professor"
        val seniorAssociateProfessor="Senior Associate Professor"
        val associateProfessor="Associate Professor"
        val myArrayAdapter:FacultyArrayAdapter=FacultyArrayAdapter(this,R.layout.faculty_list_one_sample,facultyList)
        listview.adapter=myArrayAdapter



        val intent: Intent = Intent(this, FacultyInfo::class.java)
        listview.setOnItemClickListener { parent, view, position, id ->
            var items:Faculty=listview.getItemAtPosition(position) as Faculty

           when(items.name){

                "Dr. Ashish Tripathi"->{

                    department="VITBS - VIT Business School"
                    cabin="G-03"
                    mailID="ashish.tripathi@vitbhopal.ac.in"
                    startActivity(intent.putExtra("name","Dr. Ashish Tripathi").putExtra("level",professor).putExtra("image",R.drawable.ashish_t)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))

                }
                "Dr. Sheetal Sharma"->{

                    department="School of Architecture"
                    cabin="A-102"
                    mailID="sheetal.sharma@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr. Sheetal Sharma").putExtra("level",professor).putExtra("image", R.drawable.dr_sheetal)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. Debashis Adhikari"->{

                    department="SEEE - School of Electrical and Electronics Engineering"
                    cabin="A-212"
                    mailID="debashis@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr. Debashis Adhikari").putExtra("level",professor).putExtra("image",R.drawable.dr_debashis_adhikari)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. S. Poonkuntran"->{

                    department="SCSE - School of Computing Science and Engineering"
                    cabin="A-111"
                    mailID="poonkuntran.s@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr. S. Poonkuntran").putExtra("level",seniorAssociateProfessor).putExtra("image", R.drawable.dr_poonkuthran_c)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. R. Shriram"->{

                    department="SCSE - School of Computing Science and Engineering"
                    cabin="A-207"
                    mailID="shriram.r@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr. R. Shriram").putExtra("level",seniorAssociateProfessor).putExtra("image",R.drawable.dr_r_shriram_c)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. George Chellin Chandran"->{

                    department="SCSE - School of Computing Science and Engineering"
                    cabin="00"
                    mailID="george.chandran@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr. George Chellin Chandran").putExtra("level",seniorAssociateProfessor).putExtra("image", R.drawable.dr_george)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. Shishir Kumar Shandilya"->{

                    department="SCSE - School of Computing Science and Engineering"
                    cabin="4TH FLOOR B BLOCK"
                    mailID="shishirkumar.s@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr. Shishir Kumar Shandilya").putExtra("level",seniorAssociateProfessor).putExtra("image",R.drawable.dr_shishir)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. Pushpinder Singh Patheja"->{

                    department="SCSE - School of Computing Science and Engineering"
                    cabin="G-09"
                    mailID="pushpinder.singh@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr. Pushpinder Singh Patheja").putExtra("level",seniorAssociateProfessor).putExtra("image", R.drawable.pushpinder)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr.S Balaguru"->{

                    department="School of Mechanical Engineering"
                    cabin="A-210"
                    mailID="balaguru.s@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr.S Balaguru").putExtra("level",seniorAssociateProfessor).putExtra("image",R.drawable.dr_balaguru_c)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. Lakshmi .D"->{

                    department="SCSE - School of Computing Science and Engineering"
                    cabin="G-08"
                    mailID="lakshmi.d@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr. Lakshmi .D").putExtra("level",seniorAssociateProfessor).putExtra("image", R.drawable.lakshmi)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. Manisha Jain"->{

                    department="SASL - School of Advanced Sciences and Languages"
                    cabin="G-04"
                    mailID="manisha.jain@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr. Manisha Jain").putExtra("level",associateProfessor).putExtra("image",R.drawable.manisha_jain)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. Vinod Bhatt"->{

                    department="SASL - School of Advanced Sciences and Languages"
                    cabin="G-02"
                    mailID="vinod.bhatt@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr. Vinod Bhatt").putExtra("level",associateProfessor).putExtra("image", R.drawable.vinod_bhatt)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. Pon Harshavardhanan"->{

                    department="SCSE - School of Computing Science and Engineering"
                    cabin="A-202"
                    mailID="pon.harshavardhanan@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr. Pon Harshavardhanan").putExtra("level",associateProfessor).putExtra("image",R.drawable.dr_balaguru_c)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. Venkat Padhy"->{

                    department="SCSE - School of Computing Science and Engineering"
                    cabin="A-415"
                    mailID="venkat.prasad@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr. Venkat Padhy").putExtra("level",associateProfessor).putExtra("image", R.drawable.venkat_prasad_padhy)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. Mamta Agrawal"->{

                    department="School of Applied Science and Language"
                    cabin="A-201"
                    mailID="mamta.agrawal@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr. Mamta Agrawal").putExtra("level",associateProfessor).putExtra("image", R.drawable.mamta)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. Navneet Kumar Verma"->{

                    department="School of Applied Science and Language"
                    cabin="A-203"
                    mailID="navneet.verma@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr. Navneet Kumar Verma").putExtra("level",associateProfessor).putExtra("image", R.drawable.navneet_kumar_verma)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. M. Xavier Suresh"->{

                    department="SASL - School of Advanced Sciences and Languages"
                    cabin="B-312"
                    mailID="xavier.m@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name" ,"Dr. M. Xavier Suresh").putExtra("level",associateProfessor).putExtra("image", R.drawable.xavier_suresh)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. Mahendran B"->{

                    department="School of Bioscience Engineering and technology"
                    cabin="A-506"
                    mailID="mahendran.b@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr. Mahendran B").putExtra("level",associateProfessor).putExtra("image", R.drawable.mahendran)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr Komarasamy G"->{

                    department="SCSE - School of Computing Science and Engineering"
                    cabin="A-402"
                    mailID="komarasamy.g@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr Komarasamy G").putExtra("level",associateProfessor).putExtra("image", R.drawable.komarasamy)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. S. Ananthakumaran"->{

                    department="SCSE - School of Computing Science and Engineering"
                    cabin="C-515"
                    mailID="ananthakumaran@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr. S. Ananthakumaran").putExtra("level",associateProfessor).putExtra("image", R.drawable.ananthakumaran)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. Prasad Begde"->{

                    department="VITBS - VIT Business School"
                    cabin="C-540"
                    mailID="prasad.begde@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name", "Dr. Prasad Begde").putExtra("level",associateProfessor).putExtra("image", R.drawable.prasad_begde)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. J. Subash Chandra Bose"->{

                    department="SCSE - School of Computing Science and Engineering"
                    cabin="A-205"
                    mailID="subash.chandra@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name", "Dr. J. Subash Chandra Bose").putExtra("level",associateProfessor).putExtra("image", R.drawable.subash_chandra_bose)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. Md Asdaque Hussain"->{

                    department="SCSE - School of Computing Science and Engineering"
                    cabin="****"
                    mailID="asdaque.hussain@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr. Md Asdaque Hussain").putExtra("level",associateProfessor).putExtra("image", R.drawable.asdaque_hussain)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. Sasmita Padhy"->{

                    department="SCSE - School of Computing Science and Engineering"
                    cabin="****"
                    mailID="sasmita.padhy@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr. Sasmita Padhy").putExtra("level",associateProfessor).putExtra("image", R.drawable.sasmita_padhy)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. A. Usha Ruby"->{

                    department="SCSE - School of Computing Science and Engineering"
                    cabin="****"
                    mailID="usha.ruby@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr. A. Usha Ruby").putExtra("level",associateProfessor).putExtra("image", R.drawable.usha_ruby)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. S. Periyanayagi"->{

                    department="SCSE - School of Computing Science and Engineering"
                    cabin="****"
                    mailID="periyanayagi.s@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name", "Dr. S. Periyanayagi").putExtra("level",associateProfessor).putExtra("image", R.drawable.periyanayagi)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. Neha Choubey"->{

                    department="SASL - School of Advanced Sciences and Languages"
                    cabin="CABIN NO 2 ADMISSION OFFICE"
                    mailID="neha.choubey@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name", "Dr. Neha Choubey").putExtra("level",associateProfessor).putExtra("image", R.drawable.neha_c)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. Azath.H"->{

                    department="SCSE - School of Computing Science and Engineering"
                    cabin="B-512"
                    mailID="azath.h@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name",  "Dr. Azath.H").putExtra("level",associateProfessor).putExtra("image", R.drawable.aazath_new)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. Baseera A"->{

                    department="SCSE - School of Computing Science and Engineering"
                    cabin="G-01"
                    mailID="baseera.a@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name","Dr. Baseera A").putExtra("level",associateProfessor).putExtra("image", R.drawable.bazeera)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                "Dr. Shweta Mukherjee"->{

                    department="SASL - School of Advanced Sciences and Languages"
                    cabin="A-204"
                    mailID="shweta.mukherjee@vitbhopal.ac.in"

                    startActivity(intent.putExtra("name", "Dr. Shweta Mukherjee").putExtra("level",associateProfessor).putExtra("image", R.drawable.shweta_m)
                        .putExtra("department",department).putExtra("cabin",cabin).putExtra("mailID",mailID))
                }
                else->{
                    Toast.makeText(this,"Sorry",Toast.LENGTH_LONG).show()
                }

            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater:MenuInflater=getMenuInflater()
        menuInflater.inflate(R.menu.menu_search,menu)

        val menuItem:MenuItem=menu?.findItem(R.id.actionSearch) as MenuItem

        val searchView:SearchView=menuItem.actionView as SearchView

        searchView.onActionViewExpanded()
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                var newFacultyList=ArrayList<Faculty>()
                for(i in facultyList){
                    if(i.name.lowercase().contains(newText.toString().lowercase())){
                        newFacultyList.add(i)
                    }
                }
                var newArrayAdapter=FacultyArrayAdapter(applicationContext,R.layout.faculty_list_one_sample,newFacultyList)
                listview.adapter=newArrayAdapter
                return false
            }

        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                onBackPressed()
            }
        }
        return true
    }
}