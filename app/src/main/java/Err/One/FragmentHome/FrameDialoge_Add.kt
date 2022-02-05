package com.route.todo_c35_sat

import Err.One.DataBase.clearTime
import Err.One.R
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import com.route.todo_c35_sat.database.MyDataBase
import com.route.todo_c35_sat.database.model.Todo
import java.util.*

class FrameDialoge_Add : BottomSheetDialogFragment() {
    lateinit var titleLayout: TextInputLayout
    lateinit var detailsLayout: TextInputLayout
    lateinit var chooseDate: TextView
    lateinit var addTodo: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.frame_add,
            container, false
        );
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        titleLayout = requireView().findViewById(R.id.title_layout)
        detailsLayout = requireView().findViewById(R.id.details_layout)
        addTodo = requireView().findViewById(R.id.AddBottom_todo)
        chooseDate = requireView().findViewById(R.id.Chose_Date)
        chooseDate.setText("" + calendar.get(Calendar.DAY_OF_MONTH) + "/" +
                (calendar.get(Calendar.MONTH) + 1) + "/" +
                calendar.get(Calendar.YEAR)
        )


        chooseDate.setOnClickListener {
            showDatePicker()
        }
        addTodo.setOnClickListener {
            if (validateForm()) {
                // form is valid and insert Todo item
                val title = titleLayout.editText?.text.toString();
                val details = detailsLayout.editText?.text.toString();
                InsertTodo_DataBase(title,details)
              MyDataBase.getInstance(requireContext())

            }

        }
    }

    private fun InsertTodo_DataBase(title : String,details:String) {
        val entity_class=Todo(name =title, details = details, date = calendar.clearTime().time)
        MyDataBase.getInstance(requireContext().applicationContext)
            .todoDao().addTodo(entity_class)
        Toast.makeText(requireContext(), "Todo added successfully", Toast.LENGTH_LONG)
            .show();
        // call back to activity to notify insertion
        onTodoAddedListener?.onTodoAdded()
        dismiss()
    }


    var onTodoAddedListener: OnTodoAddedListener? = null;

    interface OnTodoAddedListener {
        fun onTodoAdded();
    }

    fun validateForm(): Boolean {
        var isValid = true;
        if (titleLayout.editText?.text.toString().isBlank()) {
            titleLayout.error = "please enter todo title";
            isValid = false;
        } else {
            titleLayout.error = null
        }
        if (detailsLayout.editText?.text.toString().isBlank()) {
            detailsLayout.error = "please enter todo details";
            isValid = false;
        } else {
            detailsLayout.error = null
        }
        return isValid;
    }

    val calendar = Calendar.getInstance()
    fun showDatePicker() {

        val datePicker = DatePickerDialog(
            requireContext(),
            { p0, year, month, day ->
                calendar.set(Calendar.DAY_OF_MONTH, day)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.YEAR, year)
                chooseDate.setText("" + day + "/" + (month + 1) + "/" + year)

            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()

    }

}