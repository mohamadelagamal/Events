package app_note.ui.fragment.setting.spinner

data class Mode(
    var id:String?=null,
    var name:String?=null
){
    companion object{
        const val Light = "Light"
        const val Dark ="Dark"
        fun ID (mode: String):Mode{
            when(mode){
                Light ->{
                    return Mode(Light, name = "Light")
                }
                Dark ->{
                    return Mode(Dark, name = "Dark")
                }
                else ->{
                    return Mode(Dark, name = "Dark")
                }
            }
        }
        // set items in List
        fun getID():List<Mode>{
            return listOf(
                ID(Light),
                ID(Dark)
            )
        }
        //... item Show
        fun showItem(item:String):Mode{
            when(item){
                Light->{
                    return Mode(Light, name = "Light")
                }
                Dark->{
                    return Mode(Dark, name = "Dark")
                }
                else->{
                    return Mode(Light, name = "Light")
                }
            }
        }
    }

}
