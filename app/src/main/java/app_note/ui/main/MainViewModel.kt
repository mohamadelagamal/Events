package app_note.ui.main
import com.base.BaseViewModel

class MainViewModel: BaseViewModel<Navigator>() {
    fun addButton(){
    navigator?.showFragmentSheet()
    }
    fun movingPage(){
    navigator?.movingFragment()
    }
}