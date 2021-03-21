package com.colemichaels.giantbomb.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.colemichaels.giantbomb.data.models.Game
import com.colemichaels.giantbomb.databinding.GiantbombGridItemBinding

class MainRecyclerAdapter(
    private val gameData: List<Game>,
    private val itemListener: GameItemListener
) : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {

    private lateinit var binding: GiantbombGridItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        binding = GiantbombGridItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.game = gameData[position]

        holder.itemView.setOnClickListener {
            itemListener.onGameItemClick(gameData[position])
        }
    }

    override fun getItemCount(): Int = gameData.size

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
    }

    interface GameItemListener {
        fun onGameItemClick(gameItem: Game)
    }
}