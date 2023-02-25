/*
 * Copyright (c) 2023. BinaryWriter
 */

package me.binarywriter.stalcraft.api

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.binarywriter.stalcraft.api.enums.Order
import me.binarywriter.stalcraft.api.enums.Region
import me.binarywriter.stalcraft.api.enums.Sort
import me.binarywriter.stalcraft.api.models.auction.LotListing
import me.binarywriter.stalcraft.api.models.auction.PriceListing
import me.binarywriter.stalcraft.api.models.characters.CharacterProfileData
import me.binarywriter.stalcraft.api.models.clans.ClanInfo
import me.binarywriter.stalcraft.api.models.clans.ClanInfoListing
import me.binarywriter.stalcraft.api.models.other.Emission
import me.binarywriter.stalcraft.api.models.other.RegionInfo
import okhttp3.Headers
import okhttp3.Request

@Suppress("OPT_IN_USAGE", "SpellCheckingInspection", "unused")
open class Api(
    region: Region,
    val token: String,
    isDemo: Boolean
) {
    @Suppress("unused")
    companion object {
        const val DEMO_APP_TOKEN =
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwibmJmIjoxNjczNzk3ODM4LCJleHAiOjQ4MjczOTc4MzgsImlhdCI6MTY3Mzc5NzgzOCwianRpIjoiYXhwbzAzenJwZWxkMHY5dDgzdzc1N2x6ajl1MmdyeHVodXVlb2xsZ3M2dml1YjVva3NwZTJ3eGFrdjJ1eWZxaDU5ZDE2ZTNlN2FqdW16Z3gifQ.ZNSsvwAX72xT5BzLqqYABuH2FGbOlfiXMK5aYO1H5llG51ZjcPvOYBDRR4HUoPZVLFY8jyFUsEXNM7SYz8qL9ePmLjJl6pib8FEtqVPmf9ldXvKkbaaaSp4KkJzsIEMY_Z5PejB2Vr-q-cL13KPgnLGUaSW-2X_sHPN7VZJNMjRgjw4mPiRZTe4CEpQq0BEcPrG6OLtU5qlZ6mLDJBjN2xtK0DI6xgmYriw_5qW1mj1nqF_ewtUiQ1KTVhDgXnaNUdkGsggAGqyicTei0td6DTKtnl3noD5VkipWn_CwSqb2Mhm16I9BPfX_d5ARzWrnrwPRUf6PA_7LipNU6KkkW0mhZfmwEPTm_sXPus0mHPENoVZArdFT3L5sOYBcpqwvVIEtxRUTdcsKp-y-gSzao5muoyPVoCc2LEeHEWx0cIi9spsZ46SPRQpN4baVFp7y5rp5pjRsBKHQYUJ0lTmh1_vyfzOzbtNN2v6W_5w9JTLrN1U6fhmifvKHppFSEqD6DameL1TC59kpIdufRkEU9HE4O-ErEf1GuJFRx-Dew6XDvb_ExhvEqcw31yNvKzpVqLYJfLazqn6tUbVuAiPwpy6rP9tYO2taT1vj5TGn_vxwDu9zoLWe796tFMPS-kmbCglxB5C9L4EbpfWNbWxYjUkTvjT2Ml9OnrB0UbYo1jI"

        /**
         * Returns list of regions which can be access by api calls.
         */
        fun getRegions(): List<RegionInfo> = get(
            "https://eapi.stalcraft.net/regions",
            Headers.headersOf(
                "User-Agent", "stalcraft-api-kt"
            )
        )

        protected val http = okhttp3.OkHttpClient()
        protected val json = Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }

        protected inline fun <reified T> get(url: String, headers: Headers): T {
            val request = Request.Builder()
                .get().url(url)
                .headers(headers)
                .build()

            return json.decodeFromString(
                http.newCall(request)
                    .execute()
                    .body!!
                    .charStream()
                    .readText()
            )
        }
    }

    /**
     * Returns information about current emission, if any, and recorded time of the previous one.
     */
    fun getEmissionStatus(): Emission = get("emission")

    /**
     * Returns list of currently active lots on the auction for the given item. Lots are sorted based on given parameter.
     *
     * @param additional Whether to include additional information about lots: true or false.
     * @param limit Amount of lots to return, starting from offset, min 0, max 100.
     * @param offset Amount of lots in list to skip.
     * @param order Either asc or desc.
     * @param sort Property to sort by, one of: time_created, time_left, current_price, buyout_price.
     */
    fun getActiveItemLots(
        item: String,
        additional: Boolean = false,
        limit: Int = 20,
        offset: Int = 0,
        order: Order = Order.Ascending,
        sort: Sort = Sort.TimeCreated
    ): LotListing {
        return get("auction/${item}/lots?offset=${offset}&limit=${limit}&sort=${sort}&order=${order}&additional=${additional}")
    }

    /**
     * Returns history of prices for lots which were bought from auction for the given item. Prices are sorted in descending order by recorded time of purchase.
     *
     * @param additional Whether to include additional information about lots: true or false.
     * @param limit Amount of prices to return, starting from offset, min 0, max 100.
     * @param offset Amount of prices in list to skip.
     */
    fun getItemPriceHistory(
        item: String,
        additional: Boolean = false,
        limit: Int = 20,
        offset: Int = 0
    ): PriceListing {
        return get("auction/${item}/history?limit=${limit}&additional=${additional}&offset=${offset}")
    }

    /**
     * Returns information about player's profile. Includes alliance, profile description, last login time, stats, etc.
     *
     * @param character Case-insensitive name of the character
     */
    fun getCharacterProfile(
        character: String
    ): CharacterProfileData {
        return get("character/by-name/${character}/profile")
    }

    /**
     * Returns all clans which are currently registered in the game on specified region.
     *
     * @param limit Amount of clans to return, starting from offset, min 0, max 100.
     */
    fun getClanList(
        limit: Int = 20,
        offset: Int = 0
    ): ClanInfoListing {
        return get("clans?offset=${offset}&limit=${limit}")
    }

    /**
     * Returns information about the given clan.
     */
    fun getClanInformation(
        clanId: String
    ): ClanInfo {
        return get("clan/${clanId}/info")
    }

    protected val apiUrl = "https://${if (isDemo) "d" else "e"}api.stalcraft.net/${region.name}"

    protected inline fun <reified T> get(path: String): T {
        return Companion.get(
            "$apiUrl/$path",
            Headers.headersOf(
                "Authorization", "Bearer $token",
                "User-Agent", "stalcraft-api-kt"
            )
        )
    }
}