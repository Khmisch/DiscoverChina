package com.example.myapplication.model.profile

import com.example.myapplication.model.Links
import com.example.myapplication.model.PhotoItem
import com.example.myapplication.model.ProfileImage
import com.example.myapplication.model.Social
import com.example.pinterest.model.profile.Meta
import com.example.pinterest.model.profile.Tags

data class ProfileResp(
    val accepted_tos: Boolean,
    val allow_messages: Boolean,
    val badge: Any,
    val bio: Any,
    val downloads: Int,
    val first_name: String,
    val followed_by_user: Boolean,
    val followers_count: Int,
    val following_count: Int,
    val for_hire: Boolean,
    val id: String,
    val instagram_username: Any,
    val last_name: String,
    val links: Links,
    val location: Any,
    val meta: Meta,
    val name: String,
    val numeric_id: Int,
    val photos: List<PhotoItem>,
    val portfolio_url: Any,
    val profile_image: ProfileImage,
    val social: Social,
    val tags: Tags,
    val total_collections: Int,
    val total_likes: Int,
    val total_photos: Int,
    val twitter_username: Any,
    val updated_at: String,
    val username: String
)