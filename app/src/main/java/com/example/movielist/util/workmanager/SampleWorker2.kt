package com.example.movielist.util.workmanager

import android.app.Application
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class SampleWorker2 (appContext : Context, workerParameters: WorkerParameters) :  CoroutineWorker(appContext, workerParameters){
    override suspend fun doWork(): Result {
        println("worker2 started")

        delay(8000L)
        println("worker2 completed")

        return Result.success()

    }
}