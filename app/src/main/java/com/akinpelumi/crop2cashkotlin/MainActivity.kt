package com.akinpelumi.crop2cashkotlin

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.akinpelumi.crop2cashkotlin.R
import com.akinpelumi.crop2cashkotlin.adapter.ExhibitAdapter
import com.akinpelumi.crop2cashkotlin.callback.ISingleExhibitSelectionCallback
import com.akinpelumi.crop2cashkotlin.databinding.ActivityMainBinding
import com.akinpelumi.crop2cashkotlin.model.ExhibitModel
import com.akinpelumi.crop2cashkotlin.model.ExhibitsLoader
import com.akinpelumi.crop2cashkotlin.repository.RestExhibitsLoader

class MainActivity : AppCompatActivity(), ExhibitsLoader, ISingleExhibitSelectionCallback {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var exhibitList : List<ExhibitModel> ?= null
    private var exhibits : ExhibitAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setSupportActionBar(binding.toolbar)
//
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

        exhibitList?.let { viewToDisplay(it.size) }
        initView()
    }
    private fun initView(){
        //call transaction history java class
        if(exhibitList==null){
            binding.contentLoader.visibility = View.VISIBLE

            val restExhibitsLoader = RestExhibitsLoader(this)
            restExhibitsLoader.execute()
        }
        else if(exhibitList != null){
            exhibits = ExhibitAdapter(
                this,
                exhibitList!!,
                this
            )
            //set adapter on recycler
            binding.exhibitsRecycler.adapter = exhibits
        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    private fun viewToDisplay(listSize: Int){
        if(listSize == 0){
            binding.exhibitsRecycler.visibility = View.GONE
            binding.emptyExhibit.visibility = View.VISIBLE
        }
        else{
            binding.exhibitsRecycler.visibility = View.VISIBLE
            binding.emptyExhibit.visibility = View.GONE
        }
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

    override fun getExhibitList(_exhibitList: List<ExhibitModel?>?) {
        when{
            _exhibitList==null ->{
                //hide loader
                binding.contentLoader.visibility = View.GONE
                binding.emptyExhibit.visibility = View.VISIBLE
                Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT)
                return
            }
            _exhibitList != null -> {
                exhibitList = _exhibitList as List<ExhibitModel>?
                //instantiate adapter wit record in param
                exhibits = ExhibitAdapter(
                    this,
                    _exhibitList,
                    this
                )
                //set adapter on recycler
                binding.exhibitsRecycler.adapter = exhibits

                //hide loader
                binding.contentLoader.visibility = View.GONE
                binding.emptyExhibit.visibility = View.GONE
                binding.exhibitsRecycler.visibility = View.VISIBLE
            }
        }
    }

    override fun onSelect(item: ExhibitModel?) {
        Toast.makeText(this, item?.title +" item tapped", Toast.LENGTH_SHORT).show()
    }
}