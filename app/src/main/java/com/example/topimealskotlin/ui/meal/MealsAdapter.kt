package com.example.topimealskotlin.ui.meal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.topimealskotlin.ui.ingredients.IngredientsActivity
import com.example.topimealskotlin.R
import com.example.topimealskotlin.model.meal.Meal
import dagger.BindsInstance
import java.util.*
import javax.inject.Inject


class MealsAdapter @Inject constructor(
        private val requestOptions: RequestOptions,
        private val glideInstance: RequestManager) : RecyclerView.Adapter<MealsAdapter.ViewHolder>() {

    private var mealsListFull = emptyList<Meal>()
    private var mealsList = emptyList<Meal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_meal, parent, false)

        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = mealsList[position]

        holder.strMeal.text = meal.strMeal
        holder.strArea.text = meal.strArea

        holder.itemView.setOnClickListener{
            IngredientsActivity.startActivity(meal, holder.itemView.context)
        }


        glideInstance.load(meal.strMealThumb).centerCrop()
            .apply(requestOptions).into(holder.strMealThumb)

    }

    override fun getItemCount(): Int {
        return this.mealsList.size
    }

    fun updateList(mealsList: List<Meal>){
        this.mealsList = mealsList
        this.mealsListFull = mealsList
        notifyDataSetChanged()
    }

    fun getFilter(): Filter {

        val filter : Filter

        filter = object : Filter(){
            override fun performFiltering(filter: CharSequence): FilterResults {
                var filter: CharSequence = filter
                val results = FilterResults()
                if (filter.length == 0) {
                    results.count = mealsListFull.size
                    results.values = mealsListFull
                } else {
                    val filteredItems: MutableList<Meal> = ArrayList<Meal>()
                    for (i in mealsListFull.indices) {
                        val data: Meal = mealsListFull.get(i)
                        filter = filter.toString().toLowerCase(Locale.ROOT)
                        val name = data.strMeal.toLowerCase(Locale.ROOT)
                        val area = data.strArea.toLowerCase(Locale.ROOT)
                        if (name.contains(filter) || area.contains(filter)) {
                            filteredItems.add(data)
                        }
                    }
                    results.count = filteredItems.size
                    results.values = filteredItems
                }
                return results
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                mealsList = results.values as List<Meal>
                notifyDataSetChanged()
            }

        }
        return filter
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val strMeal : TextView = itemView.findViewById(R.id.strMeal)
        val strArea : TextView = itemView.findViewById(R.id.strArea)
        val strMealThumb : ImageView = itemView.findViewById(R.id.strMealThumb)
    }

}
