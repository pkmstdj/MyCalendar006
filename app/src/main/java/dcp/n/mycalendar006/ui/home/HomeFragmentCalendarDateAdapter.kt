package dcp.n.mycalendar006.ui.home

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dcp.n.mycalendar006.R
import kotlinx.android.synthetic.main.item_home_calendar_date.view.*
import java.text.SimpleDateFormat
import java.util.*

class HomeFragmentCalendarDateAdapter (val context: Context, val calendarLayout: LinearLayout, val date: Date) :
    RecyclerView.Adapter<HomeFragmentCalendarDateAdapter.CalendarItemHolder>() {

    companion object {
        const val DAYS_OF_WEEK = 7
        const val LOW_OF_CALENDAR = 6
    }

    private val TAG = javaClass.simpleName
    var dataList: ArrayList<Int> = arrayListOf()


    // 날짜 리스트 세팅
    var calendarDate: CalendarDate = CalendarDate(date)
    init {
        calendarDate.initBaseCalendar()
        dataList = calendarDate.dateList
    }

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun onBindViewHolder(holder: CalendarItemHolder, position: Int) {

        // list_item_calendar 높이 지정
        val h = calendarLayout.height / 6
        holder.itemView.layoutParams.height = h

        holder?.bind(dataList[position], position, context)
        if (itemClick != null) {
            holder?.itemView?.setOnClickListener { v ->
                itemClick?.onClick(v, position)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarItemHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_home_calendar_date, parent, false)
        return CalendarItemHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    inner class CalendarItemHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        var itemCalendarDateText: TextView = itemView!!.item_calendar_date_text
        var itemCalendarDotView: View = itemView!!.item_calendar_dot_view

        fun bind(data: Int, position: Int, context: Context) {
            val firstDateIndex = calendarDate.prevTail
            val lastDateIndex = dataList.size - calendarDate.nextHead - 1
            itemCalendarDateText.setText(data.toString())

            // 오늘 날짜 처리
            var dateString: String = SimpleDateFormat("dd", Locale.KOREA).format(date)
            var dateInt = dateString.toInt()
            if (dataList[position] == dateInt) {
                itemCalendarDateText.setTypeface(itemCalendarDateText.typeface, Typeface.BOLD)
            }

            // 현재 월의 1일 이전, 현재 월의 마지막일 이후 값의 텍스트를 회색처리
            if (position < firstDateIndex || position > lastDateIndex) {
                itemCalendarDateText.setTextAppearance(R.style.LightColorTextViewStyle)
                itemCalendarDotView.background = null
            }
        }
    }

    inner class CalendarDate(date: Date) {
        val calendar = Calendar.getInstance()
        var prevTail = 0
        var nextHead = 0
        var currentMaxDate = 0
        var dateList = arrayListOf<Int>()

        init {
            calendar.time = date
        }

        fun initBaseCalendar() {
            makeMonthDate()
        }

        private fun makeMonthDate() {

            dateList.clear()

            calendar.set(Calendar.DATE, 1)

            currentMaxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

            prevTail = calendar.get(Calendar.DAY_OF_WEEK) - 1

            makePrevTail(calendar.clone() as Calendar)
            makeCurrentMonth(calendar)

            nextHead = LOW_OF_CALENDAR * DAYS_OF_WEEK - (prevTail + currentMaxDate)
            makeNextHead()
        }

        private fun makePrevTail(calendar: Calendar) {
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1)
            val maxDate = calendar.getActualMaximum(Calendar.DATE)
            var maxOffsetDate = maxDate - prevTail

            for (i in 1..prevTail) dateList.add(++maxOffsetDate)
        }

        private fun makeCurrentMonth(calendar: Calendar) {
            for (i in 1..calendar.getActualMaximum(Calendar.DATE)) dateList.add(i)
        }

        private fun makeNextHead() {
            var date = 1

            for (i in 1..nextHead) dateList.add(date++)
        }
    }
}