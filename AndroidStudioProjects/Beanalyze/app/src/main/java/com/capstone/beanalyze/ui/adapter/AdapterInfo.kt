package com.capstone.beanalyze.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.beanalyze.ui.disease.DiseaseActivity
import com.capstone.beanalyze.R
import com.capstone.beanalyze.databinding.ListDiseaseBinding
import com.capstone.beanalyze.model.Disease

class AdapterInfo(private val diseaseList: List<Disease>) :
    RecyclerView.Adapter<AdapterInfo.DiseaseViewHolder>() {

    inner class DiseaseViewHolder(private val binding: ListDiseaseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(disease: Disease) {
            binding.nameDisease.text = disease.name
            Glide.with(binding.ivDisease.context)
                .load(disease.image_url)
                .placeholder(R.drawable.bean)
                .error(R.drawable.imagetest)
                .into(binding.ivDisease)

            binding.root.setOnClickListener {
                val context = binding.root.context
                val intent = Intent(context, DiseaseActivity::class.java).apply {
                    putExtra("DISEASE_IMAGE", disease.image_url)
                    putExtra("DISEASE_NAME", disease.name)
                    putExtra("DISEASE_IMPACT", disease.impact)
                    putExtra("DISEASE_CAUSE", disease.cause)
                    putExtra("DISEASE_IDENTIFICATION", disease.identification)
                    putExtra("DISEASE_SOLUTION", disease.solution)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseaseViewHolder {
        val binding = ListDiseaseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DiseaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiseaseViewHolder, position: Int) {
        holder.bind(diseaseList[position])
    }

    override fun getItemCount(): Int = diseaseList.size
}
