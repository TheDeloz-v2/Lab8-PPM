package gt.uvg.pokelist.api

import gt.uvg.pokelist.model.PokemonResponse
import gt.uvg.pokelist.model.PokemonResponseList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonCall {
    @GET("pokemon/{pokeId}")
    fun getPokemonId(@Path("pokeId")pokeId:Int): Call<PokemonResponse>

    @GET("pokemon?limit=100")
    fun getPokemons(): Call<PokemonResponseList>
}