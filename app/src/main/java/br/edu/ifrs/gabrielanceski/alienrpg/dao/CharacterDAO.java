package br.edu.ifrs.gabrielanceski.alienrpg.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.edu.ifrs.gabrielanceski.alienrpg.model.Character;

@Dao
public interface CharacterDAO {

    @Query("SELECT * FROM character")
    List<Character> getAll();

    @Insert
    void insertAll(Character... characters);

    @Delete
    void delete(Character character);
}
