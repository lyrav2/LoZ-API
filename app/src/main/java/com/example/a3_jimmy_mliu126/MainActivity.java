package com.example.a3_jimmy_mliu126;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.a3_jimmy_mliu126.adapters.ItemAdapter;
import com.example.a3_jimmy_mliu126.databinding.ActivityMainBinding;
import com.example.a3_jimmy_mliu126.models.Equipment;
import com.example.a3_jimmy_mliu126.models.EquipmentList;
import com.example.a3_jimmy_mliu126.network.API;
import com.example.a3_jimmy_mliu126.network.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    // recyclerview
    private ItemAdapter adapter;
    private static ArrayList<Equipment> equipmentList = new ArrayList<Equipment>();
    private static ArrayList<String> locationList = new ArrayList<String>();

    // API
    private API api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // get reference to API
        this.api = RetrofitClient.getInstance().getAPI();

        // network request
        Call<EquipmentList> request = this.api.getEquipmentList();

        request.enqueue(new Callback<EquipmentList>() {
            @Override
            public void onResponse(Call<EquipmentList> call, Response<EquipmentList> response) {
                EquipmentList equipmentFromAPI = response.body();

                // set data source to list
                locationList.add("Choose a location");
                equipmentList.clear();
                for (int i = 0; i < equipmentFromAPI.getEquipments().size(); i++) {
                    equipmentList.add(equipmentFromAPI.getEquipments().get(i));

                    // get locations
                    if (equipmentList.get(i).getCommon_locations() != null) {
                        for (int j = 0; j < equipmentList.get(i).getCommon_locations().size(); j++) {
                            if (!locationList.contains(equipmentList.get(i).getCommon_locations().get(j))) {
                                locationList.add(equipmentList.get(i).getCommon_locations().get(j));
                            }
                        }
                    };
                }

                // populate spinner
                ArrayAdapter<String> locationsAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, locationList);
                binding.spLocation.setAdapter(locationsAdapter);

                binding.spLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position != 0) {
                            searchByLocation(parent.getItemAtPosition(position).toString());
                        } else {
                            // if choose a location is selected, clear recycler
                            clearRecycle();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        clearRecycle();
                    }
                });
            }

            @Override
            public void onFailure(Call<EquipmentList> call, Throwable t) {
                Log.d("LOGERR", t.getMessage()); // log for developer, can remove if we want!
            }
        });

        // search button implementation
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // empty =
                if (isEmpty(binding.etSearch)) {
                    clearRecycle();
                } else {
                    searchByKeyword(binding.etSearch.getText().toString());
                }
            }
        });
    }

    // search by location
    private void searchByLocation(String location) {
        // list to display
        ArrayList<Equipment> displayList = new ArrayList<Equipment>();

        // populate list
        for (int i = 0; i < equipmentList.size(); i++) {

            // if location exists
            if (equipmentList.get(i).getCommon_locations() != null) {
                for (int j = 0; j < equipmentList.get(i).getCommon_locations().size(); j++) {
                    // if contains locations, add to display list
                    if (equipmentList.get(i).getCommon_locations().get(j).contains(location) && !displayList.contains(equipmentList.get(i))) {
                        displayList.add(equipmentList.get(i));
                    }
                }
            }
        }

        // recyclerview
        adapter = new ItemAdapter(this, displayList);
        binding.rvList.setAdapter(adapter);
        binding.rvList.setLayoutManager(new LinearLayoutManager(this));
        binding.rvList.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        adapter.notifyDataSetChanged();
    }

    // search by keyword
    private void searchByKeyword(String keyword) {
        // list to display
        ArrayList<Equipment> displayList = new ArrayList<Equipment>();

        // populate list
        for (int i = 0; i < equipmentList.size(); i++) {

            // if name contains keyword, add to display list
            if (equipmentList.get(i).getName().contains(keyword.toLowerCase())) {
                displayList.add(equipmentList.get(i));
            }
        }

        // recyclerview
        adapter = new ItemAdapter(this, displayList);
        binding.rvList.setAdapter(adapter);
        binding.rvList.setLayoutManager(new LinearLayoutManager(this));
        binding.rvList.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        adapter.notifyDataSetChanged();
    }

    // clear recyclerview
    private void clearRecycle() {
        ArrayList<Equipment> clearList = new ArrayList<Equipment>();
        clearList.clear();

        // recyclerview
        adapter = new ItemAdapter(this, clearList);
        binding.rvList.setAdapter(adapter);
        binding.rvList.setLayoutManager(new LinearLayoutManager(this));
        binding.rvList.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        adapter.notifyDataSetChanged();
    }

    // checks if input is empty
    private boolean isEmpty(EditText txt) {
        return txt.getText().toString().length() > 0 ? false : true;
    }
}