package dadm.arahmou.quotationshake.data.newquotation

import dadm.arahmou.quotationshake.data.newquotation.model.toDomain
import dadm.arahmou.quotationshake.domain.model.Quotation
import dadm.arahmou.quotationshake.utils.NoInternetException
import javax.inject.Inject

class NewQuotationRepositoryImpl @Inject constructor(
    private val newQuotationDataSource: NewQuotationDataSource,
    private val connectivityChecker: ConnectivityChecker
) : NewQuotationRepository {
    override suspend fun getNewQuotation(language:String): Result<Quotation> {
        return if (connectivityChecker.isConnectionAvailable()) {
            newQuotationDataSource.getQuotation(language).toDomain()
        } else {
            Result.failure(NoInternetException())
        }
    }
}



