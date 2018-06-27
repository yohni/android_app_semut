package id.sisemut.apps.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.ButterKnife;
import id.sisemut.apps.Adapter.RvEthnicGroup;
import id.sisemut.apps.Adapter.RvListProject;
import id.sisemut.apps.Adapter.RvOccupationGroup;
import id.sisemut.apps.Model.EthnicGroup;
import id.sisemut.apps.Model.MemberGroup;
import id.sisemut.apps.Model.OccupationGroup;
import id.sisemut.apps.Model.ProjectSemut;
import id.sisemut.apps.R;

public class FragmentOnGoing extends Fragment{

    private View view;
    private FloatingActionButton fab_create_project;
    private Button btn_create_project_cancel, btn_create_project_ethnic, btn_create_project_occupation, btn_create_project_create;
    private EditText et_create_project_name, et_create_project_ethnic, et_create_project_occupation;
    private ImageView iv_create_project_photo;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private ProgressDialog progressDialogListProject;
    private RecyclerView rv_create_project_ethnic, rv_create_project_occupation, rv_list_project;
    private RvEthnicGroup adapter_ethnic;
    private RvOccupationGroup adapter_occupation;
    private RecyclerView.Adapter adapterListProject;
    private ArrayList<EthnicGroup> ethnicGroupArrayList;
    private ArrayList<OccupationGroup> occupationGroupArrayList;
    private ArrayList<MemberGroup> memberGroupArrayList;
    private List<ProjectSemut> list = new ArrayList<>();

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseAuth auth;

    private String projectId;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;

    private String projectImageLocation;

    ConstraintLayout layoutBottomSheet;
    BottomSheetBehavior sheetBehavior;

    public FragmentOnGoing() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ongoing, container, false);
        ButterKnife.bind(getActivity());

        fab_create_project = view.findViewById(R.id.fab_create_project);

        btn_create_project_create = view.findViewById(R.id.btn_create_project_create);
        btn_create_project_cancel = view.findViewById(R.id.btn_create_project_cancel);
        btn_create_project_ethnic = view.findViewById(R.id.btn_create_project_ethnic);
        btn_create_project_occupation = view.findViewById(R.id.btn_create_project_occupation);

        iv_create_project_photo = view.findViewById(R.id.iv_create_project_photo);

        et_create_project_name = view.findViewById(R.id.et_create_project_name);
        et_create_project_ethnic = view.findViewById(R.id.et_create_project_ethnic);
        et_create_project_occupation = view.findViewById(R.id.et_create_project_occupation);

        radioGroup = view.findViewById(R.id.radioGroup);

        layoutBottomSheet = view.findViewById(R.id.layout_bottomsheet_create_project);

        rv_list_project = view.findViewById(R.id.rv_list_project);
        rv_list_project.setHasFixedSize(true);
        rv_list_project.setLayoutManager(new LinearLayoutManager(getActivity()));
        progressDialogListProject = new ProgressDialog(getActivity());
        progressDialogListProject.setMessage("Loading Data...");
        progressDialogListProject.show();

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("project_semut");

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                list.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    ProjectSemut listProject = dataSnapshot.getValue(ProjectSemut.class);

                    list.add(listProject);
                }

                adapterListProject = new RvListProject(getActivity(), list);
                rv_list_project.setAdapter(adapterListProject);

                progressDialogListProject.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialogListProject.dismiss();

            }
        });

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);

        fab_create_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        // Adapter for Ethnic Group
        rv_create_project_ethnic = view.findViewById(R.id.rv_create_project_ethnic);
        ethnicGroupArrayList = new ArrayList<>();
        adapter_ethnic = new RvEthnicGroup(ethnicGroupArrayList);
        RecyclerView.LayoutManager layoutManagerEthnic = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rv_create_project_ethnic.setLayoutManager(layoutManagerEthnic);
        rv_create_project_ethnic.setItemAnimator(new DefaultItemAnimator());

        defaultValueEthnics();

        btn_create_project_ethnic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ethinc_name = et_create_project_ethnic.getText().toString().trim().toLowerCase();
                if(!ethinc_name.matches("")) {
                    et_create_project_ethnic.setText("");
                    ethnicGroupArrayList.add(new EthnicGroup(ethinc_name));
                    rv_create_project_ethnic.setAdapter(adapter_ethnic);
                }
            }
        });

        // Adapter for Occupation Group
        rv_create_project_occupation = view.findViewById(R.id.rv_create_project_occupation);
        occupationGroupArrayList = new ArrayList<>();
        adapter_occupation = new RvOccupationGroup(occupationGroupArrayList);
        RecyclerView.LayoutManager layoutManagerOccupation = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rv_create_project_occupation.setLayoutManager(layoutManagerOccupation);
        rv_create_project_occupation.setItemAnimator(new DefaultItemAnimator());

        defaultValueOccupation();

        btn_create_project_occupation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String occupation_name = et_create_project_occupation.getText().toString().trim().toLowerCase();
                if(!occupation_name.matches("")) {
                    et_create_project_occupation.setText("");
                    occupationGroupArrayList.add(new OccupationGroup(occupation_name));
                    rv_create_project_occupation.setAdapter(adapter_occupation);
                }
            }
        });

        iv_create_project_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 chooseImage();
            }
        });

        btn_create_project_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        btn_create_project_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = view.findViewById(selectedId);

                auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                String owner_email = user.getEmail();
                memberGroupArrayList = new ArrayList<>();
                memberGroupArrayList.add(new MemberGroup(owner_email));

                String project_name = et_create_project_name.getText().toString();
                List<String> project_ethnic = adapter_ethnic.getEthnicList();
                List<String> project_occupation = adapter_occupation.getOccupationList();
                List<String> project_member = getMemberList();
                Boolean project_status = true;
                String project_age_category = radioButton.getText().toString();

                // Check for already existed userId
                if (TextUtils.isEmpty(projectId)) {
                    uploadImage();
                    createProject(project_name, project_status, project_ethnic, project_occupation, project_age_category, projectImageLocation, project_member);
                } else {
//                    updateUser(project_name, project_age_category);
                }

            }
        });

        return view;
    }

    public void createProject(String project_name, Boolean project_status, List<String> project_ethnic, List<String> project_occupation, String project_age_category, String projectImageLocation, List<String> project_member) {
        if (TextUtils.isEmpty(projectId)) {
            projectId = mFirebaseDatabase.push().getKey();
        }

        ProjectSemut new_project = new ProjectSemut(project_name, project_status, project_ethnic, project_occupation, project_age_category, projectImageLocation, project_member);

        mFirebaseDatabase.child(projectId).setValue(new_project);

    }

    public List<String> getMemberList() {
        List<String> list = new ArrayList<>();
        for(MemberGroup o : memberGroupArrayList) {
            list.add(o.getMember_name());
//            list.set(0, o.getMember_name());
        }

        return list;
    }

    public void defaultValueEthnics() {
        // For Ethnics
        ethnicGroupArrayList.add(new EthnicGroup("other"));
        rv_create_project_ethnic.setAdapter(adapter_ethnic);
    }

    public void defaultValueOccupation() {
        // For Occupations
        occupationGroupArrayList.add(new OccupationGroup("other"));
        rv_create_project_occupation.setAdapter(adapter_occupation);
    }

    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                iv_create_project_photo.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            projectImageLocation = UUID.randomUUID().toString();
            String fileLocation = "images/" + projectImageLocation;
            StorageReference ref = storageReference.child(fileLocation);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Successed to add the Project", Toast.LENGTH_SHORT).show();
                            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Failed to add the Project "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }


}
