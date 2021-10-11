package com.lessons.mvp.data.di.modules

import com.lessons.mvp.MainActivity
import com.lessons.mvp.data.network.AndroidNetworkStatus
import com.lessons.mvp.data.network.INetworkStatus
import com.lessons.mvp.data.user.GitHubUserRepository
import com.lessons.mvp.data.user.GitHubUserRepositoryImpl
import com.lessons.mvp.data.user.datasource.CacheUserDataSource
import com.lessons.mvp.data.user.datasource.CacheUserDataSourceImpl
import com.lessons.mvp.data.user.datasource.CloudUserDataSource
import com.lessons.mvp.data.user.datasource.UserDataSource
import com.lessons.mvp.presentation.userlist.UsersFragment
import com.lessons.mvp.presentation.userrepos.ReposFragment
import com.lessons.mvp.presentation.userrepos.repoinfo.RepoInfoFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module(
    includes = [DatabaseModule::class]
)

interface GitHubUsersModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindUsersFragment(): UsersFragment

    @ContributesAndroidInjector
    fun bindRepoInfoFragment(): RepoInfoFragment

    @ContributesAndroidInjector
    fun bindReposFragment(): ReposFragment

    @Singleton
    @Binds
    fun bindGitHubUserRepository(repository: GitHubUserRepositoryImpl): GitHubUserRepository

    @Singleton
    @Binds
    fun bindUserDataSource(dataSource: CloudUserDataSource): UserDataSource

    @Singleton
    @Binds
    fun bindCacheUserDataSource(dataSource: CacheUserDataSourceImpl): CacheUserDataSource

    @Singleton
    @Binds
    fun bindAndroidNetworkStatus(androidNetworkStatus: AndroidNetworkStatus): INetworkStatus

    //@Binds
    //fun bindCacheUserDataSource(db: Database): CacheUserDataSource

//    @Binds
//    fun bindGitHubUserRepository(
//        userDataSource: UserDataSource, iNetworkStatus: INetworkStatus,
//        cacheUserDataSource: CacheUserDataSource
//    ): GitHubUserRepository
}