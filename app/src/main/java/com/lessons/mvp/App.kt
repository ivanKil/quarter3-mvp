package com.lessons.mvp

import com.github.terrakok.cicerone.Cicerone
import com.lessons.mvp.data.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {
    //    companion object {
//        lateinit var instance: App
//    }
//
//    //Временно до даггера положим это тут
//    private val cicerone: Cicerone<Router> by lazy {
//        Cicerone.create()
//    }
//    val navigatorHolder get() = cicerone.getNavigatorHolder()
//    val router get() = cicerone.router
//
//    override fun onCreate() {
//        super.onCreate()
//        instance = this
//    }
    override fun applicationInjector(): AndroidInjector<App> =
        DaggerApplicationComponent
            .builder()
            .withContext(applicationContext)
            .apply {
                val cicerone = Cicerone.create()
                withNavigatorHolder(cicerone.getNavigatorHolder())
                withRouter(cicerone.router)
            }
            .build()
}