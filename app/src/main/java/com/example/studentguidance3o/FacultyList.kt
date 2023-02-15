package com.example.studentguidance3o

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.android.volley.*
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONException
import java.util.*

class FacultyList : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    private val facultyList = ArrayList<Faculty>()
    private val url = "https://recapitulative-cake.000webhostapp.com/getFaculty.php"
    private val deleteUrl = "https://recapitulative-cake.000webhostapp.com/deleteItem.php"
    private var fab: FloatingActionButton? = null
    private var searchView: SearchView? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    var myAdapter: MyAdapter2? = null
    var delete: MenuItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faculty_list)
        recyclerView = findViewById(R.id.recyclerView)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        fab = findViewById(R.id.fab)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        swipeRefreshLayout!!.setOnRefreshListener(OnRefreshListener {
            getListFromServer()
            //stop refreshing when the task is completed
            swipeRefreshLayout!!.setRefreshing(false)
        })
        fab!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@FacultyList, AddFaculty::class.java)
            startActivity(intent)
        })
        getListFromServer()


    }

    fun getListFromServer() {
        val faculties = java.util.ArrayList<Faculty>()
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    for (i in 0 until response.length()) {
                        val jsonObject = response.getJSONObject(i)
                        val faculty = Faculty(
                            jsonObject.getString("image"),
                            jsonObject.getString("name"),
                            jsonObject.getString("designation"),
                            jsonObject.getString("department"),
                            jsonObject.getString("CabinNo"),
                            jsonObject.getString("emailId")
                        )
                        faculties.add(faculty)
                    }
                    facultyList.removeAll(facultyList)
                    facultyList.addAll(faculties)
                    myAdapter = MyAdapter2(this@FacultyList, facultyList, delete!!)
                    recyclerView = findViewById(R.id.recyclerView)
                    recyclerView!!.setAdapter(myAdapter)
                    recyclerView!!.setLayoutManager(LinearLayoutManager(this@FacultyList))
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(this@FacultyList, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        ) { error -> Toast.makeText(this@FacultyList, error.toString(), Toast.LENGTH_SHORT).show() }
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(jsonArrayRequest)
    }

    override fun onResume() {
        super.onResume()
        getListFromServer()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.home_menus, menu)
        delete = menu.findItem(R.id.delete)
        delete!!.setVisible(false)
        val menuItem = menu.findItem(R.id.search)
        searchView = menuItem.actionView as SearchView


//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
//        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                val arrayList = java.util.ArrayList<Faculty>()
                for (i in facultyList.indices) {
                    if (facultyList[i].name.toLowerCase()
                            .contains(newText.lowercase(Locale.getDefault()))
                    ) {
                        arrayList.add(facultyList[i])
                    }
                }
                val myAdapter2 = MyAdapter2(this@FacultyList, arrayList, delete!!)
                recyclerView!!.adapter = myAdapter2
                recyclerView!!.layoutManager = LinearLayoutManager(this@FacultyList)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> {
                if (facultyList.size > 1) facultyList.removeAll(myAdapter!!.updatedList) else if (facultyList.size == 1) facultyList.clear()
                val newMyAdapter = MyAdapter2(this@FacultyList, facultyList, delete!!)
                recyclerView!!.adapter = newMyAdapter
                recyclerView!!.layoutManager = LinearLayoutManager(this)
                var i = 0
                while (i < myAdapter!!.updatedList.size) {
                    deleteFromServer(myAdapter!!.updatedList[i])
                    i++
                }
                delete!!.isVisible = false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteFromServer(faculty: Faculty) {
        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, deleteUrl,
            Response.Listener { response ->
                Toast.makeText(
                    this@FacultyList,
                    response,
                    Toast.LENGTH_SHORT
                ).show()
            },
            Response.ErrorListener { error ->
                Toast.makeText(
                    this@FacultyList,
                    error.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String>? {
                val hashMap = HashMap<String, String>()
                hashMap["user"] = faculty.email.trim()
                return hashMap
            }
        }
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }

    override fun onBackPressed() {
        if (!searchView!!.isIconified) {
            searchView!!.isIconified = true
        } else {
            super.onBackPressed()
        }
    }
}