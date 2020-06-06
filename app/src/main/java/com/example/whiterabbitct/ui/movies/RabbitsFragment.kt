package com.example.whiterabbitct.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.movies_fragment.*
import com.example.whiterabbitct.R
import com.example.whiterabbitct.data.models.Rabbit
import com.example.whiterabbitct.data.network.RabbitApi
import com.example.whiterabbitct.repositories.RabbitRepository


class RabbitsFragment : Fragment(), RecyclerViewClickListener{

    private lateinit var factory: RabbitsViewModelFactory
    private lateinit var viewModel: RabbitsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = RabbitApi()
        val repository = RabbitRepository(api)

        factory = RabbitsViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(RabbitsViewModel::class.java)

        viewModel.getRabbits()

        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            recycler_view_movies.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = RabbitsAdapter(movies, this)
            }
        })
    }

    override fun onRecyclerViewItemClick(view: View, rabbit: Rabbit) {
        when(view.id){
            R.id.button_book -> {
                Toast.makeText(requireContext(), "Book Button Clicked",Toast.LENGTH_LONG).show()
                //showFragmentSetup()
            }
        }
    }
}
