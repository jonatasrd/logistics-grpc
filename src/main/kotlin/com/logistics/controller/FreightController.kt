package com.logistics.controller

import com.example.FreightRequest
import com.example.FreightResponse
import com.example.FreightServiceGrpc.FreightServiceBlockingStub
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import jakarta.inject.Inject

@Controller
class FreightController(
    @Inject val freightClient: FreightServiceBlockingStub
) {

    @Get("/quotation")
    fun getQuotation(@QueryValue postalCode: String) =
        freightClient
            .quote(
                FreightRequest
                    .newBuilder()
                    .setPostalCode(postalCode)
                    .build()
            )
            .toFreightRestResponse()
}

data class FreightRestResponse(
    val postalCode: String,
    val price: Double
)

internal fun FreightResponse.toFreightRestResponse() =
    FreightRestResponse(
        this.postalCode,
        this.price
    )