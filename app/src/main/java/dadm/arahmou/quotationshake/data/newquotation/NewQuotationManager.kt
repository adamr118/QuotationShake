package dadm.arahmou.quotationshake.data.newquotation

import dadm.arahmou.quotationshake.domain.model.Quotation

interface NewQuotationManager {
    suspend fun getNewQuotation(): Result<Quotation>
}