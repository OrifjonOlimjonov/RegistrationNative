package uz.orifjon.registrationnative.models

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun add(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user")
    fun list():List<User>

}