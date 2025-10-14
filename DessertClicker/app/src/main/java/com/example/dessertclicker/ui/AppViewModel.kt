package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    private val desserts = Datasource.dessertList

    init {
        val firstDessert = desserts.first()
        _uiState.update { currentState ->
            currentState.copy(
                currentDessertPrice = firstDessert.price,
                currentDessertImageId = firstDessert.imageId
            )
        }
    }

    /**
     * Determine which dessert to show.
     */
    private fun determineDessertToShow(dessertsSold: Int): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }

    fun clickDessert() {
        _uiState.update { currentState ->
            val dessertsSold = currentState.dessertsSold + 1
            val currentDessert = determineDessertToShow(dessertsSold)
            currentState.copy(
                revenue = currentState.revenue + currentState.currentDessertPrice,
                dessertsSold = dessertsSold,
                currentDessertPrice = currentDessert.price,
                currentDessertImageId = currentDessert.imageId,
            )
        }
    }
}