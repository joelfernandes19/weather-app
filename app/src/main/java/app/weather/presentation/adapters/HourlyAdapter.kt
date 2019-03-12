package app.weather.presentation.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.weather.R
import app.weather.formatTemperature
import app.weather.getTimeInHourMin
import app.weather.presentation.models.ForecastModel
import app.weather.px
import kotlinx.android.synthetic.main.item_hourly.view.*
import java.util.*


class HourlyAdapter(val context : Context, val items : ArrayList<ForecastModel>) : RecyclerView.Adapter<HourlyAdapter.ForecastViewHolder>() {


    override fun getItemCount(): Int {
        return items.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_hourly,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecast = items[holder.adapterPosition]
        holder.tvHour.text = getTimeInHourMin(forecast.dateUTC)

        holder.tvTempAtHour.text = formatTemperature(items[holder.adapterPosition].weatherConditions.temp)

        val drawable = getIconDrawable(holder.tvTempAtHour.context, forecast.weatherInfo[0].icon)
        drawable.colorFilter = PorterDuffColorFilter(Color.parseColor("#95FFFFFF"), PorterDuff.Mode.MULTIPLY)
        holder.imgIcon.setImageDrawable(drawable)


        holder.tvCondition.text = forecast.weatherInfo[0].params

        if(holder.adapterPosition == 0) {
            holder.itemView.setPadding(16.px, 0, 0, 0)
        } else if(holder.adapterPosition == items.size) {
            holder.itemView.setPadding(0, 0, 16.px, 0)
        } else {
            holder.itemView.setPadding(16.px, 0, 16.px, 0)
        }

    }

    class ForecastViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvHour = view.tvHour
        val tvTempAtHour = view.tvTempAtHour
        val imgIcon = view.imgIcon
        val tvCondition = view.tvCondition
    }

    private fun getIconDrawable(context : Context, icon : String) : Drawable {
        if(icon.last() == 'n') {
            return context.getDrawable(
                context.resources.getIdentifier(
                    "vd_ic_".plus(icon.replace('n', 'd')),
                    "drawable",
                    context.packageName
                )
            ) as Drawable

        } else {
            return context.getDrawable(
                context.resources.getIdentifier(
                    "vd_ic_".plus(icon),
                    "drawable",
                    context.packageName
                )
            ) as Drawable

        }
    }
}