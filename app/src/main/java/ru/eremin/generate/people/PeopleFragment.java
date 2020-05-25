package ru.eremin.generate.people;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import ru.eremin.generate.R;
import ru.eremin.generate.data.AdapterRecycler;
import ru.eremin.generate.data.managers.ListManager;
import ru.eremin.generate.interfaces.InterfaceList;

/**
 * TODO Обновление должно грузить новые данные, данные могут не изменить с прошлой загрузки
 */

public class PeopleFragment extends Fragment implements InterfaceList.IClickRecycler, SwipeRefreshLayout.OnRefreshListener {
    private PeopleViewModel model;
    private TextView textView;
    private MaterialButton materialButton;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private SwipeRefreshLayout swipe;
    private Activity activity;
    private List<ListManager> data = new ArrayList<>();

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        model = new ViewModelProvider(requireActivity()).get(PeopleViewModel.class);
        View view = inflater.inflate(R.layout.fragment_people, container, false);
        activity.setProgressBarVisibility(false);
        recyclerView = view.findViewById(R.id.view_list_people);
        recyclerView.setNestedScrollingEnabled(false);
        textView = view.findViewById(R.id.view_message);
        materialButton = view.findViewById(R.id.get_data);
        swipe = view.findViewById(R.id.swipe);
        swipe.setOnRefreshListener(this);
        model.getStatusProgress().observe(getViewLifecycleOwner(), data -> {
            if(data) {
                textView.setVisibility(View.GONE);
                materialButton.setVisibility(View.GONE);
                swipe.setVisibility(View.VISIBLE);
            }
            swipe.setRefreshing(data);
        });
        model.getListData().observe(getViewLifecycleOwner(), this::insertDataRecycler);
        view.findViewById(R.id.get_data).setOnClickListener(getData -> model.getGetData());
        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    /**
     * Наполнение|перезаполнение Recycler
     * Tc
     * @param data данные для наполения
     */
    private void insertDataRecycler(List<ListManager> data){
        if(!this.data.isEmpty()){
            this.data.clear();
            adapter.notifyDataSetChanged();
        }
        this.data = data;
        if(!data.isEmpty()){
            RecyclerView.LayoutManager layoutManagerRecycler = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManagerRecycler);
            adapter = new AdapterRecycler(this.data, this);
            recyclerView.setAdapter(adapter);
        }
    }
    /**
     * Переход к детальному просмотру даных пользователя - UserFragment
     * @param id Данные пользователя
     */
    @Override
    public void click(List<ListManager> id) {
        PeopleFragmentDirections.Action_to_UserFragment data = PeopleFragmentDirections.action_to_UserFragment();
        data.setId(id.get(0).getmId()).
                setSurname(id.get(0).getmSurname()).
                setName(id.get(0).getmName()).
                setJobplace(id.get(0).getmJobplace()).
                setGender(id.get(0).getmGender()).
                setArea(id.get(0).getmArea()).
                setAvto(id.get(0).getmAvto()).
                setAge(id.get(0).getmAge());
        Navigation.findNavController(activity, R.id.nav_host_fragment).navigate(data);
    }
    /**
     * Обновление списка пользователей
     */
    @Override
    public void onRefresh() {
        model.getGetData();
    }
}
