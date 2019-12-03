package com.yasserelgammal.livesearch

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.yasserelgammal.livesearch.api.UserApi
import com.yasserelgammal.livesearch.model.User
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchContact("users", "")
    }

    private fun fetchContact(type: String, key: String) {

        UserApi().getContact(type, key).enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(applicationContext, "Failed to Connect to API", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                prograssBar.visibility = View.GONE
                val users = response.body()
                val adapter = UserAdapter(users!!)
                recycleMain.layoutManager = LinearLayoutManager(applicationContext)
                recycleMain.adapter = adapter
                adapter.notifyDataSetChanged()
                }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_toolbar,menu)
        val menuItem = menu.findItem(R.id.actionSearch)
        val searchView = menuItem.actionView as SearchView
        searchView.setIconifiedByDefault(false)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                fetchContact("users", query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                fetchContact("users", newText!!)
                return false
            }
        })

    return true
    }
}


