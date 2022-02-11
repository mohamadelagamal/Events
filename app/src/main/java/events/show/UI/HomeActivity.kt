package events.show.UI

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import events.show.R
import events.show.UI.ui.FragmentSettings
import events.show.UI.ui.categories.Categories
import events.show.UI.ui.categories.CategoriesFragment
import events.show.UI.ui.news.NewsFragment

class HomeActivity : AppCompatActivity() {
        lateinit var drawerButtonMenu:ImageView
        lateinit var drawerButtonSettings:ImageView
        lateinit var drawerlayout:DrawerLayout
        lateinit var categories:View
        lateinit var settings:View
        lateinit var linearsetting:LinearLayout
        lateinit var linearMenu:LinearLayout

    val categories_fragment= CategoriesFragment()
        val settings_fragment=FragmentSettings()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        InatioalItems()
        pushFragment(categories_fragment)
        categories_fragment.onCategoryClikeListerner = object :CategoriesFragment.OnCategoryCLickListener {
            override fun onCategoryCLick(categories: Categories) {
               // pushFragment(NewsFragment())
                pushFragment(NewsFragment.getInstance(categories),true)
            }
        }
    }

    private fun InatioalItems() {
        drawerlayout=findViewById(R.id.drawer_layout)
        drawerButtonMenu=findViewById(R.id.menuAppbar)
        categories=findViewById(R.id.Categories)
        //settings=findViewById(R.id.Settings)
        linearMenu=findViewById(R.id.LinearHome)
        linearMenu.setOnClickListener{
            pushFragment(categories_fragment)
            drawerlayout.closeDrawers()
        }
        linearsetting=findViewById(R.id.LinearSettings)
        linearsetting.setOnClickListener{
            pushFragment(settings_fragment)
            drawerlayout.close()
        }
        drawerButtonSettings=findViewById(R.id.SettingAppBar)

        drawerButtonSettings.setOnClickListener{
            pushFragment(settings_fragment)
        }
        drawerButtonMenu.setOnClickListener{
            drawerlayout.open()
        }
        categories.setOnClickListener {
        pushFragment(categories_fragment)
            drawerlayout.closeDrawers()
        }
//        settings.setOnClickListener {
//        pushFragment(settings_fragment)
//            drawerlayout.close()
//        }
    }
    fun pushFragment(fragment:Fragment,addToBackStack:Boolean=false){
      val fragmentTransaction=  supportFragmentManager.beginTransaction().replace(R.id.Fragment_Container,fragment)
        if (addToBackStack)
            fragmentTransaction.addToBackStack("")
        fragmentTransaction.commit()
    }
}