package com.example.fishbowltest.util

import com.example.data.resource.local.room.model.ContentLocalModel
import com.example.data.resource.local.room.model.toLocalModel
import com.example.data.resource.remote.model.PostRemoteModel
import com.example.domain.model.PostDomainModel
import com.example.domain.model.toDomainModel
import com.example.fishbowltest.screen.main.MainFeedModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object SampleData {

    val postRemoteRaw = """{
			"_id": "62c2d888ac428f0045c31aa4",
			"likesCount": 1,
			"likesCountIncludingComments": 1,
			"commentsCount": 0,
			"federationPoints": 1656936704,
			"messageType": 0,
			"sharesCount": 0,
			"bowlMemberBadges": [
				0
			],
			"contentFederationMode": 0,
			"feedId": "5f2910512d5ef4001d23f70e",
			"postedCompanyAlias": {
				"_id": "62c2d888ac428f0045c31aa5",
				"aliasId": "5f3b8bc3bc1bc700040b94e6",
				"aliasName": "Kk kompany limited LTD"
			},
			"feedType": 2,
			"division": "Strategy",
			"reactionCountersIncludingComments": {
				"like": 1,
				"helpful": 0,
				"smart": 0,
				"funny": 0,
				"uplifting": 0,
				"_id": "62c2d888ac428f0045c31aa6"
			},
			"reactionCounters": {
				"like": 1,
				"helpful": 0,
				"smart": 0,
				"funny": 0,
				"uplifting": 0,
				"_id": "62c2d888ac428f0045c31aa7"
			},
			"sign": {
				"_id": "62c2d888ac428f0045c31aa8",
				"companyDisplayName": "Kk kompany limited LTD",
				"signType": 0,
				"userColor": "999999",
				"schoolMeta": null,
				"signAccent": 0
			},
			"postedUserRegionId": "61891c8e2ca26eaffdf8d90f",
			"text": "Navuhodonoser",
			"messageData": {
				"text": "Navuhodonoser",
				"linkMetadata": null
			},
			"handleUrl": "navuhodonoser-uota-5",
			"tags": [],
			"userDefinedMentions": [],
			"likes": [],
			"date": "2022-07-04T12:09:44.530Z",
			"feedName": "THIS IS STAGE",
			"feedIcon": "https://d2yhyolo2glazm.cloudfront.net/crowd_images/5AX3xefeDQHSX.png",
			"feedHandleUrl": "this-is-stage",
			"publicFeed": true,
			"feedBackground": {
				"from": "A46600",
				"to": "D16500"
			},
			"feedFishIconColor": "6b0b0b",
			"inBookmarks": false,
			"joinMode": 0,
			"feedNumberOfUsers": 9999,
			"feedNumberOfFollowers": 9999,
			"feedHideNumberOfUsers": true,
			"canBeEmbedded": true,
			"canJoinBowl": true
		}"""

    val postRemoteModel: PostRemoteModel
        get() {
            val type = object : TypeToken<PostRemoteModel>() {}.type
            return Gson().fromJson(postRemoteRaw, type)
        }

    val postItemViewData: PostDomainModel
        get() {
            return postRemoteModel.toLocalModel().toDomainModel()
        }

}