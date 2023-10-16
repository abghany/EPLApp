package com.gnacoding.eplapp.presentation.screen.club_detail

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gnacoding.eplapp.R
import com.gnacoding.eplapp.domain.model.Club
import com.gnacoding.eplapp.presentation.ui.components.CardClubInfo
import com.gnacoding.eplapp.presentation.ui.components.TextStadiumInfo
import com.gnacoding.eplapp.presentation.ui.theme.Typography
import com.gnacoding.eplapp.presentation.util.UiState

@Composable
fun ClubDetailScreen(
    clubId: Int,
    viewModel: ClubDetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    viewModel.club.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState) {
            is UiState.Loading -> viewModel.getClub(clubId)
            is UiState.Success -> ClubDetailContent(
                club = uiState.data,
                onAddToFavoriteClicked = viewModel::updateFavoriteClub,
                onBackClicked = navigateBack
            )
            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClubDetailContent(
    modifier: Modifier = Modifier,
    club: Club,
    onAddToFavoriteClicked: (id: Int, isFavorite: Boolean) -> Unit,
    onBackClicked: () -> Unit
) {
    val shareLink = "https://www.premierleague.com/clubs/"
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = club.name) },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SmallFloatingActionButton(
                    onClick = {
                        val share = Intent()
                        share.action = Intent.ACTION_SEND
                        share.type = "text/plain"
                        share.putExtra(Intent.EXTRA_TEXT, "Look at this Club: $shareLink${club.link}")
                        context.startActivity(Intent.createChooser(share, "Share via"))
                    },
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_share),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                FloatingActionButton(
                    onClick = { onAddToFavoriteClicked(club.id ?: 0, !club.isFavorite) }
                ) {
                    Icon(
                        painter = if (club.isFavorite) painterResource(id = R.drawable.ic_favorite)
                        else painterResource(id = R.drawable.ic_favorite_outline),
                        contentDescription = null,
                        tint = if (club.isFavorite) Color.Red else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            ClubContent(
                logo = club.logo,
                name = club.name,
                established = club.established,
                description = club.description,
                matchesPlayed = club.matchesPlayed,
                wins = club.wins,
                losses = club.losses,
                goals = club.goals,
                goalsConceded = club.goalsConceded,
                cleanSheets = club.cleanSheets
            )
            Spacer(modifier = Modifier.height(16.dp))
            StadiumContent(
                stadiumPhoto = club.stadiumPhoto,
                stadiumName = club.stadiumName,
                stadiumDesc = club.stadiumDesc,
                stadiumCapacity = club.stadiumCapacity,
                stadiumBuilt = club.stadiumBuilt,
                stadiumPitchSize = club.stadiumPitchSize,
                stadiumAddress = club.stadiumAddress
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Data taken on October 4th, 2023",
                    style = Typography.bodySmall,
                    modifier = Modifier.alpha(0.8f)
                )
            }
        }
    }
}

@Composable
fun ClubContent(
    logo: Int,
    name: String,
    established: String,
    description: String,
    matchesPlayed: String,
    wins: String,
    losses: String,
    goals: String,
    goalsConceded: String,
    cleanSheets: String,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = painterResource(id = logo),
            contentDescription = name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(144.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = Typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Est. $established",
            style = Typography.titleSmall,
            modifier = Modifier.alpha(0.5f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider(
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.alpha(0.6f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = description,
            style = Typography.bodyMedium,
            modifier = Modifier.alpha(0.8f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            CardClubInfo(title = "Matches Played", info = matchesPlayed, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            CardClubInfo(title = "Wins", info = wins, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            CardClubInfo(title = "Losses", info = losses, modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            CardClubInfo(title = "Goals", info = goals, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            CardClubInfo(title = "Goals Conceded", info = goalsConceded, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            CardClubInfo(title = "Clean Sheet", info = cleanSheets, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun StadiumContent(
    stadiumPhoto: Int,
    stadiumName: String,
    stadiumDesc: String,
    stadiumCapacity: String,
    stadiumBuilt: String,
    stadiumPitchSize: String,
    stadiumAddress: String,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Stadium Information",
            style = Typography.titleMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = stadiumPhoto),
            contentDescription = stadiumName,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(256.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stadiumName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = Typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stadiumDesc,
            style = Typography.bodyMedium,
            modifier = Modifier.alpha(0.8f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextStadiumInfo(title = "Capacity", info = stadiumCapacity)
        Spacer(modifier = Modifier.height(4.dp))
        TextStadiumInfo(title = "Built", info = stadiumBuilt)
        Spacer(modifier = Modifier.height(4.dp))
        TextStadiumInfo(title = "Pitch size", info = stadiumPitchSize)
        Spacer(modifier = Modifier.height(4.dp))
        TextStadiumInfo(title = "Stadium address", info = stadiumAddress)
    }
}