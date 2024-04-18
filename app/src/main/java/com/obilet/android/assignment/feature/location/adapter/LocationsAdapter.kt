package com.obilet.android.assignment.feature.location.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.obilet.android.assignment.R
import com.obilet.android.assignment.core.model.bus_location.BusLocation
import com.obilet.android.assignment.databinding.ItemLocationBinding
import com.obilet.android.assignment.feature.location.listener.LocationItemClickListener
import com.obilet.android.assignment.feature.location.listener.OnLocationItemSeeOnTheMapClickListener
import com.obilet.android.assignment.feature.location.model.LocationDirection

class LocationsAdapter : ListAdapter<BusLocation, LocationsAdapter.LocationsViewHolder>(diffUtil) {

    private lateinit var locationItemClickListener: LocationItemClickListener

    private lateinit var onLocationItemSeeOnTheMapClickListener: OnLocationItemSeeOnTheMapClickListener

    private var direction: LocationDirection = LocationDirection.ORIGIN

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<BusLocation>() {
            override fun areItemsTheSame(oldItem: BusLocation, newItem: BusLocation): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: BusLocation, newItem: BusLocation): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun setListener(listener: LocationItemClickListener) {
        locationItemClickListener = listener
    }

    fun setSeeOnTheMapClickListener(listener: OnLocationItemSeeOnTheMapClickListener) {
        onLocationItemSeeOnTheMapClickListener = listener
    }

    fun setDirection(direction: LocationDirection) {
        this.direction = direction
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        return LocationsViewHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.binding.root.startAnimation(
                android.view.animation.AnimationUtils.loadAnimation(
                    holder.binding.root.context,
                    R.anim.recyclerview_item_slide_in_left_animation
                )
            )
            holder.bind(it)
        }
    }

    inner class LocationsViewHolder(val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                checkThePositionThenPerformTheAction { location ->
                    locationItemClickListener.onLocationItemClick(direction, location)
                }
            }

            binding.seeOnTheMapBtn.setOnClickListener {
                checkThePositionThenPerformTheAction { location ->
                    onLocationItemSeeOnTheMapClickListener.onSeeOnTheMapClicked(location)
                }
            }
        }

        fun bind(item: BusLocation) {
            item.name?.let {
                binding.locationTv.text = it
            }
        }

        private fun checkThePositionThenPerformTheAction(action: (BusLocation) -> Unit) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val item = getItem(position)
                item?.let {
                    action(it)
                }
            }
        }
    }
}