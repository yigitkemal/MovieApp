package com.example.movieapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.view.menu.MenuAdapter
import androidx.appcompat.widget.Toolbar
import com.example.movieapp.R
import com.example.movieapp.adapter.ViewPagerAdapter
import com.example.movieapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle

val fragmentsArray = arrayOf(
    "Movies",
    "Series",
)

class MainActivity : AppCompatActivity() {

    // google api key
    // AIzaSyDNJ951Xhse0wpx_VJPCrcK6JrEanMMJbQ

    // tmdb API Key
    // a195e377afe07079b5ccdf8d794572ce

    // Example Api Request
    // https://api.themoviedb.org/3/movie/550?api_key=a195e377afe07079b5ccdf8d794572ce

    // for image
    // https://image.tmdb.org/t/p/w500/500xj7l72BojMZ3tNBJY46tg5YJ.jpg

    // daily trends
    // https://api.themoviedb.org/3/trending/all/day?api_key=a195e377afe07079b5ccdf8d794572ce

    // popular
    // https://api.themoviedb.org/3/movie/popular?api_key=a195e377afe07079b5ccdf8d794572ce

    // for movie
    // https://api.themoviedb.org/3/movie/550?api_key=a195e377afe07079b5ccdf8d794572ce

    private lateinit var binding: ActivityMainBinding
    private var mViewHolder: ViewHolder? = null
    private var mMenuAdapter: MenuAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //duo nav init
        mViewHolder = ViewHolder()
        setSupportActionBar(mViewHolder!!.mToolbar)


        val viewPager = binding.viewPager2
        val tabLayout = binding.tabLayout

        val adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)

        //this line disable swiping
        viewPager.isUserInputEnabled = false
        viewPager.adapter = adapter

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        //duo nav
        val drawerToggle =  DuoDrawerToggle(
            this,binding.drawer,
            mViewHolder!!.mToolbar,
            R.string.nav_app_bar_open_drawer_description,
            R.string.navigation_drawer_close
        )

        mViewHolder!!.mDrawerLayout.setDrawerListener(drawerToggle)
        drawerToggle.syncState()

        TabLayoutMediator(tabLayout, viewPager){
            tab, position -> tab.text = fragmentsArray[position]
        }.attach()


    }


    private inner class ViewHolder internal constructor(){
        val mDrawerLayout: DuoDrawerLayout
        val mDuoMenuView: DuoMenuView
        val mToolbar: Toolbar

        init {
            mDrawerLayout = findViewById<View>(R.id.drawer) as DuoDrawerLayout
            mDuoMenuView = mDrawerLayout.menuView as DuoMenuView
            mToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        }
    }


}



