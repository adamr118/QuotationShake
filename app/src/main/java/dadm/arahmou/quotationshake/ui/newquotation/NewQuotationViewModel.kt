package dadm.arahmou.quotationshake.ui.newquotation

import androidx.lifecycle.*
import dadm.arahmou.quotationshake.data.favourites.FavouritesRepository
import dadm.arahmou.quotationshake.data.newquotation.NewQuotationManager
import dadm.arahmou.quotationshake.data.newquotation.NewQuotationRepository
import dadm.arahmou.quotationshake.data.settings.SettingsRepository
import dadm.arahmou.quotationshake.domain.model.Quotation
import dadm.arahmou.quotationshake.ui.favourites.FavouritesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel

class NewQuotationViewModel @Inject constructor(private val newQuotationManager: NewQuotationManager,
                                                private val settingsRepository: SettingsRepository,
                                                private val favouritesRepository: FavouritesRepository
): ViewModel() {
    private val _quotation = MutableLiveData<Quotation>()
    private val _is_loading = MutableLiveData<Boolean>()
    val isGreetingsVisible = quotation.map{ it == null }

    private val _error_to_show = MutableLiveData<Throwable?>()
    init {
        _is_loading.value = false
    }

    val isAddToFavouritesVisible: LiveData<Boolean>
    get() = quotation.switchMap() { newQuotation -> favouritesRepository.getFavouriteFromId(newQuotation.id).asLiveData()
    }.map() { favourite -> favourite == null
    }

    val username:LiveData<String>
    get() = settingsRepository.getUsername().asLiveData()

    val quotation:LiveData<Quotation>
    get() = _quotation

    val is_loading:LiveData<Boolean>
    get() = _is_loading


    val error_to_show:LiveData<Throwable?>
    get() = _error_to_show

    fun  resetError(){
        _error_to_show.value = null
    }

    fun getNewQuotation() {
        _is_loading.value = true
        viewModelScope.launch {
            newQuotationManager.getNewQuotation().fold(onSuccess = { quotation_result ->
                _quotation.value = quotation_result
                _is_loading.value = false

            }, onFailure = {error ->
                _error_to_show.value = error
                _is_loading.value = false

            })
        }
    }

    fun addToFavourites() {
        viewModelScope.launch{
            quotation.value?.let { favouritesRepository.insertQuotation(it) }
        }
    }

}


