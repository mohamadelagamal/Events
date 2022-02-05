package Err.One.FragmentHome

import Err.One.DataBase.clearTime
import Err.One.R
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.route.todo_c35_sat.database.MyDataBase
import java.util.*

class Fragment_List: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list,container,false)
    }
    lateinit var recyclerView: RecyclerView
    //make object about calander
    lateinit var calendarView:MaterialCalendarView
    val adatpter=Todo_Recyecler_Adapter_List(null)
    override fun onResume() {
        super.onResume()
        getTodoFromDatabase()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialzationItem()
    }

    var date=Calendar.getInstance()


 fun getTodoFromDatabase() {
//     clearCalendarTime()

   // date.clear()

    val todoList=MyDataBase.getInstance(requireContext())
            //.todoDao().getAllTodos()
            .todoDao().getTodosByDate(date.clearTime().time)
        adatpter.ChangeData(todoList.toMutableList())    }

//    private fun clearCalendarTime() {
//        date.clear(Calendar.HOUR)
//        date.clear(Calendar.MILLISECOND)
//        date.clear(Calendar.SECOND)
//        date.clear(Calendar.MINUTE)
//    }

    private fun initialzationItem() {
        recyclerView=requireView().findViewById(R.id.RecyclerHome)
        recyclerView.adapter=adatpter
        // findViewById to calender
        calendarView=requireActivity().findViewById(R.id.calendarView_Home)
        calendarView.selectedDate= CalendarDay.today();

        calendarView.setOnDateChangedListener { widget, calenderDay, selected ->
            Log.e("calender day of monthe",""+calenderDay.month)
            date.set(Calendar.DAY_OF_MONTH,calenderDay.day)
            date.set(Calendar.MONTH,calenderDay.month-1)
            date.set(Calendar.YEAR,calenderDay.year)
            getTodoFromDatabase()
        }

    }
}