package com.gnacoding.eplapp.presentation.screen.club_detail

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
class ClubDetailViewModel @Inject constructor(
    private val repository: ClubRepository
) : ViewModel() {

    private val _club = MutableStateFlow<UiState<Club>>(UiState.Loading)
    val club = _club.asStateFlow()

    fun getClub(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getClub(id)
                .catch { _club.value = UiState.Error(errorMessage = it.message.toString()) }
                .collect { _club.value = UiState.Success(data = it) }
        }
    }

    fun updateFavoriteClub(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteClub(id, isFavorite)
        }
    }
}