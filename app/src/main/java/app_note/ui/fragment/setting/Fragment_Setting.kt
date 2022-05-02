package app_note.ui.fragment.setting

import app_note.ui.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app_note.ui.databinding.FragmentSettingBinding
import app_note.ui.fragment.setting.spinner.Mode
import app_note.ui.fragment.setting.spinner.ModeSpinnerAdapter

class Fragment_Setting: Fragment() {

    lateinit var adapter:ModeSpinnerAdapter
    lateinit var viewDataBinding:FragmentSettingBinding
    lateinit var viewModel:SettingViewModel
    var mode = Mode.getID()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        return inflater.inflate(R.layout.fragment_setting,container,false)
        viewDataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_setting,container,false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ModeSpinnerAdapter(viewModel.categories)
        viewDataBinding.spinnerLayout.adapter = adapter
        viewDataBinding.spinnerLayout.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.selectedCategory = viewModel.categories[p2]


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SettingViewModel::class.java)
    }
}