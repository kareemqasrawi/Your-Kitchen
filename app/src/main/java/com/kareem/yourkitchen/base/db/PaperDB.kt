package com.kareem.yourkitchen.base.db

import android.content.Context
import io.paperdb.Paper

object PaperDB {
    private fun init(context: Context) {
        // Initialize Paper with the given context
        Paper.init(context)
    }

    fun put(key: String, value: Any) {
        Paper.book().write(key, value)
    }

    fun <T> get(key: String): T? {
        return Paper.book().read(key)
    }


    fun delete(key: String) {
        Paper.book().delete(key)
    }


    /* keys */
    const val isLoginProvider = "IS_LOGIN_PROVIDER"
    const val providerPhone = "PROVIDER_PHONE"


    const val isUserLogin = "IS_USER_LOGIN"
    const val userPhone = "USER_PHONE"





    /*end*/

    @JvmInline
    value class SetData(val context: Context) {

        fun setIsLoginProvider(value: Any) {
            init(context)
            put(isLoginProvider, value)
        }

        fun setProviderPhone(value: Any) {
            init(context)
            put(providerPhone, value)
        }



        fun setIsLoginUser(value: Any) {
            init(context)
            put(isUserLogin, value)
        }

        fun setUserPhone(value: Any) {
            init(context)
            put(userPhone, value)
        }



    }

    @JvmInline
    value class GetData(val context: Context) {

        fun <T> getIsLoginProvider(): T? {
            init(context)
            return get<T>(isLoginProvider)
        }

        fun <T> getProviderPhone(): T? {
            init(context)
            return get<T>(providerPhone)
        }

        fun <T> getIsLoginUser(): T? {
            init(context)
            return get<T>(isUserLogin)
        }

        fun <T> getUserPhone(): T? {
            init(context)
            return get<T>(userPhone)
        }




    }


}