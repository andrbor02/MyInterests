package com.example.core_utils_android.serialization

import org.json.JSONArray
import org.json.JSONObject

fun serializeListOfMaps(list: List<HashMap<String, Any>>): String {
    val jsonArray = JSONArray()

    list.forEach { hashMap ->
        val jsonObject = JSONObject()
        hashMap.forEach { (operand, operator) ->
            jsonObject.put(operand, operator)
        }
        jsonArray.put(jsonObject)
    }

    return jsonArray.toString()
}