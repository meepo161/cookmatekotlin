package ru.avem.cookmatekotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import ru.avem.cookmatekotlin.R


class IngredientsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_ingredients, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val pagerAdapter = SectionsPagerAdapter(childFragmentManager)
        val pager: ViewPager = requireView().findViewById(R.id.pager)
        pager.adapter = pagerAdapter

        val tabLayout: TabLayout = requireView().findViewById(R.id.tabs)
        tabLayout.setupWithViewPager(pager)
        tabLayout.setTabTextColors(
            resources.getColor(R.color.color7),
            resources.getColor(R.color.white)
        )
        super.onActivityCreated(savedInstanceState)
    }

    private class SectionsPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
        override fun getCount(): Int {
            return 2
        }

        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> return MeatFragment()
                1 -> return FishFragment()
            }
            return MeatFragment()
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return "Мясо"
                1 -> return "Рыба"
            }
            return null
        }
    }
}
