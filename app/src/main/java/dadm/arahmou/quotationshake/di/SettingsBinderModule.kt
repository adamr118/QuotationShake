package dadm.arahmou.quotationshake.di

import dadm.arahmou.quotationshake.data.settings.SettingsDataSource
import dadm.arahmou.quotationshake.data.settings.SettingsDataSourceImpl
import dadm.arahmou.quotationshake.data.settings.SettingsRepository
import dadm.arahmou.quotationshake.data.settings.SettingsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class SettingsBinderModule() {
    @Binds
    abstract fun bindSettingsDataSource(settingsDataSourceImpl: SettingsDataSourceImpl): SettingsDataSource

    @Binds
    abstract fun bindSettingsRepository(settingsRepositoryImpl: SettingsRepositoryImpl): SettingsRepository
}