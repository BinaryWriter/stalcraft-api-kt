/*
 * Copyright (c) 2023. BinaryWriter
 */

package me.binarywriter.stalcraft.api.models.clans

import kotlinx.serialization.Serializable

@Serializable
data class ClanInfoListing(
    val totalClans: Int,
    val data: List<ClanInfo>
)