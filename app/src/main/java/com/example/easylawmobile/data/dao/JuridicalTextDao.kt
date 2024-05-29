package com.example.easylawmobile.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.easylawmobile.data.models.JuridicalText

@Dao
interface JuridicalTextDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJuridicalTexts(texts: List<JuridicalText>)


}
