package com.acenkzproject.myfuitlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.acenkzproject.myfuitlist.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    protected ArrayList<Fruit> list = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;

    private FruitGridAdapter fruitGridAdapter;
    private boolean ViewList = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rvFruit.setHasFixedSize(true);
        list.addAll(getListFruit());

        binding.rvFruit.setHasFixedSize(true);
        getSupportActionBar().hide();

        layoutManager = new LinearLayoutManager(this);
        binding.rvFruit.setLayoutManager(layoutManager);

        fruitGridAdapter = new FruitGridAdapter(list, ViewList);
        binding.rvFruit.setAdapter(fruitGridAdapter);

        binding.btnSwitch.setOnClickListener(view -> {
            ViewList = !ViewList;
            if (ViewList){
                layoutManager = new LinearLayoutManager(MainActivity.this);
                binding.btnSwitch.setImageResource(R.drawable.baseline_grid_view_24);
            }
            else {
                layoutManager = new GridLayoutManager(MainActivity.this, 2);
                binding.btnSwitch.setImageResource(R.drawable.baseline_format_list_bulleted_24);
            }
            binding.rvFruit.setLayoutManager(layoutManager);
            fruitGridAdapter = new FruitGridAdapter(list, ViewList);
            binding.rvFruit.setAdapter(fruitGridAdapter);
        });

    }


    public ArrayList<Fruit> getListFruit() {
        String[] datanama = getResources().getStringArray(R.array.nama_buah);
        String[] datadeskripsi = getResources().getStringArray(R.array.deskripsi_buah);
        TypedArray dataGambar = getResources().obtainTypedArray(R.array.gambar_buah);

        ArrayList<Fruit> listfruit = new ArrayList<>();
        for (int i = 0; i < datanama.length; i++){
            Fruit fruit = new Fruit();
            fruit.setNama(datanama[i]);
            fruit.setDeskripsi(datadeskripsi[i]);
            fruit.setGambar(dataGambar.getResourceId(i, -1));
            listfruit.add(fruit);
        }
        return listfruit;
    }
}