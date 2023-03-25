package dadm.arahmou.quotationshake.data.favourites

import dadm.arahmou.quotationshake.data.favourites.model.QuotationDto
import dadm.arahmou.quotationshake.data.favourites.model.toDomain
import dadm.arahmou.quotationshake.data.favourites.model.toDto
import dadm.arahmou.quotationshake.domain.model.Quotation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouritesRepositoryImpl @Inject constructor(private val favouritesDataSource: FavouritesDataSource): FavouritesRepository {
    override suspend fun insertQuotation(quotation: Quotation) {
        favouritesDataSource.insertQuotation(quotation.toDto())
    }

    override suspend fun deleteQuotation(quotation: Quotation) {
        favouritesDataSource.deleteQuotation(quotation.toDto())
    }

    override fun getAllFavourites(): Flow<List<Quotation>> {
        return favouritesDataSource.getAllFavourites().map {it.map { quotationDto ->  quotationDto.toDomain()}
        }
    }

    override fun getFavouriteFromId(id: String): Flow<Quotation?> {
        return favouritesDataSource.getFavouriteFromId(id).map { it?.toDomain() }
    }

    suspend override fun deleteAllQuotations() {
        favouritesDataSource.deleteAllQuotations()
    }

}

