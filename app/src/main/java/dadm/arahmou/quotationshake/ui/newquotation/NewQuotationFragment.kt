package dadm.arahmou.quotationshake.ui.newquotation
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.google.android.material.snackbar.Snackbar
import dadm.arahmou.quotationshake.R
import dadm.arahmou.quotationshake.databinding.FragmentNewQuotationBinding
import dadm.arahmou.quotationshake.utils.NoInternetException
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import java.net.HttpRetryException

@AndroidEntryPoint
class NewQuotationFragment: Fragment(R.layout.fragment_new_quotation), MenuProvider {
    private var _binding: FragmentNewQuotationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewQuotationViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentNewQuotationBinding.bind(view)
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        viewModel.username.observe(viewLifecycleOwner) {
            username ->  binding.tvgreetings.text = getString(R.string.welcome_message, username)
        }
        viewModel.is_loading.observe(viewLifecycleOwner){
            is_loading ->  binding.swipetorefresh.isRefreshing = is_loading
        }
        viewModel.isGreetingsVisible.observe(viewLifecycleOwner){
            isVisible -> binding.tvgreetings.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
        }
        viewModel.quotation.observe(viewLifecycleOwner){
            quotation -> if (quotation!=null) {
                binding.quoteTv.text = quotation.text
            binding.authorNameTv.text = if (quotation.author.isEmpty()) "Anonymous" else quotation.author
            }
        }

        viewModel.isAddToFavouritesVisible.observe(viewLifecycleOwner){
            isButtonVisible -> binding.buttonFavourite.visibility = if (isButtonVisible) View.VISIBLE else View.INVISIBLE
        }

        viewModel.error_to_show.observe(viewLifecycleOwner) { error ->
            error?.let {
                    val errorMessage = when (error) {
                        is NoInternetException -> getString(R.string.network_error_message)
                        is IOException -> getString(R.string.server_error_message)
                        else -> getString(R.string.unknown_error_message)
                    }
                Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
                viewModel.resetError()
            }
        }

        binding.buttonFavourite.setOnClickListener{viewModel.addToFavourites()}
        binding.swipetorefresh.setOnRefreshListener { viewModel.getNewQuotation() }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_new_quotation, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.refresh_quotation-> {
                viewModel.getNewQuotation()
                true
            }
            else -> false

        }
    }








}