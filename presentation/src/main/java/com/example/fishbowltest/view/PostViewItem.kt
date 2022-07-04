package com.example.fishbowltest.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.domain.model.SignDomainModel
import com.example.domain.utils.DateUtils
import com.example.fishbowltest.R
import com.example.fishbowltest.screen.main.MainFeedModel
import com.example.fishbowltest.styles.BorderWidth
import com.example.fishbowltest.styles.CornerRadius
import com.example.fishbowltest.styles.MarginDouble
import com.example.fishbowltest.util.SampleData.postItemViewData

@Composable
fun PostViewItem(
    state: MainFeedModel.PostModel,
) {

    Card(
        border = BorderStroke(BorderWidth, Color.Black), shape = RoundedCornerShape(CornerRadius),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                Modifier
                    .background(
                        Color.Black.copy(alpha = 0.6f)
                    )
                    .padding(MarginDouble)
            ) {
                AsyncImage(
                    model = state.data.feedIcon,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                        .clip(CircleShape),
                    alignment = Alignment.CenterStart
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    getSignLabel(state.data.sign)?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.h6,
                            color = Color.White,
                        )
                    }
                    Text(
                        text = state.data.feedName,
                        style = MaterialTheme.typography.h6,
                        color = Color.White
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                if (state.data.messageType == 1) {
                    state.data.messageData?.linkMetadata?.title?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.fillMaxWidth().padding(15.dp)
                        )
                    }
                    AsyncImage(
                        model = state.data.messageData?.linkMetadata?.imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    state.data.messageData?.linkMetadata?.description?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.fillMaxWidth().padding(15.dp)
                        )
                    }

                } else {
                    Text(
                        text = state.data.text,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.fillMaxWidth().padding(15.dp)
                    )
                }


                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.background(
                    Color.Black.copy(alpha = 0.6f)
                ).padding(MarginDouble)) {
                    Icon(
                        tint = Color.Yellow,
                        imageVector = Icons.Filled.Star,
                        contentDescription = null
                    )
                    state.data.reactionCounters?.let {
                        val count = it.smart + it.funny + it.like + it.helpful + it.uplifting
                        Text(text = count.toString(), style = MaterialTheme.typography.h6)
                    }

                    Icon(
                        tint = Color.Red,
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null
                    )
                    Text(text = state.data.likesCount.toString(), style = MaterialTheme.typography.h6,)

                    Text(
                        text = DateUtils.getStringFromDate(state.data.date),
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.End,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }

}

@Composable
fun getSignLabel(sign: SignDomainModel?): String? {
    return when (sign?.signType) {
        0, 3 -> sign.companyDisplayName
        1 -> sign.location
        2 -> sign.title
        4 -> sign.userColor
        5 -> sign.firstLastName?.firstName + sign.firstLastName?.lastName
        6, 7 -> stringResource(id = R.string.teacher)
        else -> {
            stringResource(id = R.string.deactivated_user)
        }
    }
}

@Preview
@Composable
fun PreviewPostViewItem() {
    PostViewItem(MainFeedModel.PostModel(postItemViewData))
}

