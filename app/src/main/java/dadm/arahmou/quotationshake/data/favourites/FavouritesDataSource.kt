package dadm.arahmou.quotationshake.data.favourites

import dadm.arahmou.quotationshake.data.favourites.model.QuotationDto
import kotlinx.coroutines.flow.Flow

interface FavouritesDataSource {
    suspend fun insertQuotation(quotationDto: QuotationDto)
    suspend fun deleteQuotation(quotationDto: QuotationDto)
     fun getAllFavourites(): Flow<List<QuotationDto>>
     fun getFavouriteFromId(id:String):Flow<QuotationDto?>
     suspend fun deleteAllQuotations()


}

