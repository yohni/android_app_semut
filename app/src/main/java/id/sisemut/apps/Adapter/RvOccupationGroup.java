package id.sisemut.apps.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.sisemut.apps.Model.OccupationGroup;
import id.sisemut.apps.R;

public class RvOccupationGroup extends RecyclerView.Adapter<RvOccupationGroup.OccupationGroupViewHolder>{

    private ArrayList<OccupationGroup> dataOccupation;

    public RvOccupationGroup(ArrayList<OccupationGroup> dataOccupation) {
        this.dataOccupation = dataOccupation;
    }

    @Override
    public RvOccupationGroup.OccupationGroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_text_occupation_group, parent, false);
        return new OccupationGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OccupationGroupViewHolder holder, int position) {
        final int posisi = position;
        holder.tv_occupation_group_name.setText(dataOccupation.get(position).getOccupation_Name());
        holder.iv_occupation_group_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posisi != 0) {
                    dataOccupation.remove(posisi);
                    notifyItemRemoved(posisi);
                    notifyItemRangeChanged(posisi, dataOccupation.size());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return (dataOccupation != null) ? dataOccupation.size() : 0;
    }

    public class OccupationGroupViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_occupation_group_name;
        private ImageView iv_occupation_group_delete;

        public OccupationGroupViewHolder(View itemView) {
            super(itemView);
            tv_occupation_group_name = itemView.findViewById(R.id.tv_occupation_group_name);
            iv_occupation_group_delete = itemView.findViewById(R.id.iv_occupation_group_delete);
        }
    }

    public List<String> getOccupationList() {
        List<String> list = new ArrayList<>();
        for(OccupationGroup o : dataOccupation) {
            list.add(o.getOccupation_Name());
        }

        return list;
    }
}
