package com.example.finalproject

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.finalproject.models.Movie
import com.example.finalproject.models.MovieResponse
import com.example.finalproject.services.MovieApiInterface
import com.example.finalproject.services.MovieApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_movies_list.layoutManager = LinearLayoutManager(this)
        rv_movies_list.setHasFixedSize(true)
        getMovieData { movies : List<Movie> ->
            rv_movies_list.adapter = MovieAdapter(movies)
        }


        val secondViewPager = findViewById<ViewPager>(R.id.myViewpager)

        val myAdapter = SecondViewPagerAdapter(supportFragmentManager)

        myAdapter.addFragment(MovieTwo(), "Teachers")
        myAdapter.addFragment(MovieOne(), "Students")

        secondViewPager.adapter  = myAdapter

        val receiver: Receiver = Receiver()
        val intentFilter = IntentFilter()

        intentFilter.addAction("android.intent.action.SCREEN_ON")
        intentFilter.addAction("android.intent.action.SCREEN_OFF")
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED")
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED")
        registerReceiver(receiver, intentFilter)
    }

    private fun getMovieData(callback: (List<Movie>) -> Unit){
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

        })
    }

    class SecondViewPagerAdapter(fragment: FragmentManager) : FragmentPagerAdapter(fragment){
        private var fragmentList: MutableList<Fragment> = ArrayList()
        private var titleList : MutableList<String> = ArrayList()

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        fun addFragment(fragment: Fragment, title: String){
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }
    }
}