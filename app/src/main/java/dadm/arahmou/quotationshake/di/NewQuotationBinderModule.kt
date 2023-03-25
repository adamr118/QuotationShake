package dadm.arahmou.quotationshake.di

import dadm.arahmou.quotationshake.data.newquotation.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

abstract class NewQuotationBinderModule {
    @Binds
    abstract fun bindNewQuotationRepository(newQuotationRepositoryImpl: NewQuotationRepositoryImpl): NewQuotationRepository

    @Binds
    abstract fun bindNewQuotationDataSource(newQuotationDataSourceImpl: NewQuotationDataSourceImpl): NewQuotationDataSource
    @Binds
    abstract fun provideNewQuotationManager(newQuotationManagerImpl: NewQuotationManagerImpl): NewQuotationManager
}