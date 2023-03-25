package dadm.arahmou.quotationshake.data.favourites

import dadm.arahmou.quotationshake.domain.model.Quotation
import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {
    suspend fun insertQuotation(quotation: Quotation)
    suspend fun deleteQuotation(quotation: Quotation)
    fun getAllFavourites(): Flow<List<Quotation>>
    fun getFavouriteFromId(id:String): Flow<Quotation?>
    suspend fun deleteAllQuotations()
}