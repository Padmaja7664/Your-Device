package com.example.yourdevice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(val fragment: Fragment, val producArrayList: List<Product>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.eachitem,parent,false)
        // val itemView= View.LayoutInflater(.inflate(R.layout.eachitem,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val currItem=producArrayList[position]
        holder.title.text=currItem.title

        //img is in url so use third party lib picasso

        Picasso.get().load(currItem.thumbnail).into(holder.image);
        holder.brand.text=currItem.brand
        holder.price.text="Price : "+currItem.price.toString()+" $"



    }

    override fun getItemCount(): Int {
        return producArrayList.size
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var title: TextView
        var image: ShapeableImageView
        val brand:TextView
        val price:TextView

        init{
            title=itemView.findViewById(R.id.productname)
            image=itemView.findViewById(R.id.productimg)
            brand=itemView.findViewById(R.id.detailsid)
            price=itemView.findViewById(R.id.priceid)
        }
    }
}