/*
 * Copyright (c) 2023. BinaryWriter
 */

@file:UseSerializers(InstantSerializer::class)

package me.binarywriter.stalcraft.api.models.characters

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import me.binarywriter.stalcraft.api.serializers.InstantSerializer
import java.time.Instant

@Serializable
data class CharacterMetaInfo(
    val id: String,
    val name: String,
    val creationTime: Instant
)