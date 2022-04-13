package com.example.kotlincatchthekenny_49m

import android.widget.GridLayout

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.iterator
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    var score=0

    var handler= Handler()
    var runnable= Runnable {  }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        hideImages()

        object: CountDownTimer(15000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                timeText.text="Time: ${millisUntilFinished/1000}"
            }

            override fun onFinish() {
                timeText.text= "Time: 0"
                handler.removeCallbacks(runnable)
                for(image in gridLayout){
                    image.visibility= View.INVISIBLE
                }

                var alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Do you want to play again?")
                alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    val intent = intent
                    finish()
                    startActivity(intent)
                })
                alert.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this@MainActivity, "Game Over", Toast.LENGTH_LONG).show()
                })

                alert.show()


            }

        }.start()

    }

    fun increaseScore(view: View){
        score=score+1
        scoreText.text="Score: ${score}"


    }





    fun hideImages(){

        runnable= object: Runnable{
            override fun run() {
                for(image in gridLayout){

                    image.visibility= View.INVISIBLE
                }



                val random= Random()
                val rn1= random.nextInt(3)
                val rn2=random.nextInt(3)

                var parameters = GridLayout.LayoutParams(view.layoutParams)
                parameters.rowSpec = GridLayout.spec(rn1,3)
                parameters.columnSpec = GridLayout.spec(rn2,3)


                val view: View
                view= imageView1

                
                view.layoutParams = parameters
                view.visibility= View.VISIBLE

                handler.postDelayed(runnable, 500)
            }

        }
        handler.post(runnable)



    }
}