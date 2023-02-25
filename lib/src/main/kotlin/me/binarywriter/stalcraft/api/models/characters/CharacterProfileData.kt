/*
 * Copyright (c) 2023. BinaryWriter
 */

@file:UseSerializers(InstantSerializer::class, UUIDSerializer::class)

package me.binarywriter.stalcraft.api.models.characters

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import me.binarywriter.stalcraft.api.serializers.InstantSerializer
import me.binarywriter.stalcraft.api.serializers.UUIDSerializer
import java.time.Instant
import java.util.*

@Serializable
data class CharacterProfileData(
    val username: String,
    val uuid: UUID,
    val status: String,
    val alliance: String,
    val lastLogin: Instant?,
    val displayedAchievements: List<String>,
    val clan: CharacterClanInfo,
    val stats: List<CharacterStatValue>
)