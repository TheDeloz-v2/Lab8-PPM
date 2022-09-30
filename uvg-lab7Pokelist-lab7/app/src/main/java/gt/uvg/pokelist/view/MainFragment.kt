package gt.uvg.pokelist.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gt.uvg.pokelist.R
import gt.uvg.pokelist.api.RetrofitInstance
import gt.uvg.pokelist.databinding.FragmentMainBinding
import gt.uvg.pokelist.model.Result
import gt.uvg.pokelist.model.PokemonResponseList

import retrofit2.Response
import retrofit2.Callback
import retrofit2.Call



class MainFragment: Fragment() {

    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var pokemonList: List<Result>
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        RetrofitInstance.api.getPokemons().enqueue(object : Callback<PokemonResponseList> {
            override fun onResponse(
                call: Call<PokemonResponseList>,
                response: Response<PokemonResponseList>
            ) {
                if (response.body() != null && response.isSuccessful) {
                    pokemonList = response.body()!!.results
                    recyclerView.layoutManager = LinearLayoutManager(view.context)
                    recyclerView.adapter = PokemonListAdapter(pokemonList)
                    recyclerView.setHasFixedSize(true)
                }
            }

            override fun onFailure(call: Call<PokemonResponseList>, t: Throwable) {
                Log.i("FAILURE", t.message.toString())
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}