/*
 * Copyright (c) 2023. BinaryWriter
 */

package me.binarywriter.stalcraft.api.models.auction

import kotlinx.serialization.Serializable

@Serializable
data class PriceListing(
    val total: Long,
    val prices: List<Price>
)