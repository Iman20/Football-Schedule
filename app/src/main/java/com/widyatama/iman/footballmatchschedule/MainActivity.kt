package com.widyatama.iman.footballmatchschedule

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.View
import com.widyatama.iman.footballmatchschedule.fragment.LastMatchFragment
import com.widyatama.iman.footballmatchschedule.fragment.NextMatchFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.viewPager

class MainActivity : AppCompatActivity() {

    private lateinit var tablayout: TabLayout
    private lateinit var toolbar: Toolbar
    private lateinit var viewpager: ViewPager
    private lateinit var viewPageAdapter: ViewPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            lparams(width = matchParent, height = matchParent)

            toolbar = toolbar {
                id = R.id.toolbar
                lparams(width = matchParent, height = 100)
                setBackgroundColor(resources.getColor(R.color.customColorPrimary))
                setTitleTextColor(Color.WHITE)
            }

            tablayout = tabLayout {
                id = R.id.tab
                setBackgroundColor(resources.getColor(R.color.customColorPrimary))
                setTabTextColors(Color.GRAY, Color.WHITE)
                setSelectedTabIndicatorColor(Color.WHITE)
                lparams(width = matchParent, height = wrapContent)
            }

            viewpager = viewPager {
                id = R.id.viewpager
            }.lparams(width = matchParent, height = matchParent)
        }

        viewPageAdapter = ViewPageAdapter(supportFragmentManager)
        viewPageAdapter.addFragment(LastMatchFragment.newInstance(), "LAST MATCH")
        viewPageAdapter.addFragment(NextMatchFragment.newInstance(), "NEXT MATCH")
        viewpager.adapter = viewPageAdapter

        tablayout.setupWithViewPager(viewpager)
        setSupportActionBar(toolbar)
    }

    class ViewPageAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager){
        private var fragmentList: MutableList<Fragment> = mutableListOf()
        private var titleFragment: MutableList<String> = mutableListOf()

        override fun getItemPosition(`object`: Any): Int {
            return super.getItemPosition(`object`)
        }

        override fun getItem(p0: Int): Fragment {
            return fragmentList.get(p0)
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        open fun addFragment(fragment: Fragment, title: String){
            fragmentList.add(fragment)
            titleFragment.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleFragment.get(position)
        }


    }

}
