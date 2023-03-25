package dadm.arahmou.quotationshake.data.favourites

import androidx.room.Database
import androidx.room.RoomDatabase
import dadm.arahmou.quotationshake.data.favourites.model.QuotationDto

@Database(entities = [QuotationDto::class], version = 1)
abstract class FavouritesDatabase: RoomDatabase() {
    abstract fun favouritesDao(): FavouritesDao
}

