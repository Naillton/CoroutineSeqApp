package com.example.coroutineseqapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.coroutineseqapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Aqui as nossas funcoes definidas iram executar de forma sequencial
        // ou seja uma sera executada apenas depois da outra
        // nesse caso apos 5 segundos depois da inciacao do app a funcao use1 executa e depois de 7
        // segundos a funcao use2 ira executar.
        CoroutineScope(Dispatchers.IO).launch {
            val one = use1()
            val two = use2()
            val result = one + two

            Log.v("TAGY", "The result is $result")
        }
    }

    private suspend fun use1(): Int {
        delay(5000)
        Log.v("TAGY", "FUNCTION COMPLETE")
        return 11
    }

    private suspend fun use2(): Int {
        delay(7000)
        Log.v("TAGY", "FUNCTION 2 COMPLETE")
        return 8
    }
}