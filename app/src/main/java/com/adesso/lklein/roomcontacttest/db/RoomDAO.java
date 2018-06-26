package com.adesso.lklein.roomcontacttest.db;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.adesso.lklein.roomcontacttest.models.Projekt;

import java.util.List;


@Dao
public interface RoomDAO {

    @Insert
    public void insert(Projekt... projekts);
    @Update
    public void update(Projekt... projekts);
    @Delete
    public void delete(Projekt... projekts);

    @Query("SELECT * FROM projekt")
    public List<Projekt> getProjektname();

    @Query("SELECT * FROM projekt WHERE arbeitername= :name")
    public Projekt getProjektWithId(String name);

}
