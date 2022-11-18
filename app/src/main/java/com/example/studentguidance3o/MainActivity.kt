package com.example.studentguidance3o
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.speech.RecognizerIntent
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.widget.Toolbar
import java.util.*
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.navigation.NavigationView
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var output:String
    lateinit var micIV: ImageView
    private val REQUEST_CODE_SPEECH_INPUT = 1
    lateinit var drawerLayout:DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.navView)
        val imageSlider: ImageSlider = findViewById(R.id.imageSlider)
        var imageList: ArrayList<SlideModel> = arrayListOf()
        imageList.add(SlideModel(R.drawable.academic))
        imageList.add(SlideModel(R.drawable.boys_hostel1))
        imageList.add(SlideModel(R.drawable.girls_hostel))
        imageList.add(SlideModel(R.drawable.bh1_bh2))
        imageList.add(SlideModel(R.drawable.lab_complex))
        imageList.add(SlideModel(R.drawable.multipurpose))
        imageList.add(SlideModel(R.drawable.auditorium))
        imageList.add(SlideModel(R.drawable.post_office))
        imageList.add(SlideModel(R.drawable.ab))
        imageList.add(SlideModel(R.drawable.bh1_new))
        imageList.add(SlideModel(R.drawable.nature))
        imageList.add(SlideModel(R.drawable.lion))
        imageList.add(SlideModel(R.drawable.nature2))
        imageList.add(SlideModel(R.drawable.lc_inside))
        imageSlider.setImageList(imageList, ScaleTypes.FIT)
        setSupportActionBar(toolbar)


        //*************//
        navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
            Boolean
            when (item.itemId) {
                R.id.about_us -> {
                    Toast.makeText(this, "Clicked About Us", Toast.LENGTH_SHORT).show()
                    val intent:Intent=Intent(this,AboutUs::class.java)
                    startActivity(intent)
                }
                R.id.share -> {
                    Toast.makeText(this, "Clicked Share", Toast.LENGTH_SHORT).show()
                }

            }
            true

        })


        //***************//


        micIV = findViewById(R.id.idIVMic)
        micIV.setOnClickListener {
            // on below line we are calling speech recognizer intent.
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

            // on below line we are passing language model
            // and model free form in our intent
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )

            // on below line we are passing our
            // language as a default language.
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault()
            )

            // on below line we are specifying a prompt
            // message as speak to text on below line.
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text")

            // on below line we are specifying a try catch block.
            // in this block we are calling a start activity
            // for result method and passing our result code.
            try {
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
            } catch (e: Exception) {
                // on below line we are displaying error message in toast
                Toast
                    .makeText(
                        this@MainActivity, " " + e.message,
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
        }
        val fac:CardView=findViewById(R.id.fac)
        fac.setOnClickListener(){
            val intent:Intent=Intent(this,FacultyList::class.java)
            startActivity(intent)
        }
        val navi:CardView=findViewById(R.id.navi)
        navi.setOnClickListener(){
            val intent:Intent=Intent(this,SourcePage::class.java)
            startActivity(intent)
        }


        // Here we are changing the status bar color
        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.teal_200)
        }



    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // in this method we are checking request
        // code with our result code.
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            // on below line we are checking if result code is ok
            if (resultCode == RESULT_OK && data != null) {

                // in that case we are extracting the
                // data from our array list
                val res: ArrayList<String> =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>

                // on below line we are setting data
                // to our output text view.
                val array= arrayOf<String>("boys hostel 1","boys hostel 2","boys hostel 3","girls hostel","boys hostel","underbelly","academic block","visa mart","lab complex","multipurpose hall","auditorium","parking area","post office")

                output=Objects.requireNonNull(res)[0]
                output=output.lowercase()
                Toast.makeText(this,"$output",Toast.LENGTH_LONG).show()
                val navFacPage:Array<String> = arrayOf("Navigation","Faculty")
                if(("navigation" in output) && ("boys hostel 1" !in output) && ("boys hostel 2" !in output)&& ("boys hostel 3" !in output)
                    && ("girls hostel" !in output) && ("boys hostel" !in output) && ("underbelly" !in output)
                    && ("academic block" !in output)&& ("visa mart" !in output) && ("lab complex" !in output) && ("multipurpose hall" !in output) && ("auditorium" !in output)
                    && ("parking area" !in output) && ("post office" !in output)){
                    val intent: Intent = Intent(this, NavigationList::class.java)
                    startActivity(intent)

                }
                if("faculty" in output){
                    val intent:Intent=Intent(this,FacultyList::class.java)
                    startActivity(intent)
                }
                if("boys hostel 1" in output){
                    if("academic block 2 boys hostel 1" in output||("academic block to boys hostel 1" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/" + "23.0774280,76.8514190" + "/" + "23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)

                    }
                    else if("boys hostel 2 2 boys hostel 1" in output||("boys hostel2 2 boys hostel 1" in output)||(("boys hostel two 2 boys hostel 1" in output))||("boys hostel 2 to boys hostel 1" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/" + "New Vit Bhopal Boys Hostel" + "/" + "23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 3 2 boys hostel 1" in output||("boys hostel 32 boys hostel 1" in output)||("boys hostel 3 to boys hostel 1" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/" + "New Vit Bhopal Boys Hostel" + "/" + "23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("girls hostel 2 boys hostel 1" in output ||("girls hostel to boys hostel 1" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/" + "23.075020,76.852552" + "/" + "23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("underbelly 2 boys hostel 1" in output ||("underbelly to boys hostel 1" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/" + "23.077430,76.850581" + "/" + "23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("visa mart 2 boys hostel 1" in output ||("visa mart to boys hostel 1" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/" + "23.0773617,76.8509223" + "/" + "23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("lab complex 2 boys hostel 1" in output ||("lab complex to boys hostel 1" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/" + "23.0783234,76.8504043" + "/" + "23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("multipurpose hall 2 boys hostel 1" in output ||("multipurpose hall to boys hostel 1" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/" + "223.076180,76.849838" + "/" + "23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("auditorium 2 boys hostel 1" in output ||("auditorium to boys hostel 1" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/" + "23.077920,76.851285" + "/" + "23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("parking area 2 boys hostel 1" in output ||("parking area to boys hostel 1" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/" + "23.0746557,76.8503436" + "/" + "23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("parking area 2 boys hostel 1" in output || ("parking area to boys hostel 1" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/" + "23.0746557,76.8503436" + "/" + "23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("post office 2 boys hostel 1" in output || ("post office to boys hostel 1" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/" + "23.076110,76.849938" + "/" + "23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }

                    // Boys Hostel 1 to different location

                    else if("boys hostel 1 to academic block" in output || ("boys hostel 1 2 academic block" in output)||("boys hostel 12 academic block" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075293,76.859885"+"/"+"23.0774280,76.8514190")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 1 to boys hostel 2" in output || ("boys hostel 1 2 boys hostel 2" in output)||(("boys hostel 12 boys hostel 2" in output))){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075293,76.859885"+"/"+"New Vit Bhopal Boys Hostel")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 1 to boys hostel 3" in output || ("boys hostel 1 2 boys hostel 3" in output)||(("boys hostel 12 boys hostel 3" in output))){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075293,76.859885"+"/"+"New Vit Bhopal Boys Hostel")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)

                    }
                    else if("boys hostel 1 to girls hostel" in output || ("boys hostel 1 2 girls hostel" in output)||("boys hostel 12 girls hostel" in output)||(("boys hostel 1 two girls hostel" in output))){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075293,76.859885"+"/"+"23.075020,76.852552")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 1 to underbelly" in output || ("boys hostel 1 2 underbelly" in output)||("boys hostel 1 2 under belly" in output)||("boys hostel 12 underbelly" in output)||("boys hostel 12 under belly" in output)||("boys hostel 1 two underbelly" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075293,76.859885"+"/"+"23.077430,76.850581")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 1 to visa mart" in output || ("boys hostel 1 2 visa mart" in output)||("boys hostel 12 visa mart" in output)||("boys hostel 1 two visa mart" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075293,76.859885"+"/"+"23.0773617,76.8509223")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 1 to lab complex" in output || ("boys hostel 1 2 lab complex" in output)||("boys hostel 12 lab complex" in output)||("boys hostel 1 two lab complex" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075293,76.859885"+"/"+"23.0783234,76.8504043")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 1 to multipurpose hall" in output || ("boys hostel 1 2 multipurpose hall" in output)|| ("boys hostel 12 multipurpose hall" in output)||("boys hostel 1 two multipurpose hall" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075293,76.859885"+"/"+"23.076180,76.849838")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 1 to auditorium" in output || ("boys hostel 1 2 auditorium" in output)||("boys hostel 12 auditorium" in output)||("boys hostel 1 two auditorium" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075293,76.859885"+"/"+"23.077920,76.851285")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 1 to parking area" in output || ("boys hostel 1 2 parking area" in output)||("boys hostel 12 parking area" in output)||("boys hostel 1 two parking area" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075293,76.859885"+"/"+"23.0746557,76.8503436")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 1 to post office" in output || ("boys hostel 1 2 post office" in output)||("boys hostel 12 post office" in output)||("boys hostel 1 two post office" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075293,76.859885"+"/"+"``23.076110,76.849938``")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }



                }






                if("boys hostel 2" in output){
                    // Boys hostel 2 to different location
                    val source="New Vit Bhopal Boys Hostel"
                    if ("boys hostel 2 to academic block" in output ||("boys hostel 2 2 academic block" in output)||("boys hostel 22 academic block" in output)||("boys hostel 2 two academic block" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0774280,76.8514190")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 2 to boys hostel 1" in output ||("boys hostel 2 2 boys hostel 1" in output)||("boys hostel 22 boys hostel 1" in output)||("boys hostel 2 two boys hostel 1" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 2 to boys hostel 3" in output ||("boys hostel 2 2 boys hostel 3" in output)||("boys hostel 22 boys hostel 3" in output)||("boys hostel 2 two boys hostel 3" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 2 to girls hostel" in output ||("boys hostel 2 2 girls hostel" in output)||("boys hostel 22 girls hostel" in output)||("boys hostel 2 two girls hostel" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.075020,76.852552")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 2 to underbelly" in output ||("boys hostel 2 2 underbelly" in output)||("boys hostel 2 two underbelly" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.077430,76.850581")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 2 to visa mart" in output ||("boys hostel 2 2 visa mart" in output)||("boys hostel 22 visa mart" in output)||("boys hostel 2 two visa mart" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0773617,76.8509223")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 2 to lab complex" in output ||("boys hostel 2 2 lab complex" in output)||("boys hostel 22 lab complex" in output)||("boys hostel 2 two lab complex" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0783234,76.8504043")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 2 to multipurpose hall" in output ||("boys hostel 2 2 multipurpose hall" in output)||("boys hostel 22 multipurpose hall" in output)||("boys hostel 2 two multipurpose hall" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.076180,76.849838")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 2 to auditorium" in output ||("boys hostel 2 2 auditorium" in output)||("boys hostel 22 auditorium" in output)||("boys hostel 2 two auditorium" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.077920,76.851285")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 2 to parking area" in output ||("boys hostel 2 2 parking area" in output)||("boys hostel 22 parking area" in output)||("boys hostel 2 two parking area" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0746557,76.8503436")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 2 to post office" in output ||("boys hostel 2 2 post office" in output)||("boys hostel 22 post office" in output)||("boys hostel 2 two post office" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.076110,76.849938")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }

                    // Different Locations to boys hostel 2
                    else if("academic block to boys hostel 2" in output ||("academic block 2 boys hostel 2" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/" + "23.0774280,76.8514190" + "/" + "New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 1 to boys hostel 2" in output ||("boys hostel 1 2 boys hostel 2" in output)||("boys hostel 12 boys hostel 2" in output)||("boys hostel 1 two boys hostel 2" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075293,76.859885"+"/"+"New Vit Bhopal Boys Hostel")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 3 to boys hostel 2" in output ||("boys hostel 3 2 boys hostel 2" in output)||("boys hostel 32 boys hostel 2" in output)||("boys hostel 3 two boys hostel 2" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+"New Vit Bhopal Boys Hostel"+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("girls hostel to boys hostel 2" in output ||("girls hostel 2 boys hostel 2" in output)){

                            var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+"23.075020,76.852552"+"/"+"New Vit Bhopal Boys Hostel")
                            val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                            intent.setPackage("com.google.android.apps.maps")
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                    }
                    else if("underbelly to boys hostel 2" in output ||("underbelly 2 boys hostel 2" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+"23.077430,76.850581"+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("visa mart to boys hostel 2" in output ||("visa mart 2 boys hostel 2" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+"23.0773617,76.8509223"+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("lab complex to boys hostel 2" in output ||("lab complex 2 boys hostel 2" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+"23.0783234,76.8504043"+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("multipurpose hall to boys hostel 2" in output ||("multipurpose hall 2 boys hostel 2" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+"23.076180,76.849838"+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("auditorium to boys hostel 2" in output ||("auditorium 2 boys hostel 2" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+"23.077920,76.851285"+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("parking area to boys hostel 2" in output ||("parking area 2 boys hostel 2" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+"23.0746557,76.8503436"+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("post office to boys hostel 2" in output ||("post office 2 boys hostel 2" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+"23.076110,76.849938"+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                }








                if("boys hostel 3" in output){

                    // Boys hostel 3 to different location
                    val source="New Vit Bhopal Boys Hostel"
                    if("boys hostel 3 to academic block" in output ||("boys hostel 3 2 academic block" in output )||("boys hostel 32 academic block" in output )||("boys hostel 3 two academic block" in output )){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0774280,76.8514190")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }

                    else if("boys hostel 3 to boys hostel 1" in output ||("boys hostel 3 2 boys hostel 1" in output )||("boys hostel 32 boys hostel 1" in output )||("boys hostel 3 two boys hostel 1" in output )){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 3 to boys hostel 2" in output ||("boys hostel 3 2 boys hostel 2" in output )||("boys hostel 32 boys hostel 2" in output )||("boys hostel 3 two boys hostel 2" in output )){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 3 to girls hostel" in output ||("boys hostel 3 2 girls hostel" in output )||("boys hostel 32 girls hostel" in output )||("boys hostel 3 two girls hostel" in output )){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.075020,76.852552")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 3 to underbelly" in output ||("boys hostel 3 2 underbelly" in output )||("boys hostel 32 underbelly" in output )||("boys hostel 3 two underbelly" in output )){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.077430,76.850581")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 3 to visa mart" in output ||("boys hostel 3 2 visa mart" in output )||("boys hostel 32 visa mart" in output )||("boys hostel 3 two visa mart" in output )){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0773617,76.8509223")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 3 to lab complex" in output ||("boys hostel 3 2 lab complex" in output )||("boys hostel 32 lab complex" in output )||("boys hostel 3 two lab complex" in output )){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0783234,76.8504043")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 3 to multipurpose hall" in output ||("boys hostel 3 2 multipurpose hall" in output )||("boys hostel 3 two multipurpose hall" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.076180,76.849838")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 3 to auditorium" in output ||("boys hostel 3 2 auditorium" in output )||("boys hostel 32 auditorium" in output )||("boys hostel 3 two auditorium" in output )){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.077920,76.851285")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 3 to parking area" in output ||("boys hostel 3 2 parking area" in output )||("boys hostel 32 parking area" in output )||("boys hostel 3 two parking area" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0746557,76.8503436")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 3 to post office" in output ||("boys hostel 3 2 post office" in output )||("boys hostel 32 post office" in output )||("boys hostel 3 two post office" in output )){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.076110,76.849938")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }

                    // Different location to boys hostel 3
                    else if("academic block to boys hostel 3" in output ||("academic block 2 boys hostel 3" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/" + "23.0774280,76.8514190" + "/" + "New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 1 to boys hostel 3" in output ||("boys hostel 1 2 boys hostel 3" in output)||("boys hostel 12 boys hostel 3" in output)||("boys hostel 1 two boys hostel 3" in output)) {
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075293,76.859885"+"/"+"New Vit Bhopal Boys Hostel")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 2 to boys hostel 3" in output ||("boys hostel 2 2 boys hostel 3" in output)||("boys hostel 22 boys hostel 3" in output)||("boys hostel 2 two boys hostel 3" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+"New Vit Bhopal Boys Hostel"+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("girls hostel to boys hostel 3" in output ||("girls hostel 2 boys hostel 3" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+"23.075020,76.852552"+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("underbelly to boys hostel 3" in output ||("underbelly 2 boys hostel 3" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+"23.077430,76.850581"+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("visa mart to boys hostel 3" in output ||("visa mart 2 boys hostel 3" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+"23.0773617,76.8509223"+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("lab complex to boys hostel 3" in output ||("lab complex 2 boys hostel 3" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+"23.0783234,76.8504043"+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("multipurpose hall to boys hostel 3" in output ||("multipurpose hall 2 boys hostel 3" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+"23.076180,76.849838"+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("auditorium to boys hostel 3" in output ||("auditorium 2 boys hostel 3" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+"23.077920,76.851285"+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("parking area to boys hostel 3" in output ||("parking area 2 boys hostel 3" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+"23.0746557,76.8503436"+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("post office to boys hostel 3" in output ||("post office 2 boys hostel 3" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+"23.076110,76.849938"+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                }





                if("girls hostel" in output){
                    // Girls hostel to different location
                    val source="23.075020,76.852552"
                    if("girls hostel to academic block" in output ||("girls hostel 2 academic block" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0774280,76.8514190")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("girls hostel to boys hostel 1" in output ||("girls hostel 2 boys hostel 1" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("girls hostel to boys hostel 2" in output ||("girls hostel 2 boys hostel 2" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("girls hostel to boys hostel 3" in output ||("girls hostel 2 boys hostel 3" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("girls hostel to underbelly" in output ||("girls hostel 2 underbelly" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.077430,76.850581")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("girls hostel to visa mart" in output ||("girls hostel 2 visa mart" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0773617,76.8509223")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("girls hostel to lab complex" in output ||("girls hostel 2 lab complex" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0783234,76.8504043")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("girls hostel to multipurpose hall" in output ||("girls hostel 2 multipurpose hall" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.076180,76.849838")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("girls hostel to auditorium" in output ||("girls hostel 2 auditorium" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.077920,76.851285")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("girls hostel to parking area" in output ||("girls hostel 2 parking area" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0746557,76.8503436")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("girls hostel to post office" in output ||("girls hostel 2 post office" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.076110,76.849938")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }

                    // Different locations to girls hostel
                    else if("academic block to girls hostel" in output ||("academic block 2 girls hostel" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"23.075020,76.852552")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 1 to girls hostel" in output ||("boys hostel 1 2 girls hostel" in output)||("boys hostel 12 girls hostel" in output)||("boys hostel 1 two girls hostel" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"23.075020,76.852552")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 2 to girls hostel" in output ||("boys hostel 2 2 girls hostel" in output)||("boys hostel 22 girls hostel" in output)||("boys hostel 2 two girls hostel" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"New Vit Bhopal Boys Hostel"+"/"+"23.075020,76.852552")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 3 to girls hostel" in output ||("boys hostel 3 2 girls hostel" in output)||("boys hostel 32 girls hostel" in output)||("boys hostel 3 two girls hostel" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"New Vit Bhopal Boys Hostel"+"/"+"23.075020,76.852552")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("underbelly to girls hostel" in output ||("underbelly 2 girls hostel" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.077430,76.850581"+"/"+"23.075020,76.852552")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("visa mart to girls hostel" in output ||("visa mart 2 girls hostel" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0773617,76.8509223"+"/"+"23.075020,76.852552")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("lab complex to girls hostel" in output ||("lab complex 2 girls hostel" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0783234,76.8504043"+"/"+"23.075020,76.852552")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("multipurpose hall to girls hostel" in output ||("multipurpose hall 2 girls hostel" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.076180,76.849838"+"/"+"23.075020,76.852552")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("auditorium to girls hostel" in output ||("auditorium 2 girls hostel" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.077920,76.851285"+"/"+"23.075020,76.852552")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("parking area to girls hostel" in output ||("parking area 2 girls hostel" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0746557,76.8503436"+"/"+"23.075020,76.852552")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("post office to girls hostel" in output ||("post office 2 girls hostel" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.076110,76.849938"+"/"+"23.075020,76.852552")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                }
                if("underbelly" in output){
                    // underbelly to different location
                    val source="23.077430,76.850581"
                    if("underbelly to academic block" in output||("underbelly 2 academic block" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0774280,76.8514190")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }

                    else if("underbelly to boys hostel 1" in output||("underbelly 2 boys hostel 1" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("underbelly to boys hostel 2" in output||("underbelly 2 boys hostel 2" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("underbelly to boys hostel 3" in output||("underbelly 2 boys hostel 3" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("underbelly to girls hostel" in output||("underbelly 2 girls hostel" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.075020,76.852552")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("underbelly to visa mart" in output||("underbelly 2 visa mart" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0773617,76.8509223")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("underbelly to lab complex" in output||("underbelly 2 lab complex" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0783234,76.8504043")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("underbelly to multipurpose hall" in output||("underbelly 2 multipurpose hall" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.076180,76.849838")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("underbelly to auditorium" in output||("underbelly 2 auditorium" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.077920,76.851285")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("underbelly to parking area" in output||("underbelly 2 parking area" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0746557,76.8503436")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("underbelly to post office" in output||("underbelly 2 post office" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.076110,76.849938")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }

                    // Differnet locations to underbelly
                    else if("academic block to underbelly" in output ||("academic block 2 underbelly" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"23.077430,76.850581")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }






                    else if("boys hostel 1 to underbelly" in output ||("boys hostel 1 2 underbelly" in output)||("boys hostel 12 underbelly" in output)||("boys hostel 1 two underbelly" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075293,76.859885"+"/"+"23.077430,76.850581")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 2 to underbelly" in output ||("boys hostel 2 2 underbelly" in output)||("boys hostel 22 underbelly" in output)||("boys hostel 2 two underbelly" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"New Vit Bhopal Boys Hostel"+"/"+"23.077430,76.850581")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 3 to underbelly" in output ||("boys hostel 3 2 underbelly" in output)||("boys hostel 32 underbelly" in output)||("boys hostel 3 two underbelly" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"New Vit Bhopal Boys Hostel"+"/"+"23.077430,76.850581")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("girls hostel to underbelly" in output ||("girls hostel 2 underbelly" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075020,76.852552"+"/"+"23.077430,76.850581")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("visa mart to underbelly" in output ||("visa mart 2 underbelly" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0773617,76.8509223"+"/"+"23.077430,76.850581")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("multipurpose hall to underbelly" in output ||("multipurpose hall 2 underbelly" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"23.076180,76.849838")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }


                    else if("lab complex to underbelly" in output ||("lab complex 2 underbelly" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0783234,76.8504043"+"/"+"23.077430,76.850581")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }


                    else if("auditorium to underbelly" in output ||("auditorium 2 underbelly" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.077920,76.851285"+"/"+"23.077430,76.850581")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("parking area to underbelly" in output ||("parking area 2 underbelly" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0746557,76.8503436"+"/"+"23.077430,76.850581")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("post office to underbelly" in output ||("post office 2 underbelly" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.076110,76.849938"+"/"+"23.077430,76.850581")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }




















                }
                if("visa mart" in output){

                    // Visa mart to different locations
                    val source="23.0773617,76.8509223"
                    if("visa mart to academic block" in output ||("visa mart 2 academic block" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0774280,76.8514190")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("visa mart to boys hostel 1" in output ||("visa mart 2 boys hostel 1" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("visa mart to boys hostel 2" in output ||("visa mart 2 boys hostel 2" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("visa mart to boys hostel 3" in output ||("visa mart 2 boys hostel 3" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("visa mart to girls hostel" in output ||("visa mart 2 girls hostel" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.075020,76.852552")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("visa mart to underbelly" in output ||("visa mart 2 underbelly" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.077430,76.850581")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("visa mart to lab complex" in output ||("visa mart 2 lab complex" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0783234,76.8504043")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("visa mart to multipurpose hall" in output ||("visa mart 2 multipurpose hall" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.076180,76.849838")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("visa mart to auditorium" in output ||("visa mart 2 auditorium" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.077920,76.851285")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("visa mart to parking area" in output ||("visa mart 2 parking area" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0746557,76.8503436")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("visa mart to post office" in output ||("visa mart 2 post office" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.076110,76.849938")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }


// Start from here



                    // Different locations to visa mart
                    else if("academic block to visa mart" in output||("academic block 2 visa mart" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"23.0773617,76.8509223")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("underbelly to visa mart" in output||("underbelly 2 visa mart" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.077430,76.850581"+"/"+"23.0773617,76.8509223")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 1 to visa mart" in output||("boys hostel 1 2 visa mart" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075293,76.859885"+"/"+"23.0773617,76.8509223")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 2 to visa mart" in output||("boys hostel 2 2 visa mart" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"New Vit Bhopal Boys Hostel"+"/"+"23.0773617,76.8509223")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 3 to visa mart" in output||("boys hostel 3 visa mart" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"New Vit Bhopal Boys Hostel"+"/"+"23.0773617,76.8509223")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("girls hostel to visa mart" in output||("girls hostel 2 visa mart" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075020,76.852552"+"/"+"23.0773617,76.8509223")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("auditorium to visa mart" in output||("auditorium 2 visa mart" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.077920,76.851285"+"/"+"23.0773617,76.8509223")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("multipurpose hall to visa mart" in output||("multipurpose hall 2 visa mart" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.076180,76.849838"+"/"+"23.0773617,76.8509223")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("lab complex to visa mart" in output||("lab complex 2 visa mart" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0783234,76.8504043"+"/"+"23.0773617,76.8509223")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("parking area to visa mart" in output||("parking area 2 visa mart" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0746557,76.8503436"+"/"+"23.0773617,76.8509223")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("post office to visa mart" in output||("post office 2 visa mart" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.076110,76.849938"+"/"+"23.0773617,76.8509223")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                }



                if("lab complex" in output){
                    // lab complex to different locations
                    val source="23.0783234,76.8504043"






                    if("lab complex to academic block" in output ||("lab complex 2 academic block" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0774280,76.8514190")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }

                    else if("lab complex to boys hostel 1" in output ||("lab complex 2 boys hostel 1" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("lab complex to boys hostel 2" in output ||("lab complex 2 boys hostel 2" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("lab complex to boys hostel 3" in output ||("lab complex 2 boys hostel 3" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("lab complex to girls hostel" in output ||("lab complex 2 girls hostel" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.075020,76.852552")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("lab complex to underbelly" in output ||("lab complex 2 underbelly" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.077430,76.850581")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("lab complex to visa mart" in output ||("lab complex 2 visa mart" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0773617,76.8509223")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("lab complex to multipurpose hall" in output ||("lab complex 2 multipurpose hall" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.076180,76.849838")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("lab complex to auditorium" in output ||("lab complex 2 auditorium" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.077920,76.851285")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("lab complex to parking area" in output ||("lab complex 2 parking area" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0746557,76.8503436")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("lab complex to post office" in output ||("lab complex 2 post office" in output)) {
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.076110,76.849938")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }









                    // different locations to lab complex

                    else if("academic block to lab complex" in output ||("academic block 2 lab complex" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"23.0783234,76.8504043")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 1 to lab complex" in output ||("boys hostel 1 2 lab complex" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075293,76.859885"+"/"+"23.0783234,76.8504043")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 2 to lab complex" in output ||("boys hostel 2 2 lab complex" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"New Vit Bhopal Boys Hostel"+"/"+"23.0783234,76.8504043")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 3 to lab complex" in output ||("boys hostel 3 2 lab complex" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"New Vit Bhopal Boys Hostel"+"/"+"23.0783234,76.8504043")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("girls hostel to lab complex" in output ||("girls hostel 2 lab complex" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075020,76.852552"+"/"+"23.0783234,76.8504043")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("underbelly to lab complex" in output ||("underbelly 2 lab complex" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.077430,76.850581"+"/"+"23.0783234,76.8504043")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("visa mart to lab complex" in output ||("visa mart 2 lab complex" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0773617,76.8509223"+"/"+"23.0783234,76.8504043")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("multipurpose hall to lab complex" in output ||("multipurpose hall 2 lab complex" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.076180,76.849838"+"/"+"23.0783234,76.8504043")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("auditorium to lab complex" in output ||("auditorium 2 lab complex" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.077920,76.851285"+"/"+"23.0783234,76.8504043")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("parking area to lab complex" in output ||("parking area 2 lab complex" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0746557,76.8503436"+"/"+"23.0783234,76.8504043")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("post office to lab complex" in output ||("post office 2 lab complex" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.076110,76.849938"+"/"+"23.0783234,76.8504043")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                }




                if("multipurpose hall" in output){

                    // multipurpose hall to different locations
                    val source="23.076180,76.849838"
                    if("multipurpose hall to academic block" in output ||("multipurpose hall 2 academic block" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0774280,76.8514190")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }

                    else if("multipurpose hall to boys hostel 1" in output ||("multipurpose hall 2 boys hostel 1" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("multipurpose hall to boys hostel 2" in output ||("multipurpose hall 2 boys hostel 2" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("multipurpose hall to boys hostel 3" in output ||("multipurpose hall 2 boys hostel 3" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("multipurpose hall to girls hostel" in output ||("multipurpose hall 2 girls hostel" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.075020,76.852552")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("multipurpose hall to underbelly" in output ||("multipurpose hall 2 underbelly" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.077430,76.850581")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("multipurpose hall to lab complex" in output ||("multipurpose hall 2 lab complex" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0783234,76.8504043")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("multipurpose hall to visa mart" in output ||("multipurpose hall 2 visa mart" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0773617,76.8509223")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("multipurpose hall to auditorium" in output ||("multipurpose hall 2 auditorium" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.077920,76.851285")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("multipurpose hall to parking area" in output ||("multipurpose hall 2 parking area" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0746557,76.8503436")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("multipurpose hall to post office" in output ||("multipurpose hall 2 post office" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.076110,76.849938")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    // Different locations to multipurpose hall



                    else if("academic block to multipurpose hall" in output ||("academic block 2 multipurpose hall" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"23.076180,76.849838")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 1 to multipurpose hall" in output ||("boys hostel 2 multipurpose hall" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075293,76.859885"+"/"+"23.076180,76.849838")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 2 to multipurpose hall" in output ||("boys hostel 2 2 multipurpose hall" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"New Vit Bhopal Boys Hostel"+"/"+"23.076180,76.849838")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 3 to multipurpose hall" in output ||("boys hostel 3 2 multipurpose hall" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"New Vit Bhopal Boys Hostel"+"/"+"23.076180,76.849838")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("girls hostel to multipurpose hall" in output ||("girls hostel 2 multipurpose hall" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075020,76.852552"+"/"+"23.076180,76.849838")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("underbelly to multipurpose hall" in output ||("underbelly 2 multipurpose hall" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.077430,76.850581"+"/"+"23.076180,76.849838")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("visa mart to multipurpose hall" in output ||("visa mart 2 multipurpose hall" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"23.076180,76.849838")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }



                    else if("lab complex to multipurpose hall" in output ||("lab complex 2 multipurpose hall" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"23.076180,76.849838")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("auditorium to multipurpose hall" in output ||("auditorium 2 multipurpose hall" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.077920,76.851285"+"/"+"23.076180,76.849838")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("parking area to multipurpose hall" in output ||("parking area 2 multipurpose hall" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0746557,76.8503436"+"/"+"23.076180,76.849838")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("post office to multipurpose hall" in output ||("post office 2 multipurpose hall" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.076110,76.849938"+"/"+"23.076180,76.849838")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }















                }
                if("auditorium" in output){
                    // auditorium to different locations
                    val source="23.077920,76.851285"
                    if("auditorium to academic block" in output ||("auditorium 2 academic block" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0774280,76.8514190")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }

                    else if("auditorium to boys hostel 1" in output ||("auditorium 2 boys hostel 1" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("auditorium to boys hostel 2" in output ||("auditorium 2 boys hostel 2" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("auditorium to boys hostel 3" in output ||("auditorium 2 boys hostel 3" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("auditorium to girls hostel" in output ||("auditorium 2 girls hostel" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.075020,76.852552")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("auditorium to underbelly" in output ||("auditorium 2 underbelly" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.077430,76.850581")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("auditorium to lab complex" in output ||("auditorium 2 lab complex" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0783234,76.8504043")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("auditorium to visa mart" in output ||("auditorium 2 visa mart" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0773617,76.8509223")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("auditorium to multipurpose hall" in output ||("auditorium 2 multipurpose hall" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"223.076180,76.849838")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("auditorium to parking area" in output ||("auditorium 2 parking area" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0746557,76.8503436")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("auditorium to post office" in output ||("auditorium 2 post office" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.076110,76.849938")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }

                    // Different locations to auditorium
                    else if("academic block to auditorium" in output ||("academic block 2 auditorium" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"23.077920,76.851285")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 1 to auditorium" in output ||("boys hostel 1 2 auditorium" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075293,76.859885"+"/"+"23.077920,76.851285")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 2 to auditorium" in output ||("boys hostel 2 2 auditorium" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"New Vit Bhopal Boys Hostel"+"/"+"23.077920,76.851285")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 3 to auditorium" in output ||("boys hostel 3 2 auditorium" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"New Vit Bhopal Boys Hostel"+"/"+"23.077920,76.851285")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("girls hostel to auditorium" in output ||("girls hostel 2 auditorium" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075020,76.852552"+"/"+"23.077920,76.851285")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("underbelly to auditorium" in output ||("underbelly 2 auditorium" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.077430,76.850581"+"/"+"23.077920,76.851285")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("visa mart to auditorium" in output ||("visa mart 2 auditorium" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0773617,76.8509223"+"/"+"23.077920,76.851285")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("lab complex to auditorium" in output ||("lab complex 2 auditorium" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0783234,76.8504043"+"/"+"23.077920,76.851285")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("multipurpose hall to auditorium" in output ||("multipurpose hall 2 auditorium" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.076180,76.849838"+"/"+"23.077920,76.851285")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("parking area to auditorium" in output ||("parking area 2 auditorium" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0746557,76.8503436"+"/"+"23.077920,76.851285")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("post office to auditorium" in output ||("post office 2 auditorium" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.076110,76.849938"+"/"+"23.077920,76.851285")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }



                }
                if("parking area" in output){
                    // parking area to different locations
                    val source="23.0746557,76.8503436"
                    if("parking area to academic block" in output||("parking area 2 academic block" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0774280,76.8514190")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }

                    else  if("parking area to boys hostel 1" in output||("parking area 2 boys hostel 1" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else  if("parking area to boys hostel 2" in output||("parking area 2 boys hostel 2" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else  if("parking area to boys hostel 3" in output||("parking area 2 boys hostel 3" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else  if("parking area to girls hostel" in output||("parking area 2 girls hostel" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.075020,76.852552")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else  if("parking area to underbelly" in output||("parking area 2 underbelly" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.077430,76.850581")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else  if("parking area to lab complex" in output||("parking area 2 lab complex" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0783234,76.8504043")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else  if("parking area to visa mart" in output||("parking area 2 visa mart" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0773617,76.8509223")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else  if("parking area to multipurpose hall" in output||("parking area 2 multipurpose hall" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"223.076180,76.849838")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else  if("parking area to auditorium" in output||("parking area 2 auditorium" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.077920,76.851285")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else  if("parking area to post office" in output||("parking area 2 post office" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.076110,76.849938")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }



                    // different locations to parking area
                    else if("academic block to parking area" in output || ("academic block 2 parking area" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"23.0746557,76.8503436")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 1 to parking area" in output || ("boys hostel 1 2 parking area" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075293,76.859885"+"/"+"23.0746557,76.8503436")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 2 to parking area" in output || ("boys hostel 2 2 parking area" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"New Vit Bhopal Boys Hostel"+"/"+"23.0746557,76.8503436")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("boys hostel 3 to parking area" in output || ("boys hostel 3 2 parking area" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"New Vit Bhopal Boys Hostel"+"/"+"23.0746557,76.8503436")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("girls hostel to parking area" in output || ("girls hostel 2 parking area" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075020,76.852552"+"/"+"23.0746557,76.8503436")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("underbelly to parking area" in output || ("underbelly 2 parking area" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.077430,76.850581"+"/"+"23.0746557,76.8503436")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("visa mart to parking area" in output || ("visa mart 2 parking area" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0773617,76.8509223"+"/"+"23.0746557,76.8503436")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("lab complex to parking area" in output || ("lab complex 2 parking area" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0783234,76.8504043"+"/"+"23.0746557,76.8503436")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("multipurpose hall to parking area" in output || ("multipurpose hall 2 parking area" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.076180,76.849838"+"/"+"23.0746557,76.8503436")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("auditorium to parking area" in output || ("auditorium 2 parking area" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.077920,76.851285"+"/"+"23.0746557,76.8503436")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("post office to parking area" in output || ("poset office 2 parking area" in output)){
                        var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.076110,76.849938"+"/"+"23.0746557,76.8503436")
                        val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                }
                if("post office" in output){

                    // post office to different locations

                    val source="23.076110,76.849938"
                   if("post office to academic block" in output ||("post office 2 academic block" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0774280,76.8514190")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else if("post office to boys hostel 1" in output ||("post office 2 boys hostel 1" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.075293,76.859885")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                   else if("post office to boys hostel 2" in output ||("post office 2 boys hostel 2" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                   else if("post office to boys hostel 3" in output ||("post office 2 boys hostel 3" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"New Vit Bhopal Boys Hostel")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                   else if("post office to girls hostel" in output ||("post office 2 girls hostel" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.075020,76.852552")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                   else if("post office to underbelly" in output ||("post office 2 underbelly" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.077430,76.850581")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                   else if("post office to lab complex" in output ||("post office 2 lab complex" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0783234,76.8504043")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                   else if("post office to visa mart" in output ||("post office 2 visa mart" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0773617,76.8509223")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                   else if("post office to multipurpose hall" in output ||("post office 2 multipurpose hall" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"223.076180,76.849838")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                   else if("post office to auditorium" in output ||("post office 2 auditorium" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.077920,76.851285")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                   else if("post office to parking area" in output ||("post office 2 parking area" in output)){
                        var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0746557,76.8503436")
                        val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                        intent.setPackage("com.google.android.apps.maps")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                   }






                   // different locations to post office
                   else if("academic block to post office" in output||("academic block 2 post office" in output)){
                       var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"23.076110,76.849938")
                       val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                       intent.setPackage("com.google.android.apps.maps")
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                       startActivity(intent)
                   }
                   else if("boys hostel 1 to post office" in output||("boys hostel 1 2 post office" in output)){
                       var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075293,76.859885"+"/"+"23.076110,76.849938")
                       val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                       intent.setPackage("com.google.android.apps.maps")
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                       startActivity(intent)
                   }
                   else if("boys hostel 2 to post office" in output||("boys hostel 2 2 post office" in output)){
                       var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"New Vit Bhopal Boys Hostel"+"/"+"23.076110,76.849938")
                       val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                       intent.setPackage("com.google.android.apps.maps")
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                       startActivity(intent)
                   }
                   else if("boys hostel 3 to post office" in output||("boys hostel 3 2 post office" in output)){
                       var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"New Vit Bhopal Boys Hostel"+"/"+"23.076110,76.849938")
                       val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                       intent.setPackage("com.google.android.apps.maps")
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                       startActivity(intent)
                   }

                   else if("girls hostel to post office" in output||("girls hostel 2 post office" in output)){
                       var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.075020,76.852552"+"/"+"23.076110,76.849938")
                       val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                       intent.setPackage("com.google.android.apps.maps")
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                       startActivity(intent)
                   }
                   else if("underbelly to post office" in output||("underbelly 2 post office" in output)){
                       var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.077430,76.850581"+"/"+"23.076110,76.849938")
                       val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                       intent.setPackage("com.google.android.apps.maps")
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                       startActivity(intent)
                   }
                   else if("visa mart to post office" in output||("visa mart 2 post office" in output)){
                       var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0773617,76.8509223"+"/"+"23.076110,76.849938")
                       val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                       intent.setPackage("com.google.android.apps.maps")
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                       startActivity(intent)
                   }
                   else if("lab complex to post office" in output||("lab complex 2 post office" in output)){
                       var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0783234,76.8504043"+"/"+"23.076110,76.849938")
                       val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                       intent.setPackage("com.google.android.apps.maps")
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                       startActivity(intent)
                   }
                   else if("multipurpose hall to post office" in output||("multipurpose hall 2 post office" in output)){
                       var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.076180,76.849838"+"/"+"23.076110,76.849938")
                       val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                       intent.setPackage("com.google.android.apps.maps")
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                       startActivity(intent)
                   }
                   else if("auditorium to post office" in output||("auditorium 2 post office" in output)){
                       var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.077920,76.851285"+"/"+"23.076110,76.849938")
                       val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                       intent.setPackage("com.google.android.apps.maps")
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                       startActivity(intent)
                   }
                   else if("parking area to post office" in output||("parking area 2 post office" in output)){
                       var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0746557,76.8503436"+"/"+"23.076110,76.849938")
                       val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                       intent.setPackage("com.google.android.apps.maps")
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                       startActivity(intent)
                   }
                }

                else{
                    Toast.makeText(this,"Speak both source and destination",Toast.LENGTH_LONG).show()
                }


            }
        }
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return true
    }



}