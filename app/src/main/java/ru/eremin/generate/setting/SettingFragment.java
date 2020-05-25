package ru.eremin.generate.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import ru.eremin.generate.R;

public class SettingFragment extends Fragment {
    private SettingViewModel mSettingViewModel;
    private TextInputEditText mGenerate;
    private TextInputEditText mAgeFrom;
    private TextInputEditText mAgeBefore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mSettingViewModel = new ViewModelProvider(this).get(SettingViewModel.class);
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        mGenerate = view.findViewById(R.id.generator_data);
        mAgeFrom = view.findViewById(R.id.age_from_data);
        mAgeBefore = view.findViewById(R.id.age_before_data);
        mSettingViewModel.getGeneration().observe(getViewLifecycleOwner(), data -> mGenerate.setText(String.valueOf(data)));
        mSettingViewModel.getAge_from().observe(getViewLifecycleOwner(), data -> mAgeFrom.setText(String.valueOf(data)));
        mSettingViewModel.getAge_before().observe(getViewLifecycleOwner(), data -> mAgeBefore.setText(String.valueOf(data)));
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onPause() {
        super.onPause();
        mSettingViewModel.dataFromEditText(Objects.requireNonNull(mGenerate.getText()).toString(),
                                           Objects.requireNonNull(mAgeFrom.getText()).toString(),
                                           Objects.requireNonNull(mAgeBefore.getText()).toString());
    }
}
