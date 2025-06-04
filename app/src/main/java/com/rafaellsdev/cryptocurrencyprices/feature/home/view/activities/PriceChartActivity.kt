package com.rafaellsdev.cryptocurrencyprices.feature.home.view.activities

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.rafaellsdev.cryptocurrencyprices.R
import com.rafaellsdev.cryptocurrencyprices.databinding.PriceChartActivityBinding
import com.rafaellsdev.cryptocurrencyprices.feature.home.viewmodel.PriceChartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PriceChartActivity : AppCompatActivity() {

    private val binding by lazy { PriceChartActivityBinding.inflate(layoutInflater) }
    private val viewModel: PriceChartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getStringExtra(EXTRA_ID) ?: return finish()
        title = intent.getStringExtra(EXTRA_NAME) ?: ""

        setupSpinner(id)
        observeChartData()
        viewModel.loadChart(id, 1)
    }

    private fun setupSpinner(id: String) {
        ArrayAdapter.createFromResource(
            this,
            R.array.chart_periods,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerPeriod.adapter = adapter
        }
        binding.spinnerPeriod.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: android.view.View?,
                position: Int,
                idView: Long
            ) {
                val days = when (position) {
                    1 -> 7
                    2 -> 30
                    else -> 1
                }
                viewModel.loadChart(id, days)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun observeChartData() {
        viewModel.chartData.observe(this) { list ->
            val entries = list.map { Entry(it.first.toFloat(), it.second.toFloat()) }
            val dataSet = LineDataSet(entries, title.toString())
            val lineData = LineData(dataSet)
            binding.lineChart.data = lineData
            binding.lineChart.invalidate()
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NAME = "extra_name"
    }
}
