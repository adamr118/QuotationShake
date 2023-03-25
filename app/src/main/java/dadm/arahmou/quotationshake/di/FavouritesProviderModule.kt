package dadm.arahmou.quotationshake.di

import android.content.Context
import androidx.room.Room
import dadm.arahmou.quotationshake.data.favourites.FavouritesContract
import dadm.arahmou.quotationshake.data.favourites.FavouritesDao
import dadm.arahmou.quotationshake.data.favourites.FavouritesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FavouritesProviderModule {

    @Provides
    @Singleton
    fun provideFavouritesDatabase(@ApplicationContext context: Context): FavouritesDatabase{
        return Room.databaseBuilder(context, FavouritesDatabase::class.java, FavouritesContract.DATABASE_FAVOURITES).build()
    }

    @Provides
    fun provideFavouritesDao(favouritesDatabase: FavouritesDatabase): FavouritesDao{
        return favouritesDatabase.favouritesDao()
    }

}