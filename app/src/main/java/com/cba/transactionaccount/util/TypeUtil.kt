@file:Suppress("UNCHECKED_CAST")

package com.cba.transactionaccount.util

import java.lang.ref.WeakReference
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Proxy

object TypeUtil {

    fun <V> getGenericSafeInstance(weakReference: WeakReference<out V>?, clazz: Class<*>) =
            if (weakReference?.get() != null) {
                weakReference.get() as V
            } else {
                val vClass: Class<V> = getGenericClass(clazz)
                Proxy.newProxyInstance(vClass.classLoader, arrayOf<Class<*>>(vClass)) { _, _, _ -> null } as V
            }

    private fun <V> getGenericClass(clazz: Class<*>): Class<V> {
        val genericSuperclass = clazz.genericSuperclass

        val parameterizedType = when (genericSuperclass) {
            is ParameterizedType -> // This is for Android runtime
                genericSuperclass
            is Class<*> -> // This is for unit tests
                genericSuperclass.genericSuperclass as ParameterizedType
            else -> // This shouldn't happen
                throw RuntimeException("Class is not a generic class!")
        }

        return parameterizedType.actualTypeArguments[0] as Class<V>
    }
}