package com.colemichaels.giantbomb.ui.shared

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.colemichaels.giantbomb.LOG_TAG
import com.colemichaels.giantbomb.data.models.Game
import com.colemichaels.giantbomb.data.repositories.GiantBombRepository

class SharedViewModel(app: Application) : AndroidViewModel(app) {

    private val dataRepo = GiantBombRepository(app)

    val giantBombData = dataRepo.giantBombData

    val selectedGame = MutableLiveData<Game>()

    fun refreshData(query: String) {
        dataRepo.refreshData(query)
    }
}