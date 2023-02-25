/*
 * Copyright (c) 2023. BinaryWriter
 */

@file:UseSerializers(InstantSerializer::class)

package me.binarywriter.stalcraft.api.models.other

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import me.binarywriter.stalcraft.api.serializers.InstantSerializer
import java.time.Instant

@Serializable
data class Emission(
    val currentStart: Instant?,
    val previousStart: Instant?,
    val previousEnd: Instant?
)