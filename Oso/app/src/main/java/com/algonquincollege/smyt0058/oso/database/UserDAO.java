package com.algonquincollege.smyt0058.oso.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

/**
 * Created by Jason on 2018-04-10.
 *
 * UserDAO
 * Database interface
 *
 */
@Dao
public interface UserDAO {

    @Query("SELECT * FROM UserChat")
    Cursor getChatHistoryByID();

    @Insert
    void insertUser(UserChat userChat);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUser(UserChat userChat);

    @Delete
    void deleteUser(UserChat userchat);

    @Query("DELETE FROM UserChat")
    void nukeTable();

}
