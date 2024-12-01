package com.capstone.beanalyze.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HistoryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Fitur nya belum jadi, developernya lagi main pesbuk rek"
    }
    val text: LiveData<String> = _text
}