package com.algonquincollege.smyt0058.oso;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.algonquincollege.smyt0058.oso.models.User;

import java.util.List;

/**
 * Created by Jason on 2018-04-10.
 */
@Dao
public interface UserDAO {

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Insert
    void insertAll(User... users);

}
