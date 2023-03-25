package dadm.arahmou.quotationshake.data.favourites.model

import dadm.arahmou.quotationshake.domain.model.Quotation

fun QuotationDto.toDomain(): Quotation =
    Quotation(  id = id,
                author = author,
                text = text)

fun Quotation.toDto(): QuotationDto =
    QuotationDto(   id = id,
                    author = author,
                    text = text)