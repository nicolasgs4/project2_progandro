package com.example.project2

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore

data class notes(
    var judul: String? = null,
    var tanggal: String? = null,
    var note: String? = null
)