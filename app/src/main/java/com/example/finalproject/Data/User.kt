package com.example.finalproject.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "mTitle") val mTitle: String?,
    @ColumnInfo(name = "mComm") val mComm: String?
)