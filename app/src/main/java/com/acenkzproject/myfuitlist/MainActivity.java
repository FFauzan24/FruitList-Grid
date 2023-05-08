package com.acenkzproject.myfuitlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.acenkzproject.myfuitlist.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    protected ArrayList<Fruit> list = new ArrayList<>();

    private boolean ViewList = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rvFruit.setHasFixedSize(true);
        list.addAll(getListFruit());
        showRecyclerList(1);

        getSupportActionBar().setTitle(title);
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

    private void showRecyclerList(int mode){
        if (mode == 2){
            binding.rvFruit.setLayoutManager((new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)));
            FruitListAdapter fruitAdapter = new FruitListAdapter(list);
            binding.rvFruit.setAdapter(fruitAdapter);
        }
        else if (mode == 1){
            binding.rvFruit.setLayoutManager((new GridLayoutManager(this, 2)));
            FruitGridAdapter fruitAdapter = new FruitGridAdapter(list);
            binding.rvFruit.setAdapter(fruitAdapter);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }
    public void setMode(int selectedMode){
        switch (selectedMode){
            case R.id.action_grid:
                title = "Mode Grid";
                showRecyclerList(1);
                break;
            case R.id.action_list:
                title = "Mode List";
                showRecyclerList(2);
                break;
        }
        getSupportActionBar().setTitle(title);
    }


    private static String title = "Buah Buahan Langka Indonesia";
}