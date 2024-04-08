package ro.pub.cs.systems.eim.practicaltest01

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var input1 by mutableIntStateOf(0)
    var input2 by mutableIntStateOf(0)

    var sum by mutableIntStateOf(0)

    fun add() {
        sum = input1 + input2
    }

}