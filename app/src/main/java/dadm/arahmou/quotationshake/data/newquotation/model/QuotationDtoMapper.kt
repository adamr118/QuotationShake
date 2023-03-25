package dadm.arahmou.quotationshake.data.newquotation.model

import dadm.arahmou.quotationshake.domain.model.Quotation
import retrofit2.Response
import java.io.IOException

fun QuotationDto.toDomain() = Quotation(id = quoteLink, text = quoteText, author = quoteAuthor)

fun Response<QuotationDto>.toDomain() =
    if (isSuccessful) Result.success((body() as QuotationDto).toDomain()) else Result.failure(
        IOException()
    )