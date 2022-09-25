package uz.orifjon.registrationnative.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val fullName:String,
    val username:String,
    val password:String,
    val cryptography:String
):Serializable