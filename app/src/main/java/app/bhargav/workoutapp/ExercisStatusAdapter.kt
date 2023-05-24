package app.bhargav.workoutapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import app.bhargav.workoutapp.databinding.ItemExerciseStatusBinding



/* This is a recycler view adapter */
// creating the items paramter with an array list of exercise model
class ExercisStatusAdapter(val items : ArrayList<ExerciseModel>): RecyclerView.Adapter<ExercisStatusAdapter.ViewHolder>() {

    // creating own view holder class
     class ViewHolder(binding :ItemExerciseStatusBinding):RecyclerView.ViewHolder(binding.root){
        val tvItem  = binding.tvItem   // accessing the text view item in
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model : ExerciseModel = items[position]
        holder.tvItem.text = model.getId().toString()

        when{
            model.getIsSelected()-> {
                holder.tvItem.background= ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_circular_thin_color_accent_border)

                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
                 model.getIsCompleted()->{
                     holder.tvItem.background= ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_cicular_color_accent_background)

                     holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))

            }else->{
            holder.tvItem.background= ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_circular_color_grey_bckground)

            holder.tvItem.setTextColor(Color.parseColor("#212121"))



        }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}