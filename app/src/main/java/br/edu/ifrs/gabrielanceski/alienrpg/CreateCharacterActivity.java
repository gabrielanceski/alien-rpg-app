package br.edu.ifrs.gabrielanceski.alienrpg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

import br.edu.ifrs.gabrielanceski.alienrpg.database.AppDatabase;
import br.edu.ifrs.gabrielanceski.alienrpg.fragment.AbilitiesFragment;
import br.edu.ifrs.gabrielanceski.alienrpg.fragment.AttributesFragment;
import br.edu.ifrs.gabrielanceski.alienrpg.model.Ability;
import br.edu.ifrs.gabrielanceski.alienrpg.model.Attribute;
import br.edu.ifrs.gabrielanceski.alienrpg.model.Career;
import br.edu.ifrs.gabrielanceski.alienrpg.model.Character;

public class CreateCharacterActivity extends AppCompatActivity {
    private Button btnNext;
    private AttributesFragment attributesFragment;
    private AbilitiesFragment abilitiesFragment;
    private int currentStep;
    private static final int ATTRIBUTES_STEP = 0;
    private static final int ABILITIES_STEP = 1;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);

        attributesFragment = new AttributesFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_frame, attributesFragment);
        transaction.commit();

        btnNext = findViewById(R.id.next_btn);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "alien-rpg-app").allowMainThreadQueries().build();

        initListeners();
    }

    private void initListeners() {
        btnNext.setOnClickListener(view -> {
            if (currentStep == ATTRIBUTES_STEP) {
                if (attributesFragment.getCharacterName() == null || attributesFragment.getCharacterName().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Você precisa escolher um nome para seu personagem!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (attributesFragment.getPointPool() != 0) {
                    Toast.makeText(getApplicationContext(), "Você precisa gastar todos os pontos disponíveis nos seus atributos!", Toast.LENGTH_SHORT).show();
                    return;
                }

                setAbilitiesStep();
            } else if (currentStep == ABILITIES_STEP) {
                if (abilitiesFragment.getPointPool() != 0) {
                    Toast.makeText(getApplicationContext(), "Você precisa gastar todos os pontos disponíveis nas suas habilidades!", Toast.LENGTH_SHORT).show();
                    return;
                }

                saveCharacter();
                Toast.makeText(getApplicationContext(), "Novo personagem criado!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void saveCharacter() {
        Career career = attributesFragment.getSelectedCareer();
        String name = attributesFragment.getCharacterName();
        Map<Attribute, Integer> attributeMap = attributesFragment.getAttributes();
        Map<Ability, Integer> abilityMap = abilitiesFragment.getAbilities();

        Character character = new Character();
        character.setName(name);
        character.setCareer(career);
        character.setAttributes(attributeMap);
        character.setAbilities(abilityMap);

        db.characterDAO().insertAll(character);
    }

    private void setAbilitiesStep() {
        currentStep = ABILITIES_STEP;

        abilitiesFragment = AbilitiesFragment.newInstance(attributesFragment.getSelectedCareer());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_frame, abilitiesFragment);
        transaction.commit();
    }
}