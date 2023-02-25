/*
 * Copyright (c) 2023. BinaryWriter
 */

package me.binarywriter.stalcraft.api.models.characters

import kotlinx.serialization.Serializable
import me.binarywriter.stalcraft.api.models.clans.ClanInfo
import me.binarywriter.stalcraft.api.models.clans.ClanMember

@Serializable
data class CharacterClanInfo(
    val info: ClanInfo,
    val member: ClanMember
)