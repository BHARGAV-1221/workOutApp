package app.bhargav.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import app.bhargav.workoutapp.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private var binding : ActivityExerciseBinding ?= null // binding to excercisebinding cml

    private var restTimer : CountDownTimer ?= null      // Calling count down timer
    private  var restProgress = 0

    private var restTimerExcercise : CountDownTimer ?= null
    private  var restProgressExcercise = 0

    private var exerciseList : ArrayList<ExerciseModel>? = null     // getting Exercise Model to a array list variable

    private var currentExercisePostion = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)    //activity_excerxise layout

        setSupportActionBar(binding?.toolBarExcercise)   // To set the action bar in excercise activity

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)    // to set back option in navigation bar
        }


        exerciseList = Constants.defaultExerciseModel()     // Creating exerciselist using Constant object
        binding?.toolBarExcercise?.setNavigationOnClickListener {
            onBackPressed()                                              // it will go back to the previous screen
        }

//setRestProgressBar()
        setUpRestView()


    }


    private  fun setUpRestView(){

        binding?.flRestView?.visibility = View.VISIBLE    // we are Visibling the first Frame layout
        binding?.textviewtitle?.visibility = View.VISIBLE       // setting the text for second frame layout
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility=View.INVISIBLE      //// we are Invisibling  the second frame layout
        binding?.ivImage?.visibility= View.INVISIBLE
        if(restTimer!= null){
            restTimer?.cancel()       // when this is not an this activity we are starting the value from 0
            restProgress= 0
        }

        setRestProgressBar()
    }

    private  fun setUpExcerciseView(){
       binding?.flRestView?.visibility = View.INVISIBLE    // we are invisibleing the first Frame layout
        binding?.textviewtitle?.visibility = View.INVISIBLE       // setting the text for second frame layout
       binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility=View.VISIBLE      //// we are visibling  the second frame layout
      binding?.ivImage?.visibility= View.VISIBLE

        if(restTimerExcercise!= null){
            restTimerExcercise?.cancel()     // when this is not an this activity we are starting the value from 0
            restProgressExcercise= 0
        }


        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePostion].getImage())   // Setting the image part
        binding?.tvExerciseName?.text = exerciseList!![currentExercisePostion].getName()        // Setting the text part

        setExcerciseRestProgressBar()


    }

    private fun setRestProgressBar(){
        binding?.progressbar?.progress= restProgress

        restTimer = object :CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {
                restProgress++   // for countdowninterval what needs to happen we need to implement that
            binding?.progressbar?.progress = 10 - restProgress
                binding?.texttimer?.text = (10- restProgress).toString()

            }

            override fun onFinish() {
                // after this countdown finish we are calling setupexcerciseview method
                currentExercisePostion++
                setUpExcerciseView()
            }

        }.start()
    }

    private fun setExcerciseRestProgressBar(){
        binding?.progressbarExcercise?.progress= restProgressExcercise

        restTimerExcercise = object :CountDownTimer(30000,1000){

            // for countdowninterval what needs to happen we need to implement in below method
            override fun onTick(p0: Long) {
                restProgressExcercise++      // incrementing the value
                binding?.progressbarExcercise?.progress = 30 - restProgressExcercise            // subtartcting by 30 with incremanted value above
                binding?.texttimerExcercise?.text = (30- restProgressExcercise).toString()

            }

            override fun onFinish() {
//                Toast.makeText(
//                    this@ExerciseActivity,
//                    "30 Secs Over, will go to rest View",
//                    Toast.LENGTH_LONG
//                ).show()

                if(currentExercisePostion< exerciseList?.size!! -1){
                    setUpRestView()                // valindating how many more exercises left based on that calling setRestView method if all over then showing toast
                }else{
                    Toast.makeText(
                    this@ExerciseActivity,
                    "Congrats your Exercise done",
                    Toast.LENGTH_LONG
                ).show()
                }
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(restTimer!= null){
            restTimer?.cancel()    // cancelling restime time when its not null if this activity destroyed
            restProgress= 0     // setting value to 0 if activity destroyed
        }

        if(restTimerExcercise!= null){
            restTimerExcercise?.cancel()   // cancelling restTimerExcercise time when its not null if this activity destroyed
            restProgressExcercise= 0       // setting value to 0 if activity destroyed
        }
        binding = null


        // We need to binidng as null in onDestroy must and should
    }
}