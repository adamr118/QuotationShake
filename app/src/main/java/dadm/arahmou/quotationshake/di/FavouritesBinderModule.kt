package dadm.arahmou.quotationshake.di

import dadm.arahmou.quotationshake.data.favourites.FavouritesDataSource
import dadm.arahmou.quotationshake.data.favourites.FavouritesDataSourceImpl
import dadm.arahmou.quotationshake.data.favourites.FavouritesRepository
import dadm.arahmou.quotationshake.data.favourites.FavouritesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class FavouritesBinderModule {

    @Binds
    abstract fun bindFavouritesDataSource(favouritesDataSourceImpl: FavouritesDataSourceImpl): FavouritesDataSource

    @Binds
    abstract fun bindFavouritesRepository(favouritesRepositoryImpl: FavouritesRepositoryImpl): FavouritesRepository

}