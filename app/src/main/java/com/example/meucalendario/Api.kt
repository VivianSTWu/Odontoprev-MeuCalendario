package com.example.meucalendario

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.sql.Date

const val URL = "http://10.0.2.2:8080"

data class NetworkEvento(
    val id_evento: String, // Supondo que o id exista aqui
    val tipo_evento: String,
    val desc_evento: String,
    val dt_evento: String, // Usando String para simplificar a manipulação de datas
)

data class NetworkEventoCreate(
    val tipo_evento: String,
    val desc_evento: String,
    val dt_evento: String,
    val fk_cliente: ClienteId
) {
    data class ClienteId(
        val id_cliente: String
    )
}


interface MeuCalendarioService {
    @GET("/cliente/{id}/eventos")
    fun getAllUsersEvents(@Path("id") id: String) : Call<List<NetworkEvento>>

    @POST("evento")
    fun createEvent(
        @Body novoEvento: NetworkEventoCreate
    ): Call<NetworkEventoCreate>

    @DELETE("/evento/{id}")
    fun deleteEvent(@Path("id") eventId: String): Call<Void> // Void significa que não esperamos um corpo de resposta

}

object Api {
    fun buildService() : MeuCalendarioService {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(MeuCalendarioService::class.java)
    }
}
