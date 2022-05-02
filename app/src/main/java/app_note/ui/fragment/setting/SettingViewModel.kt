package app_note.ui.fragment.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app_note.ui.fragment.setting.spinner.Mode

class SettingViewModel:ViewModel() {
    var categories = Mode.getID()
    var selectedCategory =  categories[0]
    val roomAdded = MutableLiveData<Boolean>()


}