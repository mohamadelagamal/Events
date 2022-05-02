package app_note.ui.fragment.setting.spinner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import app_note.ui.R

class ModeSpinnerAdapter(val items:List<Mode>):BaseAdapter() {

    class viewHoder(val view: View){
        val title:TextView = view.findViewById(R.id.title_Spinner)
    }
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(p0: Int): Any {
       return items[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(index: Int, view: View?, container: ViewGroup?): View {
        var myView = view
        val holerView : viewHoder
        when(myView){
            null->{
                myView = LayoutInflater.from(container?.context).inflate(R.layout.item_spinner,container,false)
                holerView = viewHoder(myView)
                myView.setTag(holerView)
            }
            else->{
                holerView = myView.tag as viewHoder
            }
        }
        val itemList = items[index]
        holerView.title.setText(itemList.name)
        return myView!!
    }
}