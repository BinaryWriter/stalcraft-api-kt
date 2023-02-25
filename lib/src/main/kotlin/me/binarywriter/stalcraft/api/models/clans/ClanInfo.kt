/*
 * Copyright (c) 2023. BinaryWriter
 */

@file:UseSerializers(InstantSerializer::class)

package me.binarywriter.stalcraft.api.models.clans

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import me.binarywriter.stalcraft.api.serializers.InstantSerializer
import java.time.Instant

@Serializable
data class ClanInfo(
    val id: String,
    val name: String,
    val tag: String,
    val level: Int,
    val levelPoints: Int,
    val registrationDate: Instant,
    val alliance: String?,
    val description: String,
    val leader: String,
    val memberCount: Int
)