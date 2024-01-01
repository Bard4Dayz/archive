package com.example.tabulator.ui.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tabulator.R;
import com.google.android.material.textfield.TextInputEditText;

public class CreateTabFragment extends Fragment {

    public CreateTabFragment(){ super(R.layout.create_tab_layout);}
    private boolean previouslySaving = false;

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        MainViewModel viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);

        viewModel.getCurrentTab().observe(getViewLifecycleOwner(), (entry) -> {
            if(entry != null){
                TextInputEditText nameEdit = view.findViewById(R.id.nameEditText);
                TextInputEditText notesEdit = view.findViewById(R.id.notesEditText);
                nameEdit.setText(entry.name);
                notesEdit.setText(entry.notes);
            }
        });

        viewModel.getSaving().observe(getViewLifecycleOwner(), (saving) -> {
            if(saving && !previouslySaving){
                AppCompatButton button = view.findViewById(R.id.createButton);
                button.setEnabled(false);
                button.setText("Creating...");
                previouslySaving = saving;
            }
            else if (previouslySaving && !saving){
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        view.findViewById(R.id.createButton).setOnClickListener(createButton -> {
            TextInputEditText nameEditText = view.findViewById(R.id.nameEditText);
            TextInputEditText noteEditText = view.findViewById(R.id.notesEditText);
            viewModel.createTab(nameEditText.getText().toString(), noteEditText.getText().toString());
        });

    }

}
