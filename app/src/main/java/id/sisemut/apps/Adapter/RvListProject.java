package id.sisemut.apps.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import id.sisemut.apps.Activity.DentitionStatusActivity;
import id.sisemut.apps.Activity.project;
import id.sisemut.apps.Model.ProjectSemut;
import id.sisemut.apps.R;

import id.sisemut.apps.Model.ProjectSemut;
import id.sisemut.apps.R;

import static android.content.ContentValues.TAG;

public class RvListProject extends RecyclerView.Adapter<RvListProject.ViewHolder> {

    Context context;
    List<ProjectSemut> projectSemut;
    private FirebaseAuth auth;
    private FirebaseStorage storage;

    public RvListProject(Context context, List<ProjectSemut> projectSemut) {

        this.projectSemut = projectSemut;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_list_project, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        ProjectSemut listProject = projectSemut.get(position);

        Boolean status = listProject.getProject_status();
        Log.d(TAG, "onBindViewHolder: " + status);

        holder.tv_project_title.setText(listProject.getProject_name());
        holder.tv_project_category.setText(listProject.getProject_age_category().toLowerCase());
        holder.cv_project_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_data = new Intent(context, project.class);
                context.startActivity(add_data);
            }
        });

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String owner_email = user.getEmail();
        if (listProject.getProject_member().get(0).equals(owner_email)) {
            holder.tv_project_owner.setText("owner");
        } else {
            holder.tv_project_owner.setText("member");
        }

//        StorageReference gsReference = storage.getInstance().getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/si-semut.appspot.com/o/images%2F" + listProject.getProject_image() + "?alt=media");
        String imgProject = "https://firebasestorage.googleapis.com/v0/b/si-semut.appspot.com/o/images%2F" + listProject.getProject_image() + "?alt=media";
        Glide.with(context).load(imgProject).into(holder.civ_project_photo);

    }

    @Override
    public int getItemCount() {

        return projectSemut.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_project_title, tv_project_category, tv_project_owner;
        public CircleImageView civ_project_photo;
        public CardView cv_project_list;

        public ViewHolder(View itemView) {

            super(itemView);

            tv_project_title = itemView.findViewById(R.id.tv_project_title);
            tv_project_category = itemView.findViewById(R.id.tv_project_category);
            tv_project_owner = itemView.findViewById(R.id.tv_project_owner);
            civ_project_photo = itemView.findViewById(R.id.civ_project_photo);
            cv_project_list = itemView.findViewById(R.id.cv_project_list);

        }
    }
}
