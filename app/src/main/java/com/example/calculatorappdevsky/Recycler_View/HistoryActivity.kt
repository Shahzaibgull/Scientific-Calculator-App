package com.example.calculatorappdevsky.Recycler_View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calculatorappdevsky.ScientificMainActivity
import com.example.calculatorappdevsky.databinding.ActivityHistoryBinding



class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //loadData()

        binding.btnBack.setOnClickListener {
            finish()
        }
        val adapter= Adapter(this, ScientificMainActivity.CalculationHistory.historyList)   //Integrate Adapter or Recycler View  with layout manager
        binding.recyclerViewID.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL, true)
        binding.recyclerViewID.adapter=adapter

        /*val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(MainActivity.CalculationHistory)
        editor.putString("history", json)
        editor.apply()*/
        //Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show()
    }

   /* private fun loadData() {
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("history", null)
        val type: Type = object : TypeToken<ArrayList<DataModel?>?>() {}.type
        //MainActivity.CalculationHistory.historyList =  gson.fromJson<DataModel>(json, type) as ArrayList<DataModel>
        MainActivity.CalculationHistory.historyList =  gson.fromJson<DataModel>(json, type) as ArrayList<DataModel>
    }*/
}