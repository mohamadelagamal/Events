package app_note.ui.fragment.list

import app_note.model.database.clearTime
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import app_note.ui.R
import app_note.ui.databinding.FragmentListBinding
import app_note.ui.fragment.adapter.Todo_Recyecler_Adapter_List
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.route.todo_c35_sat.database.MyDataBase
import java.util.*

class Fragment_List: Fragment() {

    lateinit var viewModel:ListViewModel
    lateinit var viewDataBinding:FragmentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
      //  return inflater.inflate(R.layout.fragment_list,container,false)
        viewDataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_list,container,false)
        return viewDataBinding.root
    }

    lateinit var recyclerView: RecyclerView
    //make object about calender
    lateinit var calendarView:MaterialCalendarView
    val adatpter= Todo_Recyecler_Adapter_List(null)
    override fun onResume() {
        super.onResume()
        getTodoFromDatabase()
    }
    lateinit var makeDone:ImageView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialzationItem()
        //makeDone = requireActivity().findViewById(R.id.make_Done)
//        makeDone.setOnClickListener {
//            adatpter.onItemClickListener = object : Todo_Recyecler_Adapter_List.OnItemClick {
//                override fun onItemclike(pos: Int) {
//                makeDone.setImageResource(R.drawable.ic_action_name)
//                                }
//
//            }
//        }
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