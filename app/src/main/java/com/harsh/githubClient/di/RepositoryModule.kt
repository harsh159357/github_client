package com.harsh.githubClient.di

import com.harsh.githubClient.data.repository.GithubClientRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        GithubClientRepository(get())
    }
}