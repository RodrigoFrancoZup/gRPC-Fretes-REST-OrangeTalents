package br.com.zup

import br.com.edu.FreteRequest
import br.com.edu.FreteResponse
import br.com.edu.FretesServiceGrpc
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import javax.inject.Inject

//Só será possível fazer o @Inject do objeto, pois criamos a classe GrpcClientFactory!
@Controller
class CalculadoraDeFreteController(@Inject val gRpcClient: FretesServiceGrpc.FretesServiceBlockingStub) {

    @Get("/api/fretes")
    fun calcula(@QueryValue(defaultValue = "") cep: String): FreteDto {

        val request = FreteRequest.newBuilder()
            .setCep(cep)
            .build()

        val response = gRpcClient.calculaFrete(request)

        return FreteDto(response.cep, response.valor)
    }
}

data class FreteDto(
    val cep: String,
    val frete: Double
) {
}