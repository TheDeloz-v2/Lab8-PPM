package gt.uvg.pokelist.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInstance {
    companion object {
        private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        private val retro by lazy{
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder().addInterceptor(logging).build()

            Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(MoshiConverterFactory.create(moshi)).client(client).build()
        }

        val api: PokemonCall by lazy{
            retro.create(PokemonCall::class.java)
        }
    }
}