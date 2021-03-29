package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() ,View.OnClickListener{


    var list:MutableList<String>?=mutableListOf("1", "2","3","4")
    private val UPDATE_TEXT = 1
    private val ERROR_TEXT = 2

    private val secondtolast_TEXT = 3

    private var index:Int? =0


    var mhandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                UPDATE_TEXT -> {
                    if (index == list!!.size-1){
                        text.text = list?.get(index!!)
                    }
                    else{
                        index  =   index?.plus(1);
                        text.text = list?.get(index!!)
                    }


                }
                ERROR_TEXT -> {
                    val obj = msg.obj;
                    Toast.makeText(applicationContext, obj.toString(), Toast.LENGTH_LONG).show()
                }

                secondtolast_TEXT -> {
                    text.text = list?.get(index!!)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initview()
    }

     fun initview() {
        text.text = list?.get(index!!)

         button_left.setOnClickListener(this)
         button_right.setOnClickListener(this)

     }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_left->{
                Thread(Runnable {
                    val message = Message.obtain()
                    val m = Message()
                    message.what = UPDATE_TEXT
                    mhandler.sendMessage(message)
                }).start()
            }
            R.id.button_right->{

                if (index == list!!.size-1){
                    Log.i("index",index!!.toString())
                    index =list!!.size-1
                    Thread(Runnable {
                        val message = Message.obtain()
                        val m = Message()
                        message.what = UPDATE_TEXT
                        mhandler.sendMessage(message)
                    }).start()
                }else if (index == list!!.size-2){
                    Log.i("index",index!!.toString())
                    val get = list!!.get(list!!.size - 2)

                    val get1 = list!!.get(list!!.size - 1)

                    list!!.set(list!!.size - 1, get)

                    list!!.set(list!!.size - 2, get1)
                    Log.i("indexstring",list!!.get(index!!).toString())
                    Thread(Runnable {
                        val message = Message.obtain()
                        val m = Message()
                        message.what = secondtolast_TEXT
                        mhandler.sendMessage(message)
                    }).start()

                }else {
                    val get = list!!.get(index!!)
                    if (index!!+2 > list!!.size){
                        list!!.add( list!!.get(index!!))
                        list!!.removeAt(index!!)
                    }else{
                        list!!.add(index!!+2, get)
                        list!!.removeAt(index!!)
                    }

                    Thread(Runnable {
                        val message = Message.obtain()
                        val m = Message()
                        message.what = UPDATE_TEXT
                        mhandler.sendMessage(message)
                    }).start()
                }

            }
        }
    }
}
