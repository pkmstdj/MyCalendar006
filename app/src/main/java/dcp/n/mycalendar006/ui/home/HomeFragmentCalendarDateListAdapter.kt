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
import kotlinx.android.synthetic.main.item_home_calendar_datelist.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

//class HomeFragmentCalendarDateListAdapter (val context: Context, val calendarDateListLayout: LinearLayout, val _dataList: ArrayList<Dataset>) :
//class HomeFragmentCalendarDateListAdapter (val context: Context, val calendarDateListLayout: LinearLayout) :
class HomeFragmentCalendarDateListAdapter (val context: Context) : //, val calendarDateListLayout: LinearLayout) :
    RecyclerView.Adapter<HomeFragmentCalendarDateListAdapter.CalendarItemDateListHolder>() {

    companion object {
        const val MAX_OF_LIST = 4
    }

    private val TAG = javaClass.simpleName
    var dataList: ArrayList<Dataset> = arrayListOf()


    init {
//        dataList = _dataList
        dataList = ArrayList<Dataset>()
        dataList.add(Dataset(1))
    }

    override fun onBindViewHolder(holder: CalendarItemDateListHolder, position: Int) {

        // list_item_calendar 높이 지정
//        val h = calendarDateListLayout.height / MAX_OF_LIST
//        holder.itemView.layoutParams.height = h
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarItemDateListHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_home_calendar_datelist, parent, false)
        return CalendarItemDateListHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    inner class Dataset(
        val example: Int
    )

    inner class CalendarItemDateListHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        private var num: Int = -1
        fun bind(data: Dataset) {
            num = data.example
        }
    }
}