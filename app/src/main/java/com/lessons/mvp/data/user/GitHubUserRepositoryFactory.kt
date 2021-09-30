package ru.gb.gb_popular_libs.data.user

import ru.gb.gb_popular_libs.data.user.datasource.UserDataSourceFactory

/**
 * Пока нет DI на основе Dagger2 мы решаем проблему
 * по старинке используя фабрики.
 */
object GitHubUserRepositoryFactory {

    fun create(): GitHubUserRepository =
        GitHubUserRepositoryImpl(
            UserDataSourceFactory.create()
        )

}