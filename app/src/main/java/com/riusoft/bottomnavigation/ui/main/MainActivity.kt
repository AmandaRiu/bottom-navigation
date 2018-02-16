package com.riusoft.bottomnavigation.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.riusoft.bottomnavigation.R
import com.riusoft.bottomnavigation.extensions.*
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
        MainContract.View,
        HasSupportFragmentInjector,
        BottomNavigationView.OnNavigationItemSelectedListener {

    companion object {
        private const val KEY_POSITION = "keyPosition"
    }

    private var activeNavPosition: BottomNavigationPosition = BottomNavigationPosition.DASHBOARD

    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        Log.d("AMANDA-TEST", "mainActivity: onCreate")

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
        Log.d("AMANDA-TEST", "mainActivity: onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        // Store the current navigation position.
        outState?.putInt(KEY_POSITION, activeNavPosition.id)
        super.onSaveInstanceState(outState)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val pos = findNavigationPositionById(item.itemId)
        return switchFragment(pos)
    }

    private fun restoreSaveInstanceState(savedInstanceState: Bundle?) {
        // Restore the current navigation position.
        savedInstanceState?.also {
            val id = it.getInt(KEY_POSITION, BottomNavigationPosition.DASHBOARD.id)
            activeNavPosition = findNavigationPositionById(id)
        }
    }

    private fun setupBottomNavigation() {
        bottom_navigation.disableShiftMode() // Extension function
        bottom_navigation.active(activeNavPosition.position)   // Extension function
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
    private fun switchFragment(pos: BottomNavigationPosition): Boolean {

        Log.d("AMANDA-TEST", "mainActivity: switchFragment")
        val activeFragment = supportFragmentManager.findFragmentByTag(activeNavPosition.getTag())

        // Remove any child fragments
        clearFragmentBackStack(activeFragment)

        // Grab the requested top-level fragment and load if not already
        // in the current view.
        val fragment = supportFragmentManager.findFragment(pos)
        if (fragment.isHidden || !fragment.isAdded) {
            // Remove the active fragment and replace with this newly selected one
            hideParentFragment(activeFragment)
            showParentFragment(fragment, pos.getTag())
            supportFragmentManager.executePendingTransactions()
            activeNavPosition = pos
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
    private fun clearFragmentBackStack(fragment: Fragment?) {
        fragment?.let {
            while (fragment.childFragmentManager.backStackEntryCount > 0) {
                fragment.childFragmentManager.popBackStackImmediate()
            }
        }
    }

    /**
     * Remove the current fragment from the fragment container. This
     * should only be used with top-level fragments.
     */
    private fun hideParentFragment(fragment: Fragment?) {
        fragment?.let {
            supportFragmentManager.beginTransaction().hide(fragment).commit()
        }
    }

    /**
     * Attach the provided fragment to the fragment container. This should
     * only be used with top-level fragments.
     */
    private fun showParentFragment(fragment: Fragment, tag: String) {
        if (fragment.isHidden) {
            supportFragmentManager.beginTransaction().show(fragment).commit()
        } else {
            supportFragmentManager.beginTransaction().add(R.id.container, fragment, tag).commit()
        }
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentByTag(activeNavPosition.getTag())
        fragment.childFragmentManager.popBackStack()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
