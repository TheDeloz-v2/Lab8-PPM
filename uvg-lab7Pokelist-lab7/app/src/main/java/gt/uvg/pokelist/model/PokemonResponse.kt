package gt.uvg.pokelist.model

import android.os.Parcel
import android.os.Parcelable

data class PokemonResponse(
    val id: Int,
    val name: String?,
    val sprites: Sprites,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        TODO("sprites")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonResponse> {
        override fun createFromParcel(parcel: Parcel): PokemonResponse {
            return PokemonResponse(parcel)
        }

        override fun newArray(size: Int): Array<PokemonResponse?> {
            return arrayOfNulls(size)
        }
    }
}

data class Sprites(
    val back_default: String,
    val back_shiny: String,
    val front_default: String,
    val front_shiny: String,
)
