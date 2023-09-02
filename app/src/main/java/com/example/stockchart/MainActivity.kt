package com.example.stockchart

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.stockchart.databinding.ActivityMainBinding
import com.example.stockchart.ui.component.river_chart.displayRiverCharts
import com.example.stockchart.ui.component.river_chart.UiState
import com.example.stockchart.util.DateUtil
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 原本想用Compose，可惜MPchart沒有Compose版本
//        setContent {
//            MainNavigation()
//        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initListener()
    }

    private fun initView() {
        binding.chart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                if (e != null) {
                    viewModel.onSelect(e)
                }
            }

            override fun onNothingSelected() {
                // 這裡會在用戶取消選擇時調用
            }
        })
    }

    private fun initListener() {
        // 強制顯示圖表，遇過一次已經載入資訊卻沒顯示
        //todo viewModel.uiState.collect監聽失效沒有更新顯示
        binding.root.setOnClickListener {
            binding.progressCircular.visibility = View.GONE
            binding.chart.visibility = View.VISIBLE
            binding.textDollarUnit.visibility = View.VISIBLE
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                // 頁面初始化
                launch {
                    viewModel.uiState.collect {
                        when (it) {
                            is UiState.Error -> {
                                // 可以放錯誤資訊
                            }

                            UiState.Loading -> {
                                // 可以表示正在載入
                                binding.progressCircular.visibility = View.VISIBLE
                                binding.chart.visibility = View.INVISIBLE
                                binding.textDollarUnit.visibility = View.INVISIBLE
                            }

                            is UiState.Success -> {
                                displayRiverCharts(binding.chart, it) { entry ->
                                    viewModel.onSelect(entry)
                                }
                                binding.progressCircular.visibility = View.GONE
                                binding.chart.visibility = View.VISIBLE
                                binding.textDollarUnit.visibility = View.VISIBLE
                            }
                        }

                    }
                }

                // 選取後更新數據
                //todo 這邊可以考慮怎麼封裝
                launch {
                    viewModel.selectedPosition.collect { index ->
                        if (index != null) {
                            viewModel.uiState.value.apply {
                                if (this is UiState.Success) {
                                    binding.yearMonth.text =
                                        DateUtil.xValueToYearMonth(index, oldestDate)
                                    binding.stockPrice.setText(
                                        String.format(
                                            resources.getString(R.string.stock_price_string),
                                            getStockPrice(index)
                                        )
                                    )

                                    binding.pe1.setText(
                                        String.format(
                                            resources.getString(R.string.item_string),
                                            priceEarningsStandard[0].toFloat(),
                                            riverEntries[0].upperEntries[index].y
                                        )
                                    )
                                    binding.pe2.setText(
                                        String.format(
                                            resources.getString(R.string.item_string),
                                            priceEarningsStandard[1].toFloat(),
                                            riverEntries[1].upperEntries[index].y
                                        )
                                    )
                                    binding.pe3.setText(
                                        String.format(
                                            resources.getString(R.string.item_string),
                                            priceEarningsStandard[2].toFloat(),
                                            riverEntries[2].upperEntries[index].y
                                        )
                                    )
                                    binding.pe4.setText(
                                        String.format(
                                            resources.getString(R.string.item_string),
                                            priceEarningsStandard[3].toFloat(),
                                            riverEntries[3].upperEntries[index].y
                                        )
                                    )
                                    binding.pe5.setText(
                                        String.format(
                                            resources.getString(R.string.item_string),
                                            priceEarningsStandard[4].toFloat(),
                                            riverEntries[4].upperEntries[index].y
                                        )
                                    )
                                    binding.pe6.setText(
                                        String.format(
                                            resources.getString(R.string.item_string),
                                            priceEarningsStandard[5].toFloat(),
                                            riverEntries[5].upperEntries[index].y
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}