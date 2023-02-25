/*
 * Copyright (c) 2023. BinaryWriter
 */

package me.binarywriter.stalcraft.api.enums

@Suppress("unused")
enum class Sort(private val value: String) {
    TimeCreated("time_created"),
    TimeLeft("time_left"),
    CurrentPrice("current_price"),
    BuyoutPrice("buyout_price");

    init {
        javaClass.getField("name").apply {
            this.isAccessible = true
            this.set(this@Sort, value)
            this.isAccessible = false
        }
    }

    override fun toString(): String {
        return value
    }
}