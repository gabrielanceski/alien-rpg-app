package br.edu.ifrs.gabrielanceski.alienrpg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.edu.ifrs.gabrielanceski.alienrpg.database.AppDatabase;
import br.edu.ifrs.gabrielanceski.alienrpg.model.Ability;
import br.edu.ifrs.gabrielanceski.alienrpg.model.Attribute;
import br.edu.ifrs.gabrielanceski.alienrpg.model.Character;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {
    private List<Character> characterList;
    private final AppDatabase db;

    public CharacterAdapter(AppDatabase db) {
        this.db = db;
        characterList = db.characterDAO().getAll();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getCharacterName().setText(characterList.get(position).getName());

        Map<Attribute, Integer> attributes = characterList.get(position).getAttributes();
        String attributesStr = attributes.keySet().stream()
                .map(key -> key.getName() + ":  " + attributes.get(key))
                .collect(Collectors.joining(",  "));

        Map<Ability, Integer> abilities = characterList.get(position).getAbilities();
        String abilitiesStr = abilities.keySet().stream()
                .filter(ability -> abilities.getOrDefault(ability, 0) > 0)
                .map(key -> key.getName() + ":  " + abilities.get(key))
                .collect(Collectors.joining(",  "));

        holder.getCharacterAttributes().setText(attributesStr);
        holder.getCharacterAbilities().setText(abilitiesStr);
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView characterName;
        private final TextView characterAttributes;
        private final TextView characterAbilities;

        public ViewHolder(View view) {
            super(view);

            characterName = view.findViewById(R.id.character_name);
            characterAttributes = view.findViewById(R.id.character_attributes);
            characterAbilities = view.findViewById(R.id.character_abilities);
            view.findViewById(R.id.deleteCharacterBtn).setOnClickListener(l -> {
                int position = getAdapterPosition();
                db.characterDAO().delete(characterList.remove(position));
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, characterList.size());
            });
        }

        public TextView getCharacterName() {
            return characterName;
        }

        public TextView getCharacterAttributes() {
            return characterAttributes;
        }

        public TextView getCharacterAbilities() {
            return characterAbilities;
        }
    }

}
