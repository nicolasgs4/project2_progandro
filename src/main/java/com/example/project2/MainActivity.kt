package com.example.project2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project2.R
import com.example.project2.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var firestore: FirebaseFirestore? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()


        binding.simpan.setOnClickListener {


            val note = notes(
                binding.judul.text.toString(),
                binding.tanggal.text.toString(),
                binding.note.text.toString()
            )
            firestore?.collection("notes")?.add(note)
            binding.judul.setText("")
            binding.note.setText("")

        }
        firestore?.collection("notes")?.orderBy("judul", Query.Direction.ASCENDING)?.get()
            ?.addOnSuccessListener { data ->

                val txvOutput = findViewById<TextView>(R.id.txvOutput)
                var output = ""
                val delete = notes(binding.judul.text.toString())
                for (tampilan in data) {
                    if (delete == tampilan["judul"]) {
                        firestore!!.collection("notes").document(tampilan.id).delete()
                    }
                    txvOutput.text = output

                }

            }
    }
        private fun getCurrentDate(): String {
            val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
            val date = Date()

            return dateFormat.format(date)
        }

    }
