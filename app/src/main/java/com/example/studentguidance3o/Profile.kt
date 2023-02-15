package com.example.studentguidance3o

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException

class Profile : AppCompatActivity() {
    private var facImage: ImageView? = null
    private var facName: TextView? = null
    private var facDesignation: TextView? = null
    private var cabinNoValue: TextView? = null
    private var emailValue: TextView? = null
    private var departmentValue: TextView? = null
    private var toolbar: Toolbar? = null
    private var saveBtn: Button? = null
    private var oldEmail: String? = null
    private var bitmap: Bitmap? = null
    private var encodedImageString: String? = null
    private val url = "https://recapitulative-cake.000webhostapp.com/update.php"
    private var imageChange = "false"


    private val storageResultLauncher = registerForActivityResult<String, Uri>(
        ActivityResultContracts.GetContent()
    ) { result ->
        try {
            if (result != null) {
                facImage!!.setImageURI(result)
                val inputStream = contentResolver.openInputStream(result)
                bitmap = BitmapFactory.decodeStream(inputStream)
                encodeBitmapImage(bitmap)
                imageChange = "true"
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun encodeBitmapImage(bitmap: Bitmap?) {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val resized: Bitmap?
        resized = if (bitmap!!.width > 300 && bitmap.height > 300) {
            Bitmap.createScaledBitmap(bitmap, 300, 300, true)
        } else {
            bitmap
        }
        resized!!.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)
        val bytesOfImage = byteArrayOutputStream.toByteArray()
        encodedImageString = Base64.encodeToString(bytesOfImage, Base64.DEFAULT)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        facImage = findViewById(R.id.facImage)
        facName = findViewById(R.id.facName)
        facDesignation = findViewById(R.id.facDesignation)
        cabinNoValue = findViewById(R.id.cabinNoValue)
        emailValue = findViewById(R.id.emailValue)
        departmentValue = findViewById(R.id.departmentValue)
        toolbar = findViewById(R.id.toolbar)
        saveBtn = findViewById(R.id.saveBtn)
        saveBtn!!.setVisibility(View.INVISIBLE)
        setSupportActionBar(toolbar)
        facImage!!.setClickable(false)

        saveBtn!!.setOnClickListener(View.OnClickListener {
            updateInServer()
            facName!!.setEnabled(false)
            facDesignation!!.setEnabled(false)
            cabinNoValue!!.setEnabled(false)
            emailValue!!.setEnabled(false)
            departmentValue!!.setEnabled(false)
            facImage!!.setClickable(false)
            facImage!!.setOnClickListener(null)
            saveBtn!!.setVisibility(View.INVISIBLE)
        })



        if (intent.getStringExtra("class") == "AddFaculty") {
            facImage!!.setImageURI(Uri.parse(intent.getStringExtra("facImage")))
            facName!!.setText(intent.getStringExtra("facName"))
            facDesignation!!.setText(intent.getStringExtra("facDesignation"))
            cabinNoValue!!.setText(intent.getStringExtra("facCabin"))
            emailValue!!.setText(intent.getStringExtra("facEmail"))
            departmentValue!!.setText(intent.getStringExtra("facDepartment"))
        } else if (intent.getStringExtra("class") == "MyAdapter2") {
            Glide.with(this)
                .load(intent.getStringExtra("facImage"))
                .into(facImage!!)

            //.error(R.drawable.imagenotfound);


            //facImage.setImageBitmap(getBitmapFromURL(getIntent().getStringExtra("facImage")));
            facName!!.setText(intent.getStringExtra("facName"))
            facDesignation!!.setText(intent.getStringExtra("facDesignation"))
            cabinNoValue!!.setText(intent.getStringExtra("facCabin"))
            emailValue!!.setText(intent.getStringExtra("facEmail"))
            departmentValue!!.setText(intent.getStringExtra("facDepartment"))
        }

    }

    private fun updateInServer() {
        val stringRequest: StringRequest = object : StringRequest(
            Request.Method.POST,
            "https://recapitulative-cake.000webhostapp.com/update.php",
            Response.Listener { response ->
                Toast.makeText(
                    this@Profile,
                    response,
                    Toast.LENGTH_SHORT
                ).show()
            },
            Response.ErrorListener { error ->
                Toast.makeText(
                    this@Profile,
                    error.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): MutableMap<String, String>? {

                    val hashMap = HashMap<String, String>()
                    hashMap["name"] = facName!!.text.toString().trim { it <= ' ' }
                    hashMap["designation"] = facDesignation!!.text.toString().trim { it <= ' ' }
                    hashMap["department"] = departmentValue!!.text.toString().trim { it <= ' ' }
                    hashMap["cabinNo"] = cabinNoValue!!.text.toString().trim { it <= ' ' }
                    hashMap["emailId"] = emailValue!!.text.toString().trim { it <= ' ' }
                    hashMap["imageChange"] = imageChange
                    hashMap["oldEmail"] = oldEmail!!
                    if (imageChange == "true") {
                        hashMap["image"] = encodedImageString!!
                    }
                    return hashMap
            }
        }
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.edit_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                run { onBackPressed() }
                run {
                    facName!!.isEnabled = true
                    facDesignation!!.isEnabled = true
                    cabinNoValue!!.isEnabled = true
                    emailValue!!.isEnabled = true
                    departmentValue!!.isEnabled = true
                    saveBtn!!.visibility = View.VISIBLE
                    oldEmail = emailValue!!.text.toString()
                    facImage!!.setOnClickListener { storageResultLauncher.launch("image/*") }
                }
            }
            R.id.edit -> {
                facName!!.isEnabled = true
                facDesignation!!.isEnabled = true
                cabinNoValue!!.isEnabled = true
                emailValue!!.isEnabled = true
                departmentValue!!.isEnabled = true
                saveBtn!!.visibility = View.VISIBLE
                oldEmail = emailValue!!.text.toString()
                facImage!!.setOnClickListener { storageResultLauncher.launch("image/*") }
            }
        }
        return true
    }
}