package ado.com.testtabs

import ado.com.testtabs.databinding.ActivityMainBinding
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBar

class MainActivity : AppCompatActivity() {
    lateinit var currentFragments : List<Fragment>
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createFragments()
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.pager.adapter = MyPagerAdapter(supportFragmentManager)
        supportActionBar?.navigationMode = ActionBar.NAVIGATION_MODE_TABS

        val myTabListener : ActionBar.TabListener = MyTabListener()
        for(i in 0 until currentFragments.size) {
            supportActionBar?.
                    addTab(supportActionBar?.newTab()?.
                    setText(currentFragments.get(i)::class.java.simpleName)?.setTabListener(myTabListener))
        }
    }

    inner class MyTabListener : ActionBar.TabListener {
        override fun onTabUnselected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {
        }

        override fun onTabSelected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {
            binding.pager.currentItem = tab?.position ?: 0
        }

        override fun onTabReselected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {
        }

    }

    fun createFragments() {
        val myList = ArrayList<Fragment>()
        myList.add(MyFragment())
        myList.add(My2Fragment())
        currentFragments = myList
    }

    inner class MyPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return currentFragments.get(position)
        }

        override fun getCount(): Int {
            return currentFragments.size
        }

    }
}
