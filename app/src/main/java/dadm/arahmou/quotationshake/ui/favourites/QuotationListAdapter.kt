package dadm.arahmou.quotationshake.ui.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dadm.arahmou.quotationshake.databinding.QuotationItemBinding
import dadm.arahmou.quotationshake.domain.model.Quotation

class QuotationListAdapter(private val item_clicked : ItemClicked) :
    ListAdapter<Quotation, QuotationListAdapter.ViewHolder>(QuotationDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(QuotationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), item_clicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object QuotationDiff : DiffUtil.ItemCallback<Quotation>() {
        override fun areContentsTheSame(oldItem: Quotation, newItem: Quotation): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Quotation, newItem: Quotation): Boolean {
            return oldItem.id == newItem.id
        }

    }

    inner class ViewHolder(private val binding: QuotationItemBinding, private val item_clicked: ItemClicked): RecyclerView.ViewHolder(binding.root) {
        init { binding.root.setOnClickListener {
            item_clicked.onClick(binding.authorNameTv.text.toString()) }
        }

        fun bind(item: Quotation){
            binding.authorNameTv.text = item.author
            binding.quoteTv.text = item.text
        }

    }
    interface ItemClicked { fun onClick(author: String) }

}

