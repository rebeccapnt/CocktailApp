package com.example.cocktailtemplate
import androidx.appcompat.app.AppCompatActivity
import com.example.cocktailtemplate.databinding.ActivityMainBinding
import android.os.Bundle
import com.example.cocktailtemplate.ui.categories.CategoriesFragment
import com.example.cocktailtemplate.ui.ingredients.IngredientsFragment

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, IngredientsFragment())
                .commit()
        }
    }
}