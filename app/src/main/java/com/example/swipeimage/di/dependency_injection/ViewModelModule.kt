/*package com.example.swipeimage.di.dependency_injection

import android.app.Application
import com.example.swipeimage.Data.datasource.ViewModel.RegistrationViewModel
import com.example.swipeimage.inscription.data.datasource.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideRegistrationViewModel(
        application: Application,
        repository: Repository
    ): RegistrationViewModel {
        return RegistrationViewModel(application, repository)
    }

    // Vous pouvez ajouter d'autres ViewModels ici en utilisant la même approche
}

*/
