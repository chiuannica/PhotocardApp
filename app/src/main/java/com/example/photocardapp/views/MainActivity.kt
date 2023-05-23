package com.example.photocardapp.views
import PhotocardsViewModel
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.photocardapp.R
import com.example.photocardapp.databinding.ActivityMainBinding
import com.example.photocardapp.models.PhotocardModel

class MainActivity : AppCompatActivity() {

    private lateinit var photocardsList: List<PhotocardModel>
    private lateinit var viewModel: PhotocardsViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        viewModel = ViewModelProvider(this)[PhotocardsViewModel::class.java]
        viewModel.loadPhotocards()
        viewModel.photocardsLiveData().observe(this) { photocards ->
            photocardsList = photocards
        }

        binding.fab.setOnClickListener {
            sendEmail()
        }
//
//        database = Room.d(applicationContext, PhotocardDatabase::class.java, "photocard-db")
//            .build()

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    fun sendEmail() {
        val emailIntent = Intent(Intent.ACTION_SEND_MULTIPLE).apply {
            putExtra(Intent.EXTRA_SUBJECT, "My Photocards!")
            putExtra(Intent.EXTRA_TEXT, "These are my photocards!")

            val photocardsTitleAndNameList = StringBuilder()

            photocardsList.forEach { photocard ->
                photocardsTitleAndNameList.append("${photocard.idolName}: ${photocard.title}\n")
            }

            putExtra(Intent.EXTRA_TEXT, photocardsTitleAndNameList.toString())
        }

        if (emailIntent.resolveActivity(this.packageManager) != null) {
            startActivity(Intent.createChooser(emailIntent, "Send Email"))
        } else {
            Toast.makeText(this, "No email app found.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}