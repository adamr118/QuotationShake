package dadm.arahmou.quotationshake.data.newquotation

import dadm.arahmou.quotationshake.domain.model.Quotation

interface NewQuotationRepository {
    suspend fun getNewQuotation(language:String): Result<Quotation>
}