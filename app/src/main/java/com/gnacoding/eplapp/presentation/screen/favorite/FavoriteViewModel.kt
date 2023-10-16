package com.gnacoding.eplapp.presentation.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gnacoding.eplapp.domain.model.Club
import com.gnacoding.eplapp.domain.repository.ClubRepository
import com.gnacoding.eplapp.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: ClubRepository
) : ViewModel() {

    private val _allFavoriteClubs = MutableStateFlow<UiState<List<Club>>>(UiState.Loading)
    val allFavoriteClubs = _allFavoriteClubs.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllFavoriteClubs()
                .catch {
                    _allFavoriteClubs.value = UiState.Error(errorMessage = it.message.toString())
                }
                .collect { _allFavoriteClubs.value = UiState.Success(data = it) }
        }
    }
}