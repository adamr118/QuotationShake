package dadm.arahmou.quotationshake.data.newquotation.model

import dadm.arahmou.quotationshake.domain.model.Quotation
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface NewQuotationRetrofit{
    @GET("api/1.0/?method=getQuote&format=json")
    suspend fun getQuotation(@Query("lang") language:String): Response<QuotationDto>
}