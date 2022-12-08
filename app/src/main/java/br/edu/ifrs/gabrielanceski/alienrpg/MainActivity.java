package br.edu.ifrs.gabrielanceski.alienrpg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import br.edu.ifrs.gabrielanceski.alienrpg.database.AppDatabase;
import br.edu.ifrs.gabrielanceski.alienrpg.model.Attribute;
import br.edu.ifrs.gabrielanceski.alienrpg.model.Career;
import br.edu.ifrs.gabrielanceski.alienrpg.model.Character;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCharacters;
    private Button btnNewCharacter;

//    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCharacters = findViewById(R.id.btnCharacters);
        btnNewCharacter = findViewById(R.id.btnNewCharacter);

        btnCharacters.setOnClickListener(this);
        btnNewCharacter.setOnClickListener(this);

//        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "alien-rpg-app").allowMainThreadQueries().build();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btnNewCharacter.getId()) {
            Intent intent = new Intent(this, CreateCharacterActivity.class);
            startActivity(intent);
        } else if (view.getId() == btnCharacters.getId()) {
//            System.out.println("CHARACTERS:");
//            db.characterDAO().getAll().forEach(System.out::println);
            Intent intent = new Intent(this, CharactersActivity.class);
            startActivity(intent);
        }
    }
}