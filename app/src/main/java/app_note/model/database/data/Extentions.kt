package app_note.model.database

import java.util.*


fun Calendar.clearTime():Calendar{
    this.clear(Calendar.SECOND)
    this.clear(Calendar.MINUTE)
    this.clear(Calendar.MILLISECOND)
    this.clear(Calendar.HOUR)
    return this
}