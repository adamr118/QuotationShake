package dadm.arahmou.quotationshake.ui.favourites
import android.content.ActivityNotFoundException
import android.content.Intent
import android.media.MediaRouter.SimpleCallback
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract.Instances.END
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dadm.arahmou.quotationshake.R
import dadm.arahmou.quotationshake.databinding.FragmentFavouritesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.callbackFlow

@AndroidEntryPoint
class FavouritesFragment: Fragment(R.layout.fragment_favourites), MenuProvider, QuotationListAdapter.ItemClicked {
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavouritesViewModel by activityViewModels()





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().addMenuProvider(this,
            viewLifecycleOwner, Lifecycle.State.RESUMED)
        _binding = FragmentFavouritesBinding.bind(view)
        val adapter = QuotationListAdapter(this)
        binding.recyclerView.adapter = adapter
        viewModel.lista_citas.observe(viewLifecycleOwner){
            lista -> adapter.submitList(lista)
        }
        viewModel.isDeleteAllVisible.observe(viewLifecycleOwner){
                isVisible -> if (!isVisible) requireActivity().invalidateMenu()
        }

        val item_touch = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.END){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun isLongPressDragEnabled(): Boolean {
                return false
            }

            override fun isItemViewSwipeEnabled(): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               viewModel.deleteQuotationAtPosition(viewHolder.adapterPosition)
            }

        })

        item_touch.attachToRecyclerView(binding.recyclerView)



    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_favourites, menu)

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.delete_all_quotations -> {
                DeleteAllDialogFragment().show(childFragmentManager, null)
                true
            } else -> false
        }
    }

    override fun onPrepareMenu(menu: Menu) {
        val menuItem = menu.findItem(R.id.delete_all_quotations)
        menuItem.isVisible = viewModel.isDeleteAllVisible.value ?: false

    }

    override fun onClick(author: String) {
        if (author.equals("Anonymous")){
            Snackbar.make(binding.root, "Not possible to access to Wikipedia because the author is anonymous", Snackbar.LENGTH_SHORT).show()
            return
        }else{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Special:Search?search=$author"))
            try {
                startActivity(intent)
            }catch (e:ActivityNotFoundException){
                Snackbar.make(binding.root, "Not possible to connect to Wikipedia", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}