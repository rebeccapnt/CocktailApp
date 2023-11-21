package com.example.cocktailtemplate
import androidx.appcompat.app.AppCompatActivity
import com.example.cocktailtemplate.databinding.ActivityMainBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.cocktailtemplate.ui.categories.CategoriesFragment
import com.example.cocktailtemplate.ui.ingredients.IngredientsFragment
import com.example.cocktailtemplate.ui.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        loadFragment(SearchFragment())
        bottomNav = binding.bottomNav as BottomNavigationView
        bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search_tab -> {
                    loadFragment(SearchFragment())
                    true
                }
                R.id.categories_tab -> {
                    loadFragment(CategoriesFragment())
                    true
                }
                R.id.ingredients_tab -> {
                    loadFragment(IngredientsFragment())
                    true
                } else -> false
            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.container.id, fragment)
        transaction.commit()
    }

}