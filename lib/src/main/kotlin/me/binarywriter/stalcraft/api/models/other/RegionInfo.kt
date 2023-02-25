/*
 * Copyright (c) 2023. BinaryWriter
 */

package me.binarywriter.stalcraft.api.models.other

import kotlinx.serialization.Serializable
import me.binarywriter.stalcraft.api.enums.Region

@Serializable
data class RegionInfo(
    val id: Region,
    val name: String
)