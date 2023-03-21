package com.logistics.client

import com.example.FreightServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import jakarta.inject.Singleton

@Factory
class GrpcClientFactory {

    @Singleton
    fun freightClient(@GrpcChannel("quote") channel: ManagedChannel) =
        FreightServiceGrpc.newBlockingStub(channel)

}