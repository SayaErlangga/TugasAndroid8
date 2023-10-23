package com.example.tugas8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.tugas8.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), DataListener, DataListener1 {
    lateinit var viewPager2: ViewPager2
    var usernameArray = arrayOf("")
    var passwordArray = arrayOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            usernameArray = arrayOf("")
            passwordArray = arrayOf("")

            viewPager.adapter = TabAdapter(this@MainActivity)
            viewPager2 = viewPager

            TabLayoutMediator(tabLayout, viewPager) {
                    tab, position->
                tab.text = when(position) {
                    0 -> "Register"
                    1 -> "Login"
                    else -> ""
                }
            }.attach()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDataReceived(
        dataUsername: String,
        dataEmail: String,
        dataPhone: String,
        dataPassword: String
    ) {
        usernameArray = usernameArray.plus(dataUsername)
        passwordArray = passwordArray.plus(dataPassword)

        viewPager2.setCurrentItem(1)

    }

    override fun onDataReceived1(dataUsername: String, dataPassword: String) {
        val intentToSecondActivity = Intent(this@MainActivity, ThirdActivity::class.java)
        val index = usernameArray.indexOf(dataUsername)
        if (index != -1 && passwordArray.getOrNull(index) == dataPassword) {
            startActivity(intentToSecondActivity)
        } else {
            Toast.makeText(this, "Email atau Password Salah", Toast.LENGTH_SHORT).show()
        }
    }
}