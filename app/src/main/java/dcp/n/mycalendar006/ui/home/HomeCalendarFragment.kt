package dcp.n.mycalendar006.ui.home

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dcp.n.mycalendar006.MainActivity
import dcp.n.mycalendar006.R
import kotlinx.android.synthetic.main.item_home_calendar.view.*
import java.util.*

class HomeCalendarFragment : Fragment() {
    private lateinit var mContext: Context

    var pageIndex = 0
    lateinit var currentDate: Date

    lateinit var calendar_year_month_text: TextView
    lateinit var calendar_layout: LinearLayout
    lateinit var calendar_view: RecyclerView
    lateinit var calendarDateAdapter: HomeFragmentCalendarDateAdapter

    companion object {
        var instance: HomeCalendarFragment? = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            mContext = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.item_home_calendar, container, false)
        initView(view)
        initCalendar()
        return view
    }

    private fun initView(view: View) {
        pageIndex -= (Int.MAX_VALUE / 2)
//        Log.e(TAG, "Calender Index: $pageIndex")
        calendar_year_month_text = view.calendar_year_month_text
        calendar_layout = view.calendar_layout
        calendar_view = view.calendar_view
        // 날짜 적용
        val date = Calendar.getInstance().run {
            add(Calendar.MONTH, pageIndex)
            time
        }
        currentDate = date
//        Log.e(TAG, "$date")
        // 포맷 적용
        var datetime: String = SimpleDateFormat(
            mContext.getString(R.string.calendar_year_month_format),
            Locale.KOREA
        ).format(date.time)
        calendar_year_month_text.text = datetime
    }

    fun initCalendar() {
        // 각 월의 1일의 요일을 구해
        // 첫 주의 일요일~해당 요일 전까지는 ""으로
        // 말일까지 해당 날짜
        // 마지막 날짜 뒤로는 ""으로 처리하여
        // CalendarAdapter로 List를 넘김
        calendarDateAdapter = HomeFragmentCalendarDateAdapter(mContext, calendar_layout, currentDate)
        calendar_view.adapter = calendarDateAdapter
        calendar_view.layoutManager = GridLayoutManager(mContext, 7, GridLayoutManager.VERTICAL, false)
        calendar_view.setHasFixedSize(true)
        calendarDateAdapter.itemClick = object :
            HomeFragmentCalendarDateAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val firstDateIndex = calendarDateAdapter.dataList.indexOf(1)
                val lastDateIndex = calendarDateAdapter.dataList.lastIndexOf(calendarDateAdapter.calendarDate.currentMaxDate)
                // 현재 월의 1일 이전, 현재 월의 마지막일 이후는 터치 disable
                if (position < firstDateIndex || position > lastDateIndex) {
                    Snackbar.make(view, "out", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show()
                    return
                }
                val day = calendarDateAdapter.dataList[position].toString()
                val date = "${calendar_year_month_text.text}${day}일"

                Snackbar.make(view, "" + date + "", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()

//                Log.d(TAG, "$date")

//                val mainTab = mActivity.main_bottom_menu
//                mainTab.setScrollPosition(1, 0f, true)
//                val mainViewPager = mActivity.main_pager
//                mainViewPager.currentItem = 1
//                RoutineDateLiveData.getInstance().getLiveProgress().value = date
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }
}