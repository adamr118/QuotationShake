package dadm.arahmou.quotationshake.data.newquotation

import dadm.arahmou.quotationshake.data.newquotation.model.NewQuotationRetrofit
import dadm.arahmou.quotationshake.data.newquotation.model.QuotationDto
import dadm.arahmou.quotationshake.domain.model.Quotation
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class NewQuotationDataSourceImpl @Inject constructor(private val retrofit: Retrofit): NewQuotationDataSource {
    private val retrofitQuotationService = retrofit.create(NewQuotationRetrofit::class.java)
    override suspend fun getQuotation(language: String): Response<QuotationDto> {
        return try { retrofitQuotationService.getQuotation(language)
        } catch (e: Exception) { Response.error(
            400, // Could be any other code and text, because we are not using it
            ResponseBody.create(MediaType.parse("text/plain"), e.toString()) )
        }
    }

}