package com.harsh.githubClient

import android.app.Application
import com.harsh.githubClient.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class GitHubClientApplication : Application(){

    companion object {
        lateinit var instance: GitHubClientApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidContext(this@GitHubClientApplication)
            modules(appModules)
        }
    }
}