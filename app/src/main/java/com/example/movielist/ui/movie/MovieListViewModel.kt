package com.example.movielist.ui.movie

import android.app.AlertDialog
import android.app.Application
import android.content.ComponentCallbacks
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.work.*
import androidx.work.PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS
import com.example.movielist.data.local.sharedPreference.getTitle
import com.example.movielist.data.model.Content
import com.example.movielist.data.repository.MovieRepository
import com.example.movielist.util.SampleFoodItems
import com.example.movielist.util.workmanager.SampleWorker
import com.example.movielist.util.workmanager.SampleWorker2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

private const val TAG = "Lifecycle Fragment"
private const val TAG_OUTPUT = "Worker"

class MovieListViewModel(
        val context: Application,
        private val movieRepository: MovieRepository,
        private val preferences: SharedPreferences,
) : AndroidViewModel(context) {

    private var _updateTile :  MutableLiveData<String> = MutableLiveData()
    var updateTile : LiveData<String> = _updateTile

    private var _moviesList :  MutableLiveData<List<Content>> = MutableLiveData()
    var moviesList : LiveData<List<Content>> = _moviesList
    fun getMoviesList() = movieRepository.getResult()


    val sampleWorkerRequest = PeriodicWorkRequestBuilder<SampleWorker>(2, TimeUnit.MINUTES).addTag(TAG_OUTPUT)
            .setConstraints(
                    Constraints.Builder().setRequiresCharging(true).build()
            )
            .build()

    val sampleWorkRequest2 =  OneTimeWorkRequestBuilder<SampleWorker2>().build()
    val workManager = WorkManager.getInstance(context)
    var pageNumber = 0;
    fun setTitle (){
        _updateTile.value = (preferences.getTitle())
    }
    internal val outputWorkInfos: LiveData<List<WorkInfo>>

    init {
        // This transformation makes sure that whenever the current work Id changes the WorkInfo
        // the UI is listening to changes
        outputWorkInfos = workManager.getWorkInfosByTagLiveData(TAG_OUTPUT)
    }

    fun getMoviesFromDB(){

        viewModelScope.launch(Dispatchers.IO) {
            _moviesList.postValue(movieRepository.getMoviesFromDB())
        }
    }

    companion object {

        const val PAGE_SIZE = 20
    }


    fun isLastPage(){
    }

    fun clearData(){

        pageNumber = 0;
    }

    fun loadMore(){
            val  item = SampleFoodItems.Builder()
                    .setName("Chicken Salad")
                    .setType("Salad")
                    .create()

    }

    class Person(){

        var name = "";
        var age = ""
    }

   fun test(person : Person){

       val result = person.apply {

           age = "28"
           name = "rohit"
       }

       val age = with(result){
           name
           age
       }

       val p :  Pair<String, Int> =  Pair("rohit", 10)
       p.first
       p.second

       callback(2)

   }


    val  callback : (Int) -> Int = { a : Int -> a + a}

    fun enqueWork(){

        workManager.enqueue(
                sampleWorkerRequest
        )
    }


}