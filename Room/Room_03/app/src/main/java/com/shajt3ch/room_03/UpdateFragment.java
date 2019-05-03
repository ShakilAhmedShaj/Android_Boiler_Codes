package com.shajt3ch.room_03;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment {

    private EditText textId,textName, textEmail, textSearch;
    private Button updateBtn,searchBtn;


    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_update, container, false);
        textId = view.findViewById(R.id.id_editText);
        textName = view.findViewById(R.id.name_editText);
        textEmail = view.findViewById(R.id.email_editText);

        textSearch = view.findViewById(R.id.id_search_editText);

        updateBtn = view.findViewById(R.id.update_button);
        searchBtn = view.findViewById(R.id.search_button);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(textId.getText().toString());
                String name =textName.getText().toString();
                String email =textEmail.getText().toString();

                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setEmail(email);

                MainActivity.myAppDatabase.myDao().updateUsers(user);

                Toast.makeText(getActivity(),"Successfully Updated",Toast.LENGTH_LONG).show();

            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(textSearch.getText().toString());

               List<User> users= MainActivity.myAppDatabase.myDao().findUsersByName(id);
               for (User usr:users)
               {
                   //.toString not working
                    //textId.setText(Integer.toString(usr.getId()));  this is also OK
                   textId.setText(String.valueOf(usr.getId()));
                    textName.setText(usr.getName());
                   textEmail.setText(usr.getEmail());
               }
                Toast.makeText(getActivity(),"Search results available",Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}
