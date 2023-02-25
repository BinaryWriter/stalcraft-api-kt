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
data class Price(
    val amount: Int,
    val price: Long,
    val time: Instant,
    val additional: Map<String, String>
)