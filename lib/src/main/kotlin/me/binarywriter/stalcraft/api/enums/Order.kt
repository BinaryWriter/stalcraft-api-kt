/*
 * Copyright (c) 2023. BinaryWriter
 */

package me.binarywriter.stalcraft.api.enums

@Suppress("unused")
enum class Order(private val value: String) {
    Ascending("asc"),
    Descending("desc");

    init {
        javaClass.getField("name").apply {
            this.isAccessible = true
            this.set(this@Order, value)
            this.isAccessible = false
        }
    }
}