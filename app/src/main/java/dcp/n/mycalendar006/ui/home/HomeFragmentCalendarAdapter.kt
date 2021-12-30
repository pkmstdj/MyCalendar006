package dcp.n.mycalendar006.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeFragmentCalendarAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val pageCount = Int.MAX_VALUE
    var fragments : ArrayList<Fragment> = ArrayList()

//    override fun getItemCount(): Int {
//        return fragments.size
//    }
//    override fun createFragment(position: Int): Fragment {
//        return fragments[position]
//    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun createFragment(position: Int): Fragment {
        val calendarFragment = HomeCalendarFragment()
        calendarFragment.pageIndex = position
        return calendarFragment
    }

    fun addFragment(fragment: Fragment){
        fragments.add(fragment)
        notifyItemInserted(fragments.size - 1)
    }

    fun removeFragment(){
        fragments.removeLast()
        notifyItemRemoved(fragments.size)
    }
}