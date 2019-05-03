package com.shajt3ch.room_03;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button addbtn,viewbtn,deletebtn,updatebtn;

    private EditText userId,userName,userEmail;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        addbtn = view.findViewById(R.id.add_button);
        addbtn.setOnClickListener(this);

        viewbtn = view.findViewById(R.id.view_button);
        viewbtn.setOnClickListener(this);

        deletebtn = view.findViewById(R.id.delete_button);
        deletebtn.setOnClickListener(this);

        updatebtn = view.findViewById(R.id.update_button);
        updatebtn.setOnClickListener(this);


        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.add_button:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new AddUserFragment()).
                        addToBackStack(null).commit();
                break;

            case R.id.view_button:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new ReadUserFragment()).
                        addToBackStack(null).commit();
                break;

            case R.id.delete_button:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new DeleteUserFragment()).
                        addToBackStack(null).commit();
                break;

            case R.id.update_button:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new UpdateFragment()).
                        addToBackStack(null).commit();
                break;
        }
    }
}
