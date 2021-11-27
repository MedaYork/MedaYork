package com.darkaxce.medayork
import com.darkaxce.medayork.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.darkaxce.medayork.Poi
import com.squareup.picasso.Picasso


import androidx.annotation.NonNull






class RecyclerPoiAdapter(private val mContext: Context, mGerencia: ArrayList<Poi>?) :
    RecyclerView.Adapter<RecyclerPoiAdapter.MyViewHolder?>(), View.OnClickListener {
    private val mPoi: ArrayList<Poi>
    private var listener: View.OnClickListener? = null
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {

        val view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, null, false)
        view.setOnClickListener(this)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        myViewHolder.txtNombre.setText(mPoi[i].name)
        myViewHolder.txtRecaudo.setText(mPoi[i].description_short)
        myViewHolder.rating.setRating(mPoi[i].rating)
        Picasso.get().load(mPoi[i].img)
            .resize(300, 300) // resizes the image to these dimensions (in pixel). does not respect aspect ratio
            .into(myViewHolder.image);
    }

    override fun getItemCount(): Int {
        return mPoi.size
    }

    fun setOnClickListener(listener: View.OnClickListener?) {
        this.listener = listener
    }

    override fun onClick(view: View) {
        if (listener != null) {
            listener!!.onClick(view)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtNombre: TextView
        var txtRecaudo: TextView
        var image: ImageView
        var rating : RatingBar

        init {
            txtNombre = itemView.findViewById(R.id.idNombre)
            txtRecaudo = itemView.findViewById(R.id.idInfo)
            image = itemView.findViewById(R.id.idImagen)
            rating = itemView.findViewById(R.id.ratingBar)
        }
    }

    init {
        this.mPoi = mGerencia as ArrayList<Poi>
    }
}