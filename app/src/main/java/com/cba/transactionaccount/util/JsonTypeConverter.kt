package com.cba.transactionaccount.util

import com.google.gson.JsonDeserializer
import com.google.gson.JsonSerializer


interface JsonTypeConverter<T> : JsonSerializer<T>, JsonDeserializer<T>