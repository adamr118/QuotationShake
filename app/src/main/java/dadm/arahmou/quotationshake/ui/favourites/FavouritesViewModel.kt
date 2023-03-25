package dadm.arahmou.quotationshake.ui.favourites

import androidx.lifecycle.*
import dadm.arahmou.quotationshake.data.favourites.FavouritesRepository
import dadm.arahmou.quotationshake.domain.model.Quotation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(private val favouritesRepository: FavouritesRepository) : ViewModel() {
    val lista_citas = favouritesRepository.getAllFavourites().asLiveData()

    val isDeleteAllVisible = lista_citas.map{ it.isNotEmpty() }

    fun deleteAllQuotations(){
        viewModelScope.launch{favouritesRepository.deleteAllQuotations()}
    }

    fun deleteQuotationAtPosition(position: Int) {
        viewModelScope.launch{
            lista_citas.value?.let { favouritesRepository.deleteQuotation(it[position]) }
        }
    }

}

