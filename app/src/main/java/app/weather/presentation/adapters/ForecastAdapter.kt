package app.weather.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.weather.R
import app.weather.presentation.models.ForecastModel
import kotlinx.android.synthetic.main.item_forecast.view.*

class ForecastAdapter(val context : Context, val items : ArrayList<List<ForecastModel>>) : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {


    override fun getItemCount(): Int {
        return items.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_forecast,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.tvDay.text = items[holder.adapterPosition][0].dateFormatted

        val forecast = items[holder.adapterPosition]

        /**
         * This adapter contains nested RecyclerView to show the hourly forecast for each day
         */
        holder.hourlyAdapter =
            HourlyAdapter(holder.rvHourly.context, ArrayList(forecast))
        holder.rvHourly.adapter = holder.hourlyAdapter
    }

    class ForecastViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvDay = view.tvDay
        val rvHourly = view.rvHourly
        lateinit var hourlyAdapter : HourlyAdapter
    }
}