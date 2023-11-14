package com.example.cocktailtemplate

import androidx.appcompat.app.AppCompatActivity
import com.example.cocktailtemplate.databinding.ActivityMainBinding
import android.os.Bundle

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}