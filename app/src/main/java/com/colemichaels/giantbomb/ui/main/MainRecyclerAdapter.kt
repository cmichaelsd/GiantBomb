package com.colemichaels.giantbomb.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.colemichaels.giantbomb.R
import com.colemichaels.giantbomb.data.models.Game

class MainRecyclerAdapter(
    val context: Context,
    val gameData: List<Game>,
    val itemListener: GameItemListener
) : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.giantbomb_grid_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = gameData[position]
        with (holder) {
            nameText?.let {
                it.text = game.name
            }
            Glide.with(context)
                .load(game.thumbBoxArt)
                .into(gameBoxArt)
            itemView.setOnClickListener {
                itemListener.onGameItemClick(game)
            }
        }
    }

    override fun getItemCount(): Int = gameData.size

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.nameTextView)
        val gameBoxArt: ImageView = itemView.findViewById(R.id.gameBoxArt)
    }

    interface GameItemListener {
        fun onGameItemClick(gameItem: Game)
    }
}