package ro.pub.cs.systems.eim.practicaltest01

object Constants {
    const val INPUT1 = "input1"
    const val INPUT2 = "input2"

    val actionTypes = arrayOf(
        "ro.pub.cs.systems.eim.practicaltest01.arithmeticmean",
        "ro.pub.cs.systems.eim.practicaltest01.geometricmean",
        "ro.pub.cs.systems.eim.practicaltest01.harmonicmean"
    )

    const val BROADCAST_RECEIVER_DATA = "broadcast_data"
    const val BROADCAST_RECEIVER_TAG = "[broadcast]"

    const val CHANNEL_ID = "running_channel"
    const val SLEEP = 10000L
    const val THRESHOLD = 5

}