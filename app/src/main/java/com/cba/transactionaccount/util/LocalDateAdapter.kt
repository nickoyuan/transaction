package com.cba.transactionaccount.util

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import org.joda.time.LocalDate
import java.lang.reflect.Type

class LocalDateAdapter : JsonTypeConverter<LocalDate> {
    override fun serialize(
        src: LocalDate,
        typeOfSrc: Type,
        context: JsonSerializationContext?
    ): JsonElement {
       return JsonPrimitive(src.toString())
    }

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext?
    ): LocalDate {
       return LocalDate.parse(json.asString)
    }
}