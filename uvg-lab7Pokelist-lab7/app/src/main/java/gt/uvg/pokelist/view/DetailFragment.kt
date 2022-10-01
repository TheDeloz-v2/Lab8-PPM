package gt.uvg.pokelist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import gt.uvg.pokelist.R
import gt.uvg.pokelist.api.RetrofitInstance
import gt.uvg.pokelist.model.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragment : Fragment() {

    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val front: ImageView = view.findViewById(R.id.imageView2)

        val back: ImageView = view.findViewById(R.id.imageView3)

        val fronts: ImageView = view.findViewById(R.id.imageView4)

        val backs: ImageView = view.findViewById(R.id.imageView5)

        RetrofitInstance.api.getPokemonId(args.id).enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ){
                if(response.body() != null && response.isSuccessful) {
                    Picasso.get().load(response.body()!!.sprites.front_default).into(front)
                    Picasso.get().load(response.body()!!.sprites.back_default).into(back)
                    Picasso.get().load(response.body()!!.sprites.front_shiny).into(fronts)
                    Picasso.get().load(response.body()!!.sprites.back_shiny).into(backs)
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

}