package com.colemichaels.giantbomb.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.colemichaels.giantbomb.data.models.Game
import com.colemichaels.giantbomb.databinding.GiantbombGridItemBinding

class MainRecyclerAdapter(
    private val context: Context,
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
        val game = gameData[position]

        holder.nameText.text = game.name

        Glide.with(context)
            .load(game.thumbBoxArt)
            .into(holder.gameBoxArt)

        holder.itemView.setOnClickListener {
            itemListener.onGameItemClick(game)
        }
    }

    override fun getItemCount(): Int = gameData.size

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = binding.nameTextView

        val gameBoxArt: ImageView = binding.gameBoxArt
    }

    interface GameItemListener {
        fun onGameItemClick(gameItem: Game)
    }
}