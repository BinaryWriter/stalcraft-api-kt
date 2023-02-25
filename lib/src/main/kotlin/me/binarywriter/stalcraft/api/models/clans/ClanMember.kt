/*
 * Copyright (c) 2023. BinaryWriter
 */

@file:UseSerializers(InstantSerializer::class)

package me.binarywriter.stalcraft.api.models.clans

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import me.binarywriter.stalcraft.api.enums.ClanRank
import me.binarywriter.stalcraft.api.serializers.InstantSerializer
import java.time.Instant

@Serializable
data class ClanMember(
    val name: String,
    val rank: ClanRank,
    val joinTime: Instant
)