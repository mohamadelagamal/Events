package Err.One

import Err.One.FragmentHome.Fragment_List
import Err.One.FragmentHome.Fragment_Setting
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView
import com.route.todo_c35_sat.FrameDialoge_Add

class MainActivity : AppCompatActivity() {
    lateinit var MainNavigation:BottomNavigationView
    lateinit var addButton :FloatingActionButton
    val Todo_Frame_List=Fragment_List()
    val Todo_Frame_Setting=Fragment_Setting()
    val AddBottonSheet= FrameDialoge_Add()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        IntionlazingItems()
       MainNavigation.selectedItemId=R.id.Details_Navigation

    }
    private fun IntionlazingItems() {
        MainNavigation=findViewById(R.id.NavigationHome)
        addButton=findViewById(R.id.FloatingActionButton)
        NavigationItems()
        FloatingFragmentDilago()

    }

    private fun FloatingFragmentDilago() {
        addButton.setOnClickListener{
        val addBottomSheet=FrameDialoge_Add()
            AddBottonSheet.show(supportFragmentManager,"")
            AddBottonSheet.onTodoAddedListener=object:FrameDialoge_Add.OnTodoAddedListener{
                override fun onTodoAdded() {
                   // Refresh to Todos list From database inside listFragment
                    if (Todo_Frame_List.isVisible)
                    Todo_Frame_List.getTodoFromDatabase()
                }
            }
        }

    }

    private fun NavigationItems() {
        MainNavigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {

            if (it.itemId==R.id.Setting_Navigation){
                pushFragment(Todo_Frame_Setting)
            }
            else if (it.itemId==R.id.Details_Navigation){
                pushFragment(Todo_Frame_List)
            }
            return@OnItemSelectedListener true
        })
    }

    private fun pushFragment(fragment:Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.FrameLayoutHome,fragment).commit()
    }
}