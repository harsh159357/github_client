package com.harsh.githubClient.di

import com.harsh.githubClient.viewmodel.ReposViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ReposViewModel(get()) }
}
