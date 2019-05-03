package com.shajt3ch.room_03;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteUserFragment extends Fragment {

    private EditText editText;
    private Button button;


    public DeleteUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_delete_user, container, false);
        editText=view.findViewById(R.id.delete_editText);
        button=view.findViewById(R.id.delete_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(editText.getText().toString());
                User user = new User();
                user.setId(id);
                MainActivity.myAppDatabase.myDao().deletUsers(user);

                Toast.makeText(getActivity(),"User Successfully removed",Toast.LENGTH_LONG).show();
                editText.setText("");

            }
        });
        return view;
    }

}
