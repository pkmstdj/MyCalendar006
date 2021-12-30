package dcp.n.mycalendar006.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import dcp.n.mycalendar006.R
import dcp.n.mycalendar006.databinding.FragmentHomeBinding
import dcp.n.mycalendar006.ui.dashboard.DashboardFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeFragmentCalendarAdapter: HomeFragmentCalendarAdapter
    private lateinit var viewPager: ViewPager2
    val firstFragmentPosition = Int.MAX_VALUE / 2

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeFragmentCalendarAdapter = HomeFragmentCalendarAdapter(this)
        viewPager = view.findViewById(R.id.viewPager2_Calendar)

//        homeFragmentCalendarAdapter.addFragment(HomeCalendarFragment())
//        homeFragmentCalendarAdapter.addFragment(HomeCalendarFragment())
//        homeFragmentCalendarAdapter.addFragment(HomeCalendarFragment())
        viewPager.adapter = homeFragmentCalendarAdapter

        viewPager.setCurrentItem(firstFragmentPosition, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


//
//class HomeFragment : Fragment() {
//
//    private lateinit var homeViewModel: HomeViewModel
//    private var _binding: FragmentHomeBinding? = null
//
//    // This property is only valid between onCreateView and
//    // onDestroyView.
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)
//
//        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
//        return root
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}