package uk.ac.aber.dcs.cs31620.vocabhelper

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import uk.ac.aber.dcs.cs31620.vocabhelper.ui.vocabulary.VocabularyFragment
import uk.ac.aber.dcs.cs31620.vocabhelper.ui.vocabulary.addingitems.AddVocabularyFragment

// keys for SharePreferences that define the Language Configuration/Settings for the application
const val NATIVE_LANG_PREF = "nativeLanguage"
const val FOREIGN_LANG_PREF = "foreignLanguage"

/**
 * Set-ups navigation through the application (including the starting
 * fragment/screen).
 * Dictates on which fragments the  bottom navigation appears, and includes click listeners
 * for buttons.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNav()
    }

    private fun setupToolbar() {

    }

    /**
     * Sets up the navigation for the Activity, this includes the [navController] and
     * [bottomNavView].
     */
    private fun setupNav() {
        bottomNavView = findViewById(R.id.bottom_nav)
        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration =
            AppBarConfiguration(
                setOf(
                    R.id.vocabulary_navigation, R.id.quiz_navigation
                )
            )
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavView.setupWithNavController(navController)

        // hides bottom navigation when not on one of the main two fragments
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.vocabulary_navigation -> showBottomNav()
                R.id.quiz_navigation -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    /**
     * Shows bottom navigation
     */
    private fun showBottomNav() {
        bottomNavView.visibility = View.VISIBLE
    }

    /**
     * Hides bottom navigation
     */
    private fun hideBottomNav() {
        bottomNavView.visibility = View.GONE

    }

    // onClick listeners for buttons

    /**
     * Handles FAB in the [VocabularyFragment], taking the user to the [AddVocabularyFragment],
     * where they can add a new vocabulary item
     */
    fun fabAddVocabularyItem(view: View) {
        navController.navigate(R.id.action_vocabulary_navigation_to_add_vocabulary_navigation)
    }

}