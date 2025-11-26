package com.example.proyecto1.ui.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto1.data.Report
import com.example.proyecto1.data.ReportsRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ReportsViewModel : ViewModel() {

    private val _reports = mutableStateListOf<Report>()
    val reports: List<Report> get() = _reports

    init {
        viewModelScope.launch {
            ReportsRepository.reportsFlow().collectLatest { list ->
                _reports.clear()
                _reports.addAll(list)
            }
        }
    }
}
