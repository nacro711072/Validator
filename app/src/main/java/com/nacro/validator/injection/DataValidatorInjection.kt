package com.nacro.validator.injection

import com.nacro.validator.CheckException
import com.nacro.validator.Result
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.lang.reflect.Modifier

class DataValidatorInjection {

    companion object {
        fun inject(data: Any, validator: Any): Map<String, Exception> {
            val hashMap = HashMap<String, Method>()
            validator::class.java.methods.forEach {

                val targetField = it.getAnnotation(ValidateField::class.java)
                if (targetField != null) {

                    hashMap[targetField.name] = it
                }
            }

            val dataClass = data::class.java

            val results = HashMap<String, Exception>()
            dataClass.declaredFields.forEach { field ->
                if (hashMap.containsKey(field.name)) {
                    val method = hashMap[field.name]!!
                    try {

                        field.isAccessible = true
                        val modifiersField: Field = field::class.java.getDeclaredField("modifiers")
                        modifiersField.isAccessible = true
                        modifiersField.setInt(field, field.modifiers and Modifier.FINAL.inv())

                        val dataValue = field.get(data)
                        method.invoke(validator, dataValue)?.let { result ->
                            (result as? Result<Unit>)?.ifError {
                                results[field.name] = it
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            return results
        }
    }
}
