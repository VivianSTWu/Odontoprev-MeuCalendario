/*
package com.example.meucalendario

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.sql.Date

const val URL = "/"

data class NetworkEvento(
    val id_evento: Int,
    val dt_evento: Date,
    val desc_evento: String,
    val tipo_evento: String,
    val id_cliente: Int
)

interface MeuCalendarioService {
    @GET("{id_cliente}/events")
    fun getAllUsersEvents() : Call<List<NetworkEvento>>
}

object Api {
    fun buildService() : MeuCalendarioService {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(MeuCalendarioService::class.java)
    }
}*/
