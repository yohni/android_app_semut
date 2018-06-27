package id.sisemut.apps.Adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.sisemut.apps.Model.EthnicGroup;
import id.sisemut.apps.R;

public class RvEthnicGroup extends RecyclerView.Adapter<RvEthnicGroup.EthnicGroupViewHolder>{

    private ArrayList<EthnicGroup> dataEthnic;

    public RvEthnicGroup(ArrayList<EthnicGroup> dataEthnic) {
        this.dataEthnic = dataEthnic;
    }

    @Override
    public EthnicGroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_text_ethnic_group, parent, false);
        return new EthnicGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EthnicGroupViewHolder holder, int position) {
        final int posisi = position;
        holder.tv_ethnic_group_name.setText(dataEthnic.get(position).getEthnic_name());
        holder.iv_ethnic_group_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posisi != 0) {
                    dataEthnic.remove(posisi);
                    notifyItemRemoved(posisi);
                    notifyItemRangeChanged(posisi, dataEthnic.size());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return (dataEthnic != null) ? dataEthnic.size() : 0;
    }

    public class EthnicGroupViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_ethnic_group_name, tv_ethnic_group_width;
        private ImageView iv_ethnic_group_delete;

        public EthnicGroupViewHolder(View itemView) {
            super(itemView);
            tv_ethnic_group_name = itemView.findViewById(R.id.tv_ethnic_group_name);
            iv_ethnic_group_delete = itemView.findViewById(R.id.iv_ethnic_group_delete);
        }
    }

    public List<String> getEthnicList(){
        List<String> list = new ArrayList<>();
        for(EthnicGroup e : dataEthnic){
            list.add(e.getEthnic_name());
        }

        return list;
    }
}
