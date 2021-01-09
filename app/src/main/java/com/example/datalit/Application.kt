package com.example.datalit

import android.app.Application
import com.example.datalit.repo.BooksRepo
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApplication : Application() {
    override fun onCreate() {
        println("AAA onCreate MainApp")
        super.onCreate()
        val appModule = module {
            single { BooksRepo() }
            viewModel { MainViewModel(get()) }
        }
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }

}