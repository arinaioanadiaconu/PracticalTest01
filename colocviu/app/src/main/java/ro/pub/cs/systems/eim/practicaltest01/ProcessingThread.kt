package ro.pub.cs.systems.eim.practicaltest01

import android.content.Context
import android.content.Intent
import android.util.Log
import kotlin.math.sqrt
import kotlin.random.Random

class ProcessingThread
    (
    private val context: Context,
    input1: Int,
    input2: Int
    ): Thread() {

    private var running = true
    private val avg = (input1 + input2) / 2
    private val geo = sqrt((input1 * input2).toDouble())

    override fun run() {
        while (running) {
            sleep()
            sendBroadcastMessage()
        }
    }

    private fun sleep() {
        try {
            sleep(Constants.SLEEP)
        } catch (interruptedException: InterruptedException) {
            Log.d("ProcessThread", "Thread has stopped!")
        }
    }

    private fun sendBroadcastMessage() {
        val intent = Intent()
        intent.action = Constants.actionTypes[Random.nextInt(Constants.actionTypes.size)]
        intent.putExtra(Constants.BROADCAST_RECEIVER_DATA,
            "Average: $avg, Geometric: $geo, Date: ${System.currentTimeMillis()}, Time: ${System.nanoTime()}")
        context.sendBroadcast(intent)
    }

    fun stopThread() {
        running = false
    }
}