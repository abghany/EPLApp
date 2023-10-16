package com.gnacoding.eplapp.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gnacoding.eplapp.data.data_source.ClubDataSource
import com.gnacoding.eplapp.domain.model.Club
import com.gnacoding.eplapp.domain.repository.ClubRepository
import com.gnacoding.eplapp.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val repository: ClubRepository
) : ViewModel() {

    private val _allClubs = MutableStateFlow<UiState<List<Club>>>(UiState.Loading)
    val allClubs: StateFlow<UiState<List<Club>>>
        get() = _allClubs

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllClubs().collect { club ->
                when (club.isEmpty()) {
                    true -> repository.insertAllClub(ClubDataSource.dummyClubs)
                    else -> _allClubs.value = UiState.Success(club)
                }
            }
        }
    }
}