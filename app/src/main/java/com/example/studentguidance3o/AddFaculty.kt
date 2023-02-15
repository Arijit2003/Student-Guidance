package com.example.studentguidance3o

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException

class AddFaculty : AppCompatActivity() {
    private var facImage: ImageView? = null
    private var nameValue: EditText? = null
    private var designationValue: EditText? = null
    private var departmentValue: EditText? = null
    private var cabinValue: EditText? = null
    private var emailValue: EditText? = null
    private var encodedImageString: String? = null
    private var bitmap: Bitmap? = null
    private var ImageUri: String? = null
    private var cardView: CardView? = null
    var url = "https://recapitulative-cake.000webhostapp.com/postData.php"
    var requestStoragePermissionResult = registerForActivityResult<String, Boolean>(
        ActivityResultContracts.RequestPermission()
    ) { result ->
        if (result) {
            storageResultLauncher.launch("image/*")
        } else {
            popUpAlertDialog("Storage access denied", "Can't access photos")
        }
    }

    var storageResultLauncher = registerForActivityResult<String, Uri>(
        ActivityResultContracts.GetContent()
    ) { result ->
        try {
            if (result != null) {
                facImage!!.setImageURI(result)
                ImageUri = result.toString()
                val inputStream = contentResolver.openInputStream(result)
                bitmap = BitmapFactory.decodeStream(inputStream)
                encodeBitmapImage(bitmap)
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


    private fun popUpAlertDialog(title: String, message: String) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(title)
        dialog.setMessage(message)
        dialog.setPositiveButton(
            "Cancel"
        ) { dialog, which -> dialog.dismiss() }
        dialog.create().show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_faculty)
        facImage = findViewById(R.id.facImage)
        nameValue = findViewById(R.id.nameValue)
        designationValue = findViewById(R.id.designationValue)
        departmentValue = findViewById(R.id.departmentValue)
        cabinValue = findViewById(R.id.cabinValue)
        emailValue = findViewById(R.id.emailValue)
        cardView = findViewById(R.id.cardView)
        cardView!!.setOnClickListener(View.OnClickListener {
            requestStoragePermissionResult.launch(
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        })

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menus, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (facImage != null && nameValue != null && designationValue != null && departmentValue != null && cabinValue != null && emailValue != null) {
            when (item.itemId) {
                R.id.save -> {
                    saveToServer(
                        nameValue!!.getText().toString(),
                        designationValue!!.getText().toString(),
                        departmentValue!!.getText().toString(),
                        cabinValue!!.getText().toString(),
                        emailValue!!.getText().toString(),
                        encodedImageString!!
                    )
                    val intent = Intent(this, Profile::class.java)
                    intent.putExtra("facImage", ImageUri.toString())
                        .putExtra("facName", nameValue!!.getText().toString())
                        .putExtra("facDesignation", designationValue!!.getText().toString())
                        .putExtra("facCabin", cabinValue!!.getText().toString())
                        .putExtra("facEmail", emailValue!!.getText().toString())
                        .putExtra("facDepartment", departmentValue!!.getText().toString())
                        .putExtra("class", "AddFaculty")
                    startActivity(intent)
                    finish()
                }
            }
        } else {
            Toast.makeText(this, "fill all the fields", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun saveToServer(
        name: String,
        designationValue: String,
        departmentValue: String,
        cabinValue: String,
        emailValue: String,
        encodedImageString: String
    ) {
        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener { response ->
                Toast.makeText(
                    this@AddFaculty,
                    response,
                    Toast.LENGTH_SHORT
                ).show()
            },
            Response.ErrorListener { error ->
                Toast.makeText(
                    this@AddFaculty,
                    error.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String>? {
                val hashMap = HashMap<String, String>()
                hashMap["name"] = name
                hashMap["designation"] = designationValue
                hashMap["department"] = departmentValue
                hashMap["cabinno"] = cabinValue
                hashMap["emailId"] = emailValue
                hashMap["image"] = encodedImageString
                return hashMap
            }
        }
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }
}