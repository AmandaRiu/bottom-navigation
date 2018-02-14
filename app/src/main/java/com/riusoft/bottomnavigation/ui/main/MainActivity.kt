package com.riusoft.bottomnavigation.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.riusoft.bottomnavigation.R
import com.riusoft.bottomnavigation.extensions.*
import com.riusoft.bottomnavigation.ui.base.FragmentListener
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
        MainContract.View,
        HasSupportFragmentInjector,
        BottomNavigationView.OnNavigationItemSelectedListener, FragmentListener {

    companion object {
        private const val KEY_POSITION = "keyPosition"
    }

    private var navPosition: BottomNavigationPosition = BottomNavigationPosition.DASHBOARD

    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        restoreSaveInstanceState(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup support toolbar
        setSupportActionBar(toolbar)

        presenter.takeView(this)
        setupBottomNavigation()
        initFragment(savedInstanceState)
    }

    override fun onDestroy() {
        presenter.dropView()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        // Store the current navigation position.
        outState?.putInt(KEY_POSITION, navPosition.id)
        super.onSaveInstanceState(outState)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        navPosition = findNavigationPositionById(item.itemId)
        return switchFragment(navPosition)
    }

    private fun restoreSaveInstanceState(savedInstanceState: Bundle?) {
        // Restore the current navigation position.
        savedInstanceState?.also {
            val id = it.getInt(KEY_POSITION, BottomNavigationPosition.DASHBOARD.id)
            navPosition = findNavigationPositionById(id)
        }
    }

    private fun setupBottomNavigation() {
//        bottom_navigation.disableShiftMode() // Extension function
        bottom_navigation.active(navPosition.position)   // Extension function
        bottom_navigation.setOnNavigationItemSelectedListener(this)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    private fun initFragment(savedInstanceState: Bundle?) {
        savedInstanceState ?: switchFragment(BottomNavigationPosition.DASHBOARD)
    }

    /**
     * If the user clicked on the already displayed top-level option, pop any child
     * fragments and reset to the main fragment.
     *
     * If the user selected an option not currently selected, pop any child fragments,
     * detach the current top-level fragment and attach the destination top-level fragment.
     *
     * Immediately execute transactions with FragmentManager#executePendingTransactions.
     */
    private fun switchFragment(navPosition: BottomNavigationPosition): Boolean {

        // Remove any child fragments
        returnToMainFragment()

        // Grab the requested top-level fragment and load if not already
        // in the current view.
        val fragment = supportFragmentManager.findFragment(navPosition)
        if (!fragment.isAdded) {

            // Remove the active fragment and replace with this newly selected one
            detachParentFragment()
            attachParentFragment(fragment, navPosition.getTag())
            supportFragmentManager.executePendingTransactions()

            return true
        }
        return false
    }

    /**
     * Extension function for retrieving an existing fragment from the [FragmentManager]
     * if one exists, if not, create a new instance of the requested fragment.
     */
    private fun FragmentManager.findFragment(position: BottomNavigationPosition): Fragment {
        return findFragmentByTag(position.getTag()) ?: position.createFragment()
    }

    /**
     * Pop all child fragments to return to the top-level view.
     */
    private fun returnToMainFragment() {
        while (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
        }
        // Reset toolbar status
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    /**
     * Remove the current fragment from the fragment container. This
     * should only be used with top-level fragments.
     */
    private fun detachParentFragment() {
        supportFragmentManager.findFragmentById(R.id.container)?.also {
            supportFragmentManager.beginTransaction().detach(it).commit()
        }
    }

    /**
     * Attach the provided fragment to the fragment container. This should
     * only be used with top-level fragments.
     */
    private fun attachParentFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.popBackStack()
        if (fragment.isDetached) {
            supportFragmentManager.beginTransaction().attach(fragment).commit()
        } else {
            supportFragmentManager.beginTransaction().add(R.id.container, fragment, tag).commit()
        }
        // Set a transition animation for this transaction.
        supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
    }

    /**
     * Display the requested fragment in the fragment view and add to the backstack.
     * Only use with child fragments to drill down from the top-level fragment.
     */
    override fun loadChildFragment(fragment: Fragment, tag: String) {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment, tag)
                .addToBackStack(null)
                .commit()
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount

        if (count > 0) {
            supportFragmentManager.popBackStack()
            if (count == 1) {
                supportActionBar?.setDisplayShowHomeEnabled(false)
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> false
        }
    }
}
