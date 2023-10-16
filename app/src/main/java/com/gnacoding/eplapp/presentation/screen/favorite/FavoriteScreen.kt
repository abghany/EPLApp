package com.gnacoding.eplapp.presentation.screen.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gnacoding.eplapp.domain.model.Club
import com.gnacoding.eplapp.presentation.ui.components.ClubItem
import com.gnacoding.eplapp.presentation.ui.components.EmptyContent
import com.gnacoding.eplapp.presentation.ui.components.LoadingIndicator
import com.gnacoding.eplapp.presentation.util.UiState

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = hiltViewModel(),
    navigateToDetail: (Int?) -> Unit
) {
    viewModel.allFavoriteClubs.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState) {
            is UiState.Loading -> LoadingIndicator()
            is UiState.Success -> FavoriteContent(
                modifier = modifier,
                clubs = uiState.data,
                navigateToDetail = navigateToDetail
            )
            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier,
    clubs: List<Club>,
    navigateToDetail: (Int?) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Favorite") })
        },
        modifier = modifier
    ) { innerPadding ->
        when (clubs.isEmpty()) {
            true -> {
                EmptyContent()
            }
            false -> {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(160.dp),
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier.padding(innerPadding)
                ) {
                    items(clubs) { club ->
                        ClubItem(
                            name = club.name,
                            est = club.established,
                            clubLogo = club.logo,
                            modifier = Modifier.clickable {
                                navigateToDetail(club.id)
                            }
                        )
                    }
                }
            }
        }
    }
}