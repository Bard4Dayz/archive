package com.example.tabulator.ui.main;


import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.tabulator.R;

import java.util.ArrayList;

public class TabFragment extends Fragment {

    public TabFragment(){
        super(R.layout.tab_fragment_layout);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        MainViewModel viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        int[] notesList = {R.drawable.a, R.drawable.a1, R.drawable.b, R.drawable.c,
                R.drawable.c1, R.drawable.d, R.drawable.d1, R.drawable.e, R.drawable.f,
                R.drawable.f1, R.drawable.g, R.drawable.g_, R.drawable.a_, R.drawable.a__,
                R.drawable.b_, R.drawable.c_, R.drawable.c__, R.drawable.d_, R.drawable.d__,
                R.drawable.e_, R.drawable.f_};
        ArrayList<Integer> finalNotes = new ArrayList<>();
        viewModel.getCurrentTab().observe(getViewLifecycleOwner(), (entry) -> {
            if (entry == null){
                getActivity().getSupportFragmentManager().popBackStack();
            }
            else{
                GridLayout grid = view.findViewById(R.id.tab_grid);
                String noteList = entry.notes;
                String[] noteArray = noteList.split(" ");
                for(int i = 0; i < noteArray.length; i ++){
                    int startRef = -1;
                    if(noteArray[i].charAt(noteArray[i].length()-1) == '^'){
                        startRef = 11;
                    }
                    if (noteArray[i].charAt(0) == 'A'){
                        startRef += 1;
                    }
                    else if(noteArray[i].charAt(0) == 'B'){
                        startRef += 3;
                    } else if(noteArray[i].charAt(0) == 'C'){
                        startRef += 4;
                    } else if(noteArray[i].charAt(0) == 'D'){
                        startRef += 6;
                    } else if(noteArray[i].charAt(0) == 'E'){
                        startRef += 8;
                    } else if(noteArray[i].charAt(0) == 'F'){
                        startRef += 9;
                    } else if(noteArray[i].charAt(0) == 'G'){
                        startRef += 11;
                    }
                    if(noteArray[i].length() > 1){
                        if(noteArray[i].charAt(1) == '#'){
                            startRef += 1;
                        } else if(noteArray[i].charAt(1) == 'b'){
                            startRef -= 1;
                        }
                    }
                    if(startRef >= 0 && startRef < notesList.length){
                        finalNotes.add(notesList[startRef]);
                    }
                }

                for(int i = 0; i < 6; i ++){
                    for (int j = 0; j < 4; j ++){
                        int coordinate = (i*4) + j;
                        ImageView image;
                        switch (coordinate){
                            case 0: image = view.findViewById(R.id.g0_0);
                                break;
                            case 1: image = view.findViewById(R.id.g0_1);
                                break;
                            case 2: image = view.findViewById(R.id.g0_2);
                                break;
                            case 3: image = view.findViewById(R.id.g0_3);
                                break;
                            case 4: image = view.findViewById(R.id.g1_0);
                                break;
                            case 5: image = view.findViewById(R.id.g1_1);
                                break;
                            case 6: image = view.findViewById(R.id.g1_2);
                                break;
                            case 7: image = view.findViewById(R.id.g1_3);
                                break;
                            case 8: image = view.findViewById(R.id.g2_0);
                                break;
                            case 9: image = view.findViewById(R.id.g2_1);
                                break;
                            case 10: image = view.findViewById(R.id.g2_2);
                                break;
                            case 11: image = view.findViewById(R.id.g2_3);
                                break;
                            case 12: image = view.findViewById(R.id.g3_0);
                                break;
                            case 13: image = view.findViewById(R.id.g3_1);
                                break;
                            case 14: image = view.findViewById(R.id.g3_2);
                                break;
                            case 15: image = view.findViewById(R.id.g3_3);
                                break;
                            case 16: image = view.findViewById(R.id.g4_0);
                                break;
                            case 17: image = view.findViewById(R.id.g4_1);
                                break;
                            case 18: image = view.findViewById(R.id.g4_2);
                                break;
                            case 19: image = view.findViewById(R.id.g4_3);
                                break;
                            case 20: image = view.findViewById(R.id.g5_0);
                                break;
                            case 21: image = view.findViewById(R.id.g5_1);
                                break;
                            case 22: image = view.findViewById(R.id.g5_2);
                                break;
                            default: image = view.findViewById(R.id.g5_3);
                                break;
                        }
                        if(!finalNotes.isEmpty()){
                            image.setImageResource(finalNotes.remove(0));
                            image.setVisibility(View.VISIBLE);
                        }

                    }
                }
            }
        });

        view.findViewById(R.id.delete_button).setOnClickListener((button) -> {
            viewModel.deleteCurrentEntry();
        });

        view.findViewById(R.id.edit_button).setOnClickListener(editButton -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.menu_fragment_container, CreateTabFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        });
    }

}
