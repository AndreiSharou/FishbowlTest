package com.example.fishbowltest.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fishbowltest.screen.main.MainFeedModel
import com.example.fishbowltest.styles.*

@Composable
fun CardViewItem(
    state: MainFeedModel.CardModel,
    modifier: Modifier = Modifier,
) {

    Card(
        border = BorderStroke(BorderWidth, Color.Black), shape = RoundedCornerShape(CornerRadius),
        backgroundColor =
        state.data.content?.backgroundColor?.color ?: MaterialTheme.colors.background

    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(MarginDouble),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            state.data.content?.let { contentData ->
                val textColor = contentData.titleColor?.color
                    ?: MaterialTheme.colors.primary

                contentData.title?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h5,
                        color = textColor
                    )
                }

                contentData.resultsUi?.resultsTitle?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h6,
                        color = textColor
                    )
                }
                contentData.resultsUi?.resultsSubtitle?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.subtitle1,
                        color = textColor
                    )
                }

                contentData.text?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body1,
                        color = textColor
                    )
                }

                contentData.answers?.let {
                    LazyRow {
                        items(it) { item ->
                            Button(
                                onClick = {}, shape = RoundedCornerShape(ButtonCornerRadius),
                                modifier = Modifier
                                    .defaultMinSize(20.dp, 20.dp)
                                    .padding(MarginSingle),
                                contentPadding = PaddingValues(MarginDouble),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
                            ) {
                                Text(
                                    text = item.text,
                                    style = MaterialTheme.typography.body1,
                                    color = item.color.color,
                                    modifier = Modifier.padding(MarginSingle)
                                )
                            }
                        }
                    }
                }

                if (contentData.resultsUi?.resultsButtonText?.isNotEmpty() == true) {
                    contentData.resultsUi?.resultsButtonText?.let {
                        Spacer(Modifier.height(20.dp))
                        Button(
                            onClick = {}, shape = RoundedCornerShape(ButtonCornerRadius),
                            modifier = modifier
                                .shadow(0.dp),
                            contentPadding = PaddingValues(MarginDouble),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                            border = BorderStroke(BorderWidth, Color.Gray)
                        ) {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.body1,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}