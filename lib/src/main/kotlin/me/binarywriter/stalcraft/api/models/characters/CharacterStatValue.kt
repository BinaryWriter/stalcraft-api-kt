/*
 * Copyright (c) 2023. BinaryWriter
 */

package me.binarywriter.stalcraft.api.models.characters

import kotlinx.serialization.Serializable
import me.binarywriter.stalcraft.api.enums.StatType
import me.binarywriter.stalcraft.api.serializers.CharacterStatValueSerializer

@Serializable(CharacterStatValueSerializer::class)
data class CharacterStatValue(
    val id: String,
    val type: StatType,
    val value: Any
)