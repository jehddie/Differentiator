package com.jehddie.differentiator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//class TermsAdapter(private val bases: List<Int>, private val powers: List<Int>) :
class TermsAdapter(private val powers: List<Int>) :
    RecyclerView.Adapter<TermsAdapter.TermsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TermsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.terms_item, parent, false)

        return TermsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TermsViewHolder, position: Int) {
        //holder.baseTextView.text = bases[position].toString()
        holder.powerTextView.text = powers[position].toString()
    }

    override fun getItemCount() = powers.size

    // ViewHolder represents a single row in our list
    class TermsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //val baseTextView: TextView = itemView.findViewById(R.id.base_term_edit_text)
        val powerTextView: TextView = itemView.findViewById(R.id.power_text_view)
    }
}