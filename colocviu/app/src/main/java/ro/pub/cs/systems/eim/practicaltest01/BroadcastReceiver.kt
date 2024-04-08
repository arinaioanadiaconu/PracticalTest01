package ro.pub.cs.systems.eim.practicaltest01

import android.util.Log
import android.content.BroadcastReceiver

class BroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: android.content.Context?, intent: android.content.Intent?) {
        intent?.let {
            Log.d(Constants.BROADCAST_RECEIVER_TAG, it.action.toString())
            Log.d(Constants.BROADCAST_RECEIVER_TAG, it.getStringExtra(Constants.BROADCAST_RECEIVER_DATA).toString())
        }
    }
}