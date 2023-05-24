package app.bhargav.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import app.bhargav.workoutapp.databinding.ActivityExerciseBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var binding : ActivityExerciseBinding ?= null // binding to excercisebinding cml

    private var restTimer : CountDownTimer ?= null      // Calling count down timer
    private  var restProgress = 0

    private var restTimerExcercise : CountDownTimer ?= null
    private  var restProgressExcercise = 0

    private var exerciseList : ArrayList<ExerciseModel>? = null     // getting Exercise Model to a array list variable

    private var currentExercisePostion = -1

    private var tts :TextToSpeech ? = null

    private var exerciseAdapter : ExercisStatusAdapter ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)    //activity_excerxise layout

        setSupportActionBar(binding?.toolBarExcercise)   // To set the action bar in excercise activity

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)    // to set back option in navigation bar
        }


        exerciseList = Constants.defaultExerciseModel()     // Creating exerciselist using Constant object
        tts = TextToSpeech(this, this)
        binding?.toolBarExcercise?.setNavigationOnClickListener {
            onBackPressed()                                              // it will go back to the previous screen
        }

//setRestProgressBar()
        setUpRestView()
        setUpExerciseStatusRecyclerView()


    }

    private fun setUpExerciseStatusRecyclerView() {
     binding?.rvExerciseStatus?.layoutManager =
         LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        exerciseAdapter = ExercisStatusAdapter(exerciseList!!)
        binding?.rvExerciseStatus?.adapter= exerciseAdapter
    }

    private  fun setUpRestView(){

        binding?.flRestView?.visibility = View.VISIBLE    // we are Visibling the first Frame layout
        binding?.textviewtitle?.visibility = View.VISIBLE       // setting the text for second frame layout
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility=View.INVISIBLE      //// we are Invisibling  the second frame layout
        binding?.ivImage?.visibility= View.INVISIBLE
        binding?.tvUpComingLabel?.visibility= View.VISIBLE
        binding?.tvUpCOmingExercise?.visibility= View.VISIBLE

        if(restTimer!= null){
            restTimer?.cancel()       // when this is not an this activity we are starting the value from 0
            restProgress= 0
        }

        speakOut("Please Rest for 10 seconds")
        binding?.tvUpCOmingExercise?.text = exerciseList!![currentExercisePostion +1].getName()        // Here we are setting next exercise name
        setRestProgressBar()
    }

    private  fun setUpExcerciseView(){
       binding?.flRestView?.visibility = View.INVISIBLE    // we are invisibleing the first Frame layout
        binding?.textviewtitle?.visibility = View.INVISIBLE       // setting the text for second frame layout
       binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility=View.VISIBLE      //// we are visibling  the second frame layout
      binding?.ivImage?.visibility= View.VISIBLE
        binding?.tvUpComingLabel?.visibility= View.INVISIBLE
        binding?.tvUpCOmingExercise?.visibility= View.INVISIBLE

        if(restTimerExcercise!= null){
            restTimerExcercise?.cancel()     // when this is not an this activity we are starting the value from 0
            restProgressExcercise= 0
        }

       speakOut("Your Exercise is "+exerciseList!![currentExercisePostion].getName())    // setting exercise name to speakout methif
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

                exerciseList!![currentExercisePostion].setIsSelected(true)     // Setting the isSelected method to true since it not yet completed
                exerciseAdapter?.notifyDataSetChanged()       // to notify the excercise adapter
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
                exerciseList!![currentExercisePostion].setIsSelected(false)       // setting is selected method to false since the current exercise is completed
                exerciseList!![currentExercisePostion].setIsCompleted(true)          // Setting isCompeted methos to true since the current exercide is complted
                exerciseAdapter?.notifyDataSetChanged()
                if(currentExercisePostion< exerciseList?.size!! -1){
                    setUpRestView()                // valindating how many more exercises left based on that calling setRestView method if all over then showing toast
                }else{
                    Toast.makeText(
                    this@ExerciseActivity,
                    "Congrats your Exercise done",
                    Toast.LENGTH_LONG
                ).show()
                    speakOut("Congrats your Done with your Exercise")
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

        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        binding = null


        // We need to binidng as null in onDestroy must and should
    }


    // InBuilt method for speck API that will will give language and success anf failure cae
    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts?.setLanguage(Locale.ENGLISH)    // Setting english Language

            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","The Language specified is not supported!")
            }else{
                Log.e("TTS","Intialization Failed")
            }
        }
    }

    
    
    // Method to speak the text
    private fun speakOut(text:String){
        tts?.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }
}