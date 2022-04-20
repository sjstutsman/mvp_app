package com.csis365.mvpapp.view

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.csis365.mvpapp.R
import com.csis365.mvpapp.view.a.AFragment
import com.csis365.mvpapp.view.b.BFragment
import com.csis365.mvpapp.view.c.CFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class ListsActivity : AppCompatActivity() {

    lateinit var fragmentContainer: FrameLayout
    lateinit var bottomBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lists)
        bindViews()
        setupNavBar()
    }

    private fun bindViews() {
        fragmentContainer = findViewById(R.id.frame_container)
        bottomBar = findViewById(R.id.bottom_nav_view)
        listOf("arst").first()
    }

    private fun setupNavBar() {
        bottomBar.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                // This first case will prevent us from opening a page if it's already selected
                bottomBar.selectedItemId -> true

                R.id.action_lists -> openListsPage()
                R.id.action_fragment_a -> openPageA()
                R.id.action_fragment_b -> openPageB()
                R.id.action_fragment_c -> openPageC()
                else -> throw IllegalStateException("Unknown action id, cannot open any fragment")
            }
            true
        }

        openListsPage()
    }


    private fun openListsPage() {
        val fragment = ListsFragment.newInstance()
        openFragment(fragment)
    }

    private fun openPageA() {
        val fragment = AFragment.newInstance()
        openFragment(fragment)
    }

    private fun openPageB() {
        val fragment = BFragment.newInstance()
        openFragment(fragment)
    }

    private fun openPageC() {
        val fragment = CFragment.newInstance()
        openFragment(fragment)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.frame_container, fragment, null)

        transaction.commit()
    }
}