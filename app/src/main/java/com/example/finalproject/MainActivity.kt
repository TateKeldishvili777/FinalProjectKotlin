package com.example.finalproject

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
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
}