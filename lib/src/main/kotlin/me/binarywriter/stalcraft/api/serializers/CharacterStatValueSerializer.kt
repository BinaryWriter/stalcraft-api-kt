/*
 * Copyright (c) 2023. BinaryWriter
 */

package me.binarywriter.stalcraft.api.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import kotlinx.serialization.serializer
import me.binarywriter.stalcraft.api.enums.StatType
import me.binarywriter.stalcraft.api.models.characters.CharacterStatValue

class CharacterStatValueSerializer : KSerializer<CharacterStatValue> {
    override val descriptor = buildClassSerialDescriptor("CharacterStatValue") {
        element("id", serialDescriptor<String>())
        element("type", serialDescriptor<StatType>())
        element("value", buildClassSerialDescriptor("Any"))
    }

    @Suppress("UNCHECKED_CAST")
    private val valueSerializers: Map<StatType, KSerializer<Any>> = mapOf(
        StatType.INTEGER to serializer<Int>(),
        StatType.DECIMAL to serializer<Double>(),
        StatType.DURATION to serializer<Long>(),
        StatType.DATE to InstantSerializer(),
    ).mapValues { (_, v) -> v as KSerializer<Any> }

    override fun deserialize(decoder: Decoder): CharacterStatValue {
        return decoder.decodeStructure(descriptor) {
            val id = decodeStringElement(descriptor, 0)
            val type = StatType.valueOf(decodeStringElement(descriptor, 1))
            val value = decodeSerializableElement(descriptor, 2, valueSerializers[type]!!)
            CharacterStatValue(id, type, value)
        }
    }

    override fun serialize(encoder: Encoder, value: CharacterStatValue) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.id)
            encodeStringElement(descriptor, 1, value.type.name)
            encodeSerializableElement(descriptor, 2, valueSerializers[value.type]!!, value.value)
        }
    }
}