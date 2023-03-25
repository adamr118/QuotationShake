package dadm.arahmou.quotationshake.ui.favourites

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels

class DeleteAllDialogFragment: DialogFragment() {
    private val favouritesViewModel: FavouritesViewModel by activityViewModels()


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete all favourite quotations")
        builder.setMessage("Do you really want to delete all the favourite quotations ?")
        builder.setNegativeButton("Cancel"){
            _,_-> dismiss()
        }
        builder.setPositiveButton("Delete"){
            _,_ -> favouritesViewModel.deleteAllQuotations()
        }
        return builder.create()
    }

}