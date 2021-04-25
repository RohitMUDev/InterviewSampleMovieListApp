package com.example.movielist.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.movielist.databinding.ActivityMainBinding
import com.example.movielist.util.HashTest
import com.example.movielist.util.SampleDialog
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

private const val TAG = "Corountine"
private const val TAG1 = "Lifecycle Activi"
class HomeActivity : AppCompatActivity(), SampleDialog.OnClickListner {

    private var _homeActivityMainBinding: ActivityMainBinding? = null
    private val binding get() = _homeActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _homeActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        Log.d(TAG1, "onCreate")

       SampleDialog().show(supportFragmentManager,"sample")
        val t1 =  HashTest(
                name = "rohit",
                number = 1
        )

        val t2 =  HashTest(

                name = "rohit",
                number = 1
        )

        val list =  ArrayList<HashTest>()
        list.add(t1)
        list.add(t2)

        var e = false
        if (list[0] === (list[1])) {
            e = true
        }

        Log.e("hashtest size== ", "" + list.size)
        Log.e("hashtest equals== ", "" + e)

        val handler = CoroutineExceptionHandler { _, exception ->
            Log.d(TAG, "$exception handled !")
        }
        val scope = CoroutineScope(Job() + Dispatchers.IO + handler)

        scope.launch {

            val time = measureTimeMillis {
                var answer1 = "default"
                var answer2 = "default"
               val job1 =  async { network1()}
               val job2  =  async { network2()}
                answer1 =  job1.await()
                answer2 =  job2.await()
                Log.d(TAG ,answer1)
                Log.d(TAG ,answer2)
            }
            Log.d(TAG, "request to $time")

        }

        Log.d(TAG, "outside run blocking")


    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG1, "onStop")

    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG1, "onRestart")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG1, "onResume")

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG1, "onStart")

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG1, "onPause")

    }



    suspend fun network1() :  String{

        delay (3000L)
        return "network1"
    }



    suspend fun network2() :  String{

        delay (3000L)
        return "network2"
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG1, "onDestroy")
        _homeActivityMainBinding = null
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")

    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onSaveInstanceState")

    }

    override fun onPostiveClicked() {
    }

    override fun onNegativeClicked() {
    }
}