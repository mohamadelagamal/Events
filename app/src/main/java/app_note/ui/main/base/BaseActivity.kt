package com.base

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import app_note.ui.R
import app_note.ui.fragment.list.Fragment_List
import app_note.ui.fragment.setting.Fragment_Setting
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView

abstract class BaseActivity <DB : ViewDataBinding, VM : BaseViewModel<*>> :AppCompatActivity()  {
    lateinit var viewDataBinding: DB
    lateinit var viewModel: VM
    lateinit var addButton : FloatingActionButton
    val Todo_Frame_List=Fragment_List()
    val Todo_Frame_Setting=Fragment_Setting()
    lateinit var item_navigation : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this,getLayoutID())
        viewModel=makeViewModelProvider()
        //subscribToLiveData()
         item_navigation = findViewById(R.id.NavigationHome)
        addButton=findViewById(R.id.FloatingActionButton)
    }
    lateinit var view:View
    private fun subscribToLiveData() {
        viewModel.messageLiveData.observe(this,{
            showAlertDialog(it)
        })
       viewModel.showLoading.observe(this,{
           if (it){
               showLoading()
           }
           else{
               hideLoading()
           }
       })
    }
//    private fun pushFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction().replace(R.id.FrameLayoutHome,fragment).commit()
//    }
//    fun makeItemNavigation(){
//        item_navigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
//
//            when (it.itemId) {
//                R.id.Setting_Navigation-> {
//                    pushFragment(Fragment_Setting())
//                }
//                R.id.Details_Navigation -> {
//                    pushFragment(Fragment_List())
//                }
//            }
//            return@OnItemSelectedListener true
//        })
//        item_navigation.selectedItemId=R.id.Details_Navigation
//    }
    private fun showAlertDialog(message:String) {
    MaterialAlertDialogBuilder(this).setMessage(message).setPositiveButton("yes"){dialog,which->
        dialog.dismiss()
    }.show()
    }
    var progressDialog : ProgressDialog?=null
    private fun showLoading() {
        progressDialog= ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        progressDialog?.show()
    }
    private fun hideLoading() {
        progressDialog?.dismiss()
        progressDialog=null
    }
    abstract fun getLayoutID (): Int
    abstract fun makeViewModelProvider (): VM

}