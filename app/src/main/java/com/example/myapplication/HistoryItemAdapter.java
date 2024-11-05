package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.model.HistoryItem;
import java.util.List;

public class HistoryItemAdapter extends RecyclerView.Adapter<HistoryItemAdapter.ItemViewHolder> {

    private List<HistoryItem> itemList;

    public HistoryItemAdapter(List<HistoryItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item_layout, parent, false);
        return new ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        HistoryItem item = itemList.get(position);
        holder.itemTitle1.setText(item.getTitle1());
        holder.itemTitle2.setText(item.getTitle2());
        holder.itemKhuvuc.setText(item.getKhuvuc());
        holder.itemToado.setText(item.getToado());
        holder.itemSize.setText(item.getSize());
        holder.itemFindBy.setText(item.getFindby());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle1;
        TextView itemTitle2;
        TextView itemKhuvuc;
        TextView itemToado;
        TextView itemSize;
        TextView itemFindBy;

        ItemViewHolder(View itemView) {
            super(itemView);
            itemTitle1 = itemView.findViewById(R.id.itemTitle1);
            itemTitle2 = itemView.findViewById(R.id.itemTitle2);
            itemKhuvuc = itemView.findViewById(R.id.khu_vuc_data);
            itemToado = itemView.findViewById(R.id.vi_tri_data);
            itemSize = itemView.findViewById(R.id.size_data);
            itemFindBy = itemView.findViewById(R.id.find_by_data);
        }
    }
}
