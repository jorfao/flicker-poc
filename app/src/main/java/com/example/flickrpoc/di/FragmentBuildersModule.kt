package com.example.flickrpoc.di

import com.example.flickrpoc.ui.BaseFragment
import com.example.flickrpoc.ui.details.DetailsFragment
import com.example.flickrpoc.ui.list.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeListFragment(): ListFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailsFragment(): DetailsFragment
}
