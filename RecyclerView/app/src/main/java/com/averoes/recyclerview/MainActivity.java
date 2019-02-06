package com.averoes.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.averoes.recyclerview.adapter.CardAdapter;
import com.averoes.recyclerview.adapter.GridAdapter;
import com.averoes.recyclerview.adapter.HeroAdapter;
import com.averoes.recyclerview.model.Hero;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public ArrayList<Hero> list = new ArrayList<>();
    public String tittle = "Mode List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_main);



        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);


        list.addAll(HeroesData.getList_data());
        showRecyclerList();
    }

    private void setActionBarTittle(String tittle) {
        getSupportActionBar().setTitle(tittle);
    }
    private void showSelectedHero(Hero hero){
        Toast.makeText(this, "Kamu memilih "+hero.getName(), Toast.LENGTH_SHORT).show();
    }


    private void showRecyclerList(){

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        HeroAdapter heroAdapter = new HeroAdapter(this);
        heroAdapter.setList_hero(list);
        recyclerView.setAdapter(heroAdapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedHero(list.get(position));
            }
        });

    }

    private void showRecyclerGrid(){
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        GridAdapter gridAdapter = new GridAdapter(this);
        gridAdapter.setList_hero(list);
        recyclerView.setAdapter(gridAdapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedHero(list.get(position));
            }
        });

    }

    private void showRecyclerCardView(){
        //setLayoutManager
        //Untuk menammpilkan recycler view sesuai dengan yang kita inginkan bisa linear atau grid
        
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CardAdapter cardAdapter = new CardAdapter(this);
        cardAdapter.setList_hero(list);
        recyclerView.setAdapter(cardAdapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedHero(list.get(position));
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_list :
                setActionBarTittle("List Mode");
                showRecyclerList();
                break;
            case R.id.action_grid:
                setActionBarTittle("Grid Mode");
                showRecyclerGrid();
                break;
            case R.id.action_cardview:
                showRecyclerCardView();
                setActionBarTittle("Grid Wtih Card View");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
