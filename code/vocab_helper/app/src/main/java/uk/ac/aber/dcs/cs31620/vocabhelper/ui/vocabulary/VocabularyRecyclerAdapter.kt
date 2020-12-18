package uk.ac.aber.dcs.cs31620.vocabhelper.ui.vocabulary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uk.ac.aber.dcs.cs31620.vocabhelper.databinding.VocabularyItemBinding
import uk.ac.aber.dcs.cs31620.vocabhelper.model.VocabularyItem
import uk.ac.aber.dcs.cs31620.vocabhelper.model.WordType
import java.util.*

class VocabularyRecyclerAdapter(
    private val context: Context?,
) :
    RecyclerView.Adapter<VocabularyRecyclerAdapter.ViewHolder>() {

    private var dataSet: MutableList<VocabularyItem> = mutableListOf()
    var clickListener: View.OnClickListener? = null

    inner class ViewHolder(
        itemView: View,
        private val foreignWord: TextView,
        private val translation: TextView,
        private val wordType: TextView
    ) :
        RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener(clickListener)
        }

        fun bindDataSet(vocabItem: VocabularyItem) {
            val wordTypeValue = if (vocabItem.wordType != WordType.NONE) {
                vocabItem.wordType.toString().toLowerCase(Locale.ROOT).capitalize(Locale.ROOT)
            } else {
                ""
            }

            foreignWord.text = vocabItem.foreignWord
            translation.text = vocabItem.translation
            wordType.text = wordTypeValue
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VocabularyRecyclerAdapter.ViewHolder {
        val vocabItemBinding =
            VocabularyItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(
            vocabItemBinding.vocabularyCard,
            vocabItemBinding.foreignWord,
            vocabItemBinding.translation,
            vocabItemBinding.wordType
        )
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: VocabularyRecyclerAdapter.ViewHolder, position: Int) {
        holder.bindDataSet(dataSet[position])
    }

    fun changeDataset(dataSet: MutableList<VocabularyItem>) {
        this.dataSet = dataSet
        this.notifyDataSetChanged()
    }
}