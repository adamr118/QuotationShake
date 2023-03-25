package dadm.arahmou.quotationshake.data.favourites

import androidx.room.*
import dadm.arahmou.quotationshake.data.favourites.FavouritesContract.FavouritesEntry.COLUMN_ID
import dadm.arahmou.quotationshake.data.favourites.FavouritesContract.FavouritesEntry.TABLE_NAME
import dadm.arahmou.quotationshake.data.favourites.model.QuotationDto
import dadm.arahmou.quotationshake.domain.model.Quotation
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotation(vararg quotationDto: QuotationDto)

    @Delete
    suspend fun deleteQuotation(vararg quotationDto: QuotationDto)

    @Query("SELECT * FROM ${TABLE_NAME}")
     fun getAllFavourites(): Flow<List<QuotationDto>>

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = :id")
     fun getFavouriteFromId(id:String):Flow<QuotationDto?>

    @Query("DELETE FROM $TABLE_NAME")
     suspend fun deleteAllQuotations()






}


