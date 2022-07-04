package com.example.fishbowltest.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fishbowltest.screen.main.MainFeedModel

@Composable
fun MainRecyclerView(
    state: List<MainFeedModel>,
    onScrollToEnd: () -> Unit
) {

    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = listState
    ) {

        items(state) { item ->
            when (item) {
                is MainFeedModel.PostModel -> PostViewItem(
                    state = item
                )
                is MainFeedModel.CardModel -> CardViewItem(
                    state = item
                )
            }

            val scrolledToEnd =
                listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == listState.layoutInfo.totalItemsCount - 1
            if (scrolledToEnd) {
                onScrollToEnd.invoke()
            }
        }
    }
}