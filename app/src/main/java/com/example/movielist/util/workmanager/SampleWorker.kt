package com.example.movielist.util.workmanager

import android.app.Application
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class SampleWorker (appContext : Context, workerParameters: WorkerParameters) :  CoroutineWorker(appContext, workerParameters){
    override suspend fun doWork(): Result {
        println("worker started")

        delay(1000L)
        println("worker completed")

        return Result.success()

    }

}