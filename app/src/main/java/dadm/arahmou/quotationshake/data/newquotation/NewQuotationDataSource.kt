package dadm.arahmou.quotationshake.data.newquotation

import dadm.arahmou.quotationshake.data.newquotation.model.QuotationDto
import dadm.arahmou.quotationshake.domain.model.Quotation
import retrofit2.Response

interface NewQuotationDataSource {
    suspend fun getQuotation( language: String):
            Response<QuotationDto>
}