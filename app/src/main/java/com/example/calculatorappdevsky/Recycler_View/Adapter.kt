package com.example.calculatorappdevsky.Recycler_View

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.calculatorappdevsky.databinding.HistoryItemLayoutBinding


class Adapter(private val context: HistoryActivity, private val historyList: ArrayList<DataModel>) : RecyclerView.Adapter<Adapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.MyViewHolder {
        var binding= HistoryItemLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
        /*val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item_layout, parent, false)
        return MyViewHolder(view)*/
    }
    @SuppressLint("CommitPrefEdits")
    override fun onBindViewHolder(holder: Adapter.MyViewHolder, position: Int) {   //Bind data to the views

        val historyItem=historyList[position]
        holder.binding.textViewInput.text=historyItem.input
        holder.binding.textViewOutput.text= historyItem.output.toString()

        /*val historyItem=historyList[position]
        holder.textView1.text=historyItem.input
        holder.textView2.text= historyItem.output.toString()*/

    }
    override fun getItemCount(): Int {
         return historyList.size
    }
    inner class MyViewHolder(var binding: HistoryItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    /*class MyViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val textView1: TextView = itemView.findViewById(R.id.textViewInput)
        val textView2: TextView = itemView.findViewById(R.id.textViewOutput)

        val sharedPreferences1 = context.getSharedPreferences("history", Context.MODE_PRIVATE)
        val editor = sharedPreferences1.edit()
        editor.putString("key1", holder.binding.textViewInput.text as String)
        editor.putString("key2", holder.binding.textViewOutput.text as String)
        editor.apply()
    }*/
}


