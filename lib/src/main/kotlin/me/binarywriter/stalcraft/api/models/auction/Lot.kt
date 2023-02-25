/*
 * Copyright (c) 2023. BinaryWriter
 */

@file:UseSerializers(InstantSerializer::class)

package me.binarywriter.stalcraft.api.models.auction

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import me.binarywriter.stalcraft.api.serializers.InstantSerializer
import java.time.Instant

@Serializable
data class Lot(
    val itemId: String,
    val amount: Int,
    val startPrice: Long,
    val currentPrice: Long?,
    val buyoutPrice: Long,
    val startTime: Instant,
    val endTime: Instant,
    val additional: Map<String, String>
)