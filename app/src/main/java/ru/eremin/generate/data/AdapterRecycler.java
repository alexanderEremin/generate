package ru.eremin.generate.data;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.Collections;
import java.util.List;

import ru.eremin.generate.R;
import ru.eremin.generate.data.managers.ListManager;
import ru.eremin.generate.interfaces.InterfaceList;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolder> {
    private List<ListManager> listManager;
    private InterfaceList.IClickRecycler clickRecycler;
    public AdapterRecycler(List<ListManager> list, InterfaceList.IClickRecycler clickRecycler){
        this.listManager = list;
        this.clickRecycler = clickRecycler;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MaterialCardView view = (MaterialCardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(listManager.get(position).getmSurname()+" "+listManager.get(position).getmName());
        holder.gender.setText("Пол: "+listManager.get(position).getmGender());
        holder.age.setText("Возраст: "+listManager.get(position).getmAge());
    }

    @Override
    public int getItemCount() {
        return listManager.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, gender, age;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            gender = itemView.findViewById(R.id.gender);
            age = itemView.findViewById(R.id.age);
            itemView.setOnClickListener(v -> {
                clickRecycler.click(Collections.singletonList(listManager.get(getAdapterPosition()))); // Передать id во фрагмент
            });
        }
    }

}
