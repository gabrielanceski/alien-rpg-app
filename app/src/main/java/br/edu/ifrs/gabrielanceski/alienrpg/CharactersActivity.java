package br.edu.ifrs.gabrielanceski.alienrpg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import br.edu.ifrs.gabrielanceski.alienrpg.database.AppDatabase;

public class CharactersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "alien-rpg-app").allowMainThreadQueries().build();

        RecyclerView recyclerView = findViewById(R.id.characterList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CharacterAdapter(db));

    }
}