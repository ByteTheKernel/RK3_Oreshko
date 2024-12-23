package com.example.rk_3.utils

import com.example.rk_3.R

fun getCuisineFlag(cuisine: String): String {
    return when (cuisine.lowercase()) {
        "italian" -> "\uD83C\uDDEE\uD83C\uDDF9" // 🇮🇹
        "japanese" -> "\uD83C\uDDEF\uD83C\uDDF5" // 🇯🇵
        "mexican" -> "\uD83C\uDDF2\uD83C\uDDFD" // 🇲🇽
        "french" -> "\uD83C\uDDEB\uD83C\uDDF7" // 🇫🇷
        "indian" -> "\uD83C\uDDEE\uD83C\uDDF3" // 🇮🇳
        "chinese" -> "\uD83C\uDDE8\uD83C\uDDF3" // 🇨🇳
        "american" -> "\uD83C\uDDFA\uD83C\uDDF8" // 🇺🇸
        "brazilian" -> "\uD83C\uDDE7\uD83C\uDDF7" // 🇧🇷
        "russian" -> "\uD83C\uDDF7\uD83C\uDDFA" // 🇷🇺
        "pakistani" -> "\uD83C\uDDF5\uD83C\uDDF0" // 🇵🇰
        "lebanese" -> "\uD83C\uDDF1\uD83C\uDDE7" // 🇱🇧
        "turkish" -> "\uD83C\uDDF9\uD83C\uDDF7" // 🇹🇷
        "thai" -> "\uD83C\uDDF9\uD83C\uDDED" // 🇹🇭
        "greek" -> "\uD83C\uDDEC\uD83C\uDDF7" // 🇬🇷
        "korean" -> "\uD83C\uDDF0\uD83C\uDDF7" // 🇰🇷
        "moroccan" -> "\uD83C\uDDF2\uD83C\uDDE6" // 🇲🇦
        "hawaiian" -> "hawaiian_flag" // us-🇭🇮
        "vietnamese" -> "\uD83C\uDDFB\uD83C\uDDF3" // 🇻🇳
        "spanish" -> "\uD83C\uDDEA\uD83C\uDDF8" // 🇪🇸
        else -> "\uD83C\uDF0D" // 🌍 (универсальный флаг для неизвестной кухни)
    }
}