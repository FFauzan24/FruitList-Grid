package com.acenkzproject.myfuitlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

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

    private boolean ViewList = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rvFruit.setHasFixedSize(true);
        list.addAll(getListFruit());
        showRecyclerList();

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

    private void showRecyclerList(){
        SwitchCompat toggle = findViewById(R.id.action_toggle);
        toggle.setOnCheckedChangeListener((compoundButton, b) -> {
            ViewList = b;
            if (ViewList){
                binding.rvFruit.setLayoutManager((new LinearLayoutManager(MainActivity.this)));
            }
            else {
                binding.rvFruit.setLayoutManager((new GridLayoutManager(MainActivity.this, 2)));
            }
            FruitGridAdapter fruitGridAdapter = new FruitGridAdapter(list);
            binding.rvFruit.setAdapter(fruitGridAdapter);
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }
    private static String title = "Buah Buahan Langka Indonesia";
}