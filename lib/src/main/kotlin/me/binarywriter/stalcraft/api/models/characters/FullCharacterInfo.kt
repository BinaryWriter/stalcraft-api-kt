/*
 * Copyright (c) 2023. BinaryWriter
 */

package me.binarywriter.stalcraft.api.models.characters

import kotlinx.serialization.Serializable

@Serializable
data class FullCharacterInfo(
    val information: CharacterMetaInfo,
    val clan: CharacterClanInfo
)