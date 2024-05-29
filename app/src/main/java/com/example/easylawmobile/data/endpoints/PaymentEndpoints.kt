import com.example.easylawmobile.URL
import com.example.easylawmobile.data.models.Service
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST



data class Token(
    val id: String
)

data class SubscribeRequest(
    val priceId: String,
    val token: Token,
    val method: String
)

interface PaymentEndpoints{

    @GET("payment/service")
    suspend fun getServices(
        @Header("Authorization") authToken: String,
    ): ServiceResponse


    @POST("payment/subscribe")
    suspend fun subscribe(
        @Header("Authorization") authToken: String,
        @Body subscribeRequest: SubscribeRequest
    ): Response<Void>



    companion object {
        var endpoint: PaymentEndpoints? = null
        var gson = GsonBuilder()
            .setLenient()
            .create()

        fun createEndpoint(): PaymentEndpoints {
            if (endpoint == null) {
                endpoint = Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build()
                    .create(PaymentEndpoints::class.java)
            }

            return endpoint!!
        }
    }
}


data class ServiceResponse(
    val all: List<Service>,
    val current: String?
)
