package ru.eremin.generate.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.eremin.generate.R;

public class UserFragment extends Fragment {
    private TextView name, jobplace, gender, area, avto, age;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        UserViewModel model = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        model.setDataFromBundle(getArguments());
        name = view.findViewById(R.id.name);
        jobplace = view.findViewById(R.id.jobplace);
        gender = view.findViewById(R.id.gender);
        area = view.findViewById(R.id.area);
        avto = view.findViewById(R.id.avto);
        age = view.findViewById(R.id.age);

        model.getName().observe(getViewLifecycleOwner(), data -> name.setText(data));
        model.getJobplace().observe(getViewLifecycleOwner(), data -> jobplace.setText(data));
        model.getGender().observe(getViewLifecycleOwner(), data -> gender.setText(data));
        model.getArea().observe(getViewLifecycleOwner(), data -> area.setText(data));
        model.getAvto().observe(getViewLifecycleOwner(), data -> avto.setText(data));
        model.getAge().observe(getViewLifecycleOwner(), data -> age.setText(data));
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


}
