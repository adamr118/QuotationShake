package dadm.arahmou.quotationshake.data.favourites

import dadm.arahmou.quotationshake.data.favourites.model.QuotationDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavouritesDataSourceImpl @Inject constructor(private val favouritesDao: FavouritesDao):FavouritesDataSource {
    override suspend fun insertQuotation(quotationDto: QuotationDto) {
         favouritesDao.insertQuotation(quotationDto)
    }

    override suspend fun deleteQuotation(quotationDto: QuotationDto) {
         favouritesDao.deleteQuotation(quotationDto)
    }

    override  fun getAllFavourites(): Flow<List<QuotationDto>> {
        return favouritesDao.getAllFavourites()
    }

    override  fun getFavouriteFromId(id: String): Flow<QuotationDto?> {
        return favouritesDao.getFavouriteFromId(id)
    }

    suspend override  fun deleteAllQuotations() {
        favouritesDao.deleteAllQuotations()
    }
}

