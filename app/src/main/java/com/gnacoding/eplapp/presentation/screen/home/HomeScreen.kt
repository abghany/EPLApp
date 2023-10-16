package com.gnacoding.eplapp.presentation.screen.home

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gnacoding.eplapp.R
import com.gnacoding.eplapp.data.data_source.ClubDataSource
import com.gnacoding.eplapp.domain.model.Club
import com.gnacoding.eplapp.presentation.ui.components.ClubItem
import com.gnacoding.eplapp.presentation.ui.components.EmptyContent
import com.gnacoding.eplapp.presentation.ui.components.LoadingIndicator
import com.gnacoding.eplapp.presentation.ui.theme.EPLAppTheme
import com.gnacoding.eplapp.presentation.util.UiState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int?) -> Unit,
) {
    viewModel.allClubs.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> LoadingIndicator()
            is UiState.Success -> HomeContent(
                clubs = uiState.data,
                navigateToDetail = navigateToDetail,
                modifier = modifier
            )
            is UiState.Error -> EmptyContent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    clubs: List<Club>,
    navigateToDetail: (Int?) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Image(
                    painter = painterResource(
                        id = if (isSystemInDarkTheme()) R.drawable.pl_logo_white else R.drawable.pl_logo
                    ),
                    contentDescription = null,
                    modifier = Modifier.height(56.dp)
                )
            })
        },
        modifier = modifier
    ) { innerPadding ->
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

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun HomeContentLightPreview() {
    EPLAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            HomeContent(
                clubs = ClubDataSource.dummyClubs,
                navigateToDetail = {}
            )
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeContentDarkPreview() {
    EPLAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            HomeContent(
                clubs = ClubDataSource.dummyClubs,
                navigateToDetail = {}
            )
        }
    }
}