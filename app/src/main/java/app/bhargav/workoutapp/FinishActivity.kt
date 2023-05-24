package app.bhargav.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.bhargav.workoutapp.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {
    private var binding : ActivityFinishBinding ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.tvToolBar)   // To set the action bar in excercise activity

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)    // to set back option in navigation bar
        }
        binding?.tvToolBar?.setNavigationOnClickListener {
            onBackPressed()                                              // it will go back to the previous screen
        }


        binding?.btnFinish?.setOnClickListener {
            finish()
        }


    }
}