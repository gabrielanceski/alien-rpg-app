package br.edu.ifrs.gabrielanceski.alienrpg.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import br.edu.ifrs.gabrielanceski.alienrpg.R;
import br.edu.ifrs.gabrielanceski.alienrpg.RPGConstraints;
import br.edu.ifrs.gabrielanceski.alienrpg.model.Ability;
import br.edu.ifrs.gabrielanceski.alienrpg.model.Attribute;
import br.edu.ifrs.gabrielanceski.alienrpg.model.Career;

/**
 * Fragment que gerencia as habilidades de um novo personagem.
 */
public class AbilitiesFragment extends Fragment {
    private View parentView;
    private TextView pointPoolView;

    private Career characterCareer;
    private int pointPool;

    public static AbilitiesFragment newInstance(Career characterCareer) {
        AbilitiesFragment abilitiesFragment = new AbilitiesFragment();
        Bundle args = new Bundle();
        args.putInt("career", characterCareer.ordinal());
        abilitiesFragment.setArguments(args);
        return abilitiesFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            characterCareer = Career.values()[getArguments().getInt("career")];
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_abilities, container, false);

        pointPoolView = parentView.findViewById(R.id.abilities_point_pool);

        resetPointPool();

        // Habilidades do atributo FORÇA
        initializeSelector(R.id.abilities_heavy_machinery_display, R.id.abilities_heavy_machinery_value, R.id.abilities_heavy_machinery_subtract, R.id.abilities_heavy_machinery_add);
        initializeSelector(R.id.abilities_close_combat_display, R.id.abilities_close_combat_value, R.id.abilities_close_combat_subtract, R.id.abilities_close_combat_add);
        initializeSelector(R.id.abilities_stamina_display, R.id.abilities_stamina_value, R.id.abilities_stamina_subtract, R.id.abilities_stamina_add);
        // Habilidades do atributo PERSPICÁCIA
        initializeSelector(R.id.abilities_observation_display, R.id.abilities_observation_value, R.id.abilities_observation_subtract, R.id.abilities_observation_add);
        initializeSelector(R.id.abilities_survival_display, R.id.abilities_survival_value, R.id.abilities_survival_subtract, R.id.abilities_survival_add);
        initializeSelector(R.id.abilities_comtech_display, R.id.abilities_comtech_value, R.id.abilities_comtech_subtract, R.id.abilities_comtech_add);
        // Habilidades do atributo EMPATIA
        initializeSelector(R.id.abilities_medical_aid_display, R.id.abilities_medical_aid_value, R.id.abilities_medical_aid_subtract, R.id.abilities_medical_aid_add);
        initializeSelector(R.id.abilities_manipulation_display, R.id.abilities_manipulation_value, R.id.abilities_manipulation_subtract, R.id.abilities_manipulation_add);
        initializeSelector(R.id.abilities_command_display, R.id.abilities_command_value, R.id.abilities_command_subtract, R.id.abilities_command_add);
        // Habilidades do atributo AGILIDADE
        initializeSelector(R.id.abilities_piloting_display, R.id.abilities_piloting_value, R.id.abilities_piloting_subtract, R.id.abilities_piloting_add);
        initializeSelector(R.id.abilities_mobility_display, R.id.abilities_mobility_value, R.id.abilities_mobility_subtract, R.id.abilities_mobility_add);
        initializeSelector(R.id.abilities_ranged_combat_display, R.id.abilities_ranged_combat_value, R.id.abilities_ranged_combat_subtract, R.id.abilities_ranged_combat_add);

        return parentView;
    }

    private void resetPointPool() {
        pointPool = RPGConstraints.ABILITY_POINT_POOL;
        pointPoolView.setText(String.valueOf(pointPool));
    }

    private void initializeSelector(int descriptionId, int valueId, int subtractId, int addId) {
        TextView selectorDescription = parentView.findViewById(descriptionId);
        TextView selectorValue = parentView.findViewById(valueId);
        Button subtract = parentView.findViewById(subtractId);
        Button add = parentView.findViewById(addId);

        subtract.setOnClickListener(view -> selectorSubtract(selectorValue));
        add.setOnClickListener(view -> selectorAdd(selectorDescription, selectorValue));
    }

    private void selectorSubtract(TextView selectorValue) {
        int value = Integer.parseInt(String.valueOf(selectorValue.getText()));
        if (--value < RPGConstraints.MIN_ABILITY_POINTS) return;
        selectorValue.setText(String.valueOf(value));
        pointPool++;
        pointPoolView.setText(String.valueOf(pointPool));
    }

    private void selectorAdd(TextView selectorDescription, TextView selectorValue) {
        int value = Integer.parseInt(String.valueOf(selectorValue.getText()));
        Ability selectorAbility = Ability.fromPortuguese(selectorDescription.getText().toString());
        boolean isFromCareerMainAttribute = selectorAbility.getAttribute() == characterCareer.getMainAttribute();
        if (++value > (isFromCareerMainAttribute ? RPGConstraints.MAX_CAREER_ABILITY_POINTS : RPGConstraints.MAX_ABILITY_POINTS) || pointPool == 0)
            return;
        selectorValue.setText(String.valueOf(value));
        pointPool--;
        pointPoolView.setText(String.valueOf(pointPool));
    }

    private int getSelectorValue(int id) {
        return Integer.parseInt(((TextView) parentView.findViewById(id)).getText().toString());
    }

    public int getPointPool() {
        return pointPool;
    }

    public Map<Ability, Integer> getAbilities() {
        Map<Ability, Integer> map = new HashMap<>();

        // FORÇA
        map.put(Ability.HEAVY_MACHINERY, getSelectorValue(R.id.abilities_heavy_machinery_value));
        map.put(Ability.CLOSE_COMBAT, getSelectorValue(R.id.abilities_close_combat_value));
        map.put(Ability.STAMINA, getSelectorValue(R.id.abilities_stamina_value));
        // PERSPICÁCIA
        map.put(Ability.OBSERVATION, getSelectorValue(R.id.abilities_observation_value));
        map.put(Ability.SURVIVAL, getSelectorValue(R.id.abilities_survival_value));
        map.put(Ability.COMTECH, getSelectorValue(R.id.abilities_comtech_value));
        // EMPATIA
        map.put(Ability.MEDICAL_AID, getSelectorValue(R.id.abilities_medical_aid_value));
        map.put(Ability.MANIPULATION, getSelectorValue(R.id.abilities_manipulation_value));
        map.put(Ability.COMMAND, getSelectorValue(R.id.abilities_command_value));
        // AGILIDADE
        map.put(Ability.PILOTING, getSelectorValue(R.id.abilities_stamina_value));
        map.put(Ability.MOBILITY, getSelectorValue(R.id.abilities_mobility_value));
        map.put(Ability.RANGED_COMBAT, getSelectorValue(R.id.abilities_ranged_combat_value));

        return map;
    }
}