package br.edu.ifrs.gabrielanceski.alienrpg.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import br.edu.ifrs.gabrielanceski.alienrpg.R;
import br.edu.ifrs.gabrielanceski.alienrpg.RPGConstraints;
import br.edu.ifrs.gabrielanceski.alienrpg.model.Attribute;
import br.edu.ifrs.gabrielanceski.alienrpg.model.Career;

/**
 * Fragment que gerencia os atributos iniciais de um novo personagem.
 * Atributos: Nome, Android, Força, Agilidade, Perspicácia e Empatia
 */
public class AttributesFragment extends Fragment {
    // View
    private View parentView;
    private TextView pointPoolView;
    private EditText characterNameField;

    // RPG
    private Career selectedCareer = Career.ROUGHNECK;
    private int pointPool = RPGConstraints.ATTRIBUTE_POINT_POOL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_attributes, container, false);

        pointPoolView = parentView.findViewById(R.id.attributePointPool);
        characterNameField = parentView.findViewById(R.id.characterNameField);

        resetAllSelectors();
        initializeCareerSelector();
        initializeSelector(R.id.attribute_strength_display, R.id.attribute_strength_value, R.id.attribute_strength_subtract, R.id.attribute_strength_add);
        initializeSelector(R.id.attribute_wits_display, R.id.attribute_wits_value, R.id.attribute_wits_subtract, R.id.attribute_wits_add);
        initializeSelector(R.id.attribute_empathy_display, R.id.attribute_empathy_value, R.id.attribute_empathy_subtract, R.id.attribute_empathy_add);
        initializeSelector(R.id.attribute_agility_display, R.id.attribute_agility_value, R.id.attribute_agility_subtract, R.id.attribute_agility_add);

        return parentView;
    }

    private void initializeCareerSelector() {
        Spinner careerSelector = parentView.findViewById(R.id.career_selector);
        careerSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                selectedCareer = Career.values()[position];
                resetPointPool();
                resetAllSelectors();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedCareer = Career.ROUGHNECK;
            }
        });
    }

    private void resetPointPool() {
        pointPool = RPGConstraints.ATTRIBUTE_POINT_POOL;
        pointPoolView.setText(String.valueOf(pointPool));
    }

    private void resetAllSelectors() {
        resetSelector(R.id.attribute_strength_value);
        resetSelector(R.id.attribute_wits_value);
        resetSelector(R.id.attribute_empathy_value);
        resetSelector(R.id.attribute_agility_value);
    }

    private void resetSelector(int id) {
        ((TextView) parentView.findViewById(id)).setText(String.valueOf(RPGConstraints.MIN_ATTRIBUTE_POINTS));
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
        if (--value < RPGConstraints.MIN_ATTRIBUTE_POINTS) return;
        selectorValue.setText(String.valueOf(value));
        pointPool++;
        pointPoolView.setText(String.valueOf(pointPool));
    }

    private void selectorAdd(TextView selectorDescription, TextView selectorValue) {
        int value = Integer.parseInt(String.valueOf(selectorValue.getText()));
        Attribute selectorAttribute = Attribute.fromPortuguese(selectorDescription.getText().toString());
        boolean mainAttribute = selectorAttribute == selectedCareer.getMainAttribute();
        if (++value > (mainAttribute ? RPGConstraints.MAX_CAREER_ATTRIBUTE_POINTS : RPGConstraints.MAX_ATTRIBUTE_POINTS) || pointPool == 0)
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

    public String getCharacterName() {
        return characterNameField.getText().toString();
    }

    public Career getSelectedCareer() {
        return selectedCareer;
    }

    public Map<Attribute, Integer> getAttributes() {
        Map<Attribute, Integer> map = new HashMap<>();

        map.put(Attribute.STRENGTH, getSelectorValue(R.id.attribute_strength_value));
        map.put(Attribute.WITS, getSelectorValue(R.id.attribute_wits_value));
        map.put(Attribute.EMPATHY, getSelectorValue(R.id.attribute_empathy_value));
        map.put(Attribute.AGILITY, getSelectorValue(R.id.attribute_agility_value));

        return map;
    }
}