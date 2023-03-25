package dadm.arahmou.quotationshake.data.favourites.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dadm.arahmou.quotationshake.data.favourites.FavouritesContract.FavouritesEntry.COLUMN_AUTHOR
import dadm.arahmou.quotationshake.data.favourites.FavouritesContract.FavouritesEntry.COLUMN_TEXT
import dadm.arahmou.quotationshake.data.favourites.FavouritesContract.FavouritesEntry.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class QuotationDto(@PrimaryKey val id: String,
                        @ColumnInfo(name = COLUMN_AUTHOR) val author: String,
                        @ColumnInfo(name = COLUMN_TEXT) val text: String)
