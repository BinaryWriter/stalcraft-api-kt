/*
 * Copyright (c) 2023. BinaryWriter
 */

package me.binarywriter.stalcraft.api.models.auction

import kotlinx.serialization.Serializable

@Serializable
data class LotListing(
    val total: Long,
    val lots: List<Lot>
)