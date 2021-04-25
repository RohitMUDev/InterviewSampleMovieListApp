package com.example.movielist.util

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.movielist.R
import kotlinx.coroutines.NonCancellable.cancel
import java.lang.Exception

class SampleDialog() : DialogFragment() {


    private lateinit var  onClickListner: OnClickListner
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movie_list_fragment, container)
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    interface  OnClickListner{

        fun onPostiveClicked()
        fun onNegativeClicked()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try{

            onClickListner = context as OnClickListner
        }catch (exception : Exception){

        }

    }
}