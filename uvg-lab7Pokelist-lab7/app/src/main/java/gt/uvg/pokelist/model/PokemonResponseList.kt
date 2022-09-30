package gt.uvg.pokelist.model

data class PokemonResponseList(
    val count: Int,
    val next: String,
    val results: List<Result>
)
    data class Result(
        val name: String,
        val url: String
    )