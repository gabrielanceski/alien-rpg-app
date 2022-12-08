package br.edu.ifrs.gabrielanceski.alienrpg.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.edu.ifrs.gabrielanceski.alienrpg.AbilityMapConverter;
import br.edu.ifrs.gabrielanceski.alienrpg.AttributeMapConverter;
import br.edu.ifrs.gabrielanceski.alienrpg.dao.CharacterDAO;
import br.edu.ifrs.gabrielanceski.alienrpg.model.Character;

@Database(entities = {Character.class}, version = 1, exportSchema = false)
@TypeConverters({AttributeMapConverter.class, AbilityMapConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract CharacterDAO characterDAO();
}
