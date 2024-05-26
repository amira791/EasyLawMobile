import com.example.easylawmobile.URL
import com.example.easylawmobile.data.models.Service
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface PaymentEndpoints{

    @GET("payment/service")
    suspend fun getServices(): ServiceResponse

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
