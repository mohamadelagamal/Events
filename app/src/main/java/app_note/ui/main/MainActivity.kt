package app_note.ui.main

import app_note.ui.fragment.list.Fragment_List
import app_note.ui.fragment.setting.Fragment_Setting
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app_note.ui.R
import app_note.ui.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView
import app_note.ui.fragment.add.FrameDialoge_Add
import com.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vmHome=viewModel
        viewModel.navigator=this
        movingFragment()
        showFragmentSheet()
    }
   val AddBottonSheet= FrameDialoge_Add()
    override fun showFragmentSheet() {
      addButton.setOnClickListener {
        AddBottonSheet.show(supportFragmentManager,"")
        AddBottonSheet.onTodoAddedListener=object: FrameDialoge_Add.OnTodoAddedListener {
            override fun onTodoAdded() {
                // Refresh to Todos list From database inside listFragment
                //... to make refresh list you should take object From Fragment Layout such val todo list = Fragment List
                if (Todo_Frame_List.isVisible)
                    Todo_Frame_List.getTodoFromDatabase()
            }
        }}
    }

    override fun movingFragment() {
        item_navigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
            when (it.itemId) {
                R.id.Setting_Navigation-> {
                    pushFragment(Todo_Frame_Setting)
                }
                R.id.Details_Navigation -> {
                    pushFragment(Todo_Frame_List)
                }
            }
            return@OnItemSelectedListener true
        })
        item_navigation.selectedItemId=R.id.Details_Navigation
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun makeViewModelProvider(): MainViewModel {
        return ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.FrameLayoutHome,fragment).commit()
    }

}