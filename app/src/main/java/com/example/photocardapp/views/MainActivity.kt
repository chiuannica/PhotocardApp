package com.example.photocardapp.views
import PhotocardsViewModel
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.photocardapp.R
import com.example.photocardapp.databinding.ActivityMainBinding
import com.example.photocardapp.models.PhotocardModel
import com.example.photocardapp.repository.SharedPreferencesRepository

@SuppressLint("NewApi")
class MainActivity : AppCompatActivity() {

    private lateinit var photocardsList: List<PhotocardModel>
    private lateinit var viewModel: PhotocardsViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val sharedPreferencesRepository = SharedPreferencesRepository(this)
        viewModel = PhotocardsViewModel(sharedPreferencesRepository)

        viewModel.loadPhotocards()
        viewModel.photocardsLiveData.observe(this) { photocards ->
            photocardsList = photocards
        }

        binding.fab.hide()
//        binding.fab.setOnClickListener {
//            sendEmail()
//        }

        val navController = findNavController(R.id.nav_host_fragment_content_main)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.action_home ->
                    navController.navigate(R.id.action_global_FirstFragment)
                R.id.action_my_photocards ->
                    navController.navigate(R.id.action_global_PhotocardsFragment)
                R.id.action_choose_photocard ->
                    navController.navigate(R.id.action_global_ChooseFragment)
                R.id.action_yutaro ->
                    navController.navigate(R.id.action_global_YutaroFragment)
            }
            true
        }

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