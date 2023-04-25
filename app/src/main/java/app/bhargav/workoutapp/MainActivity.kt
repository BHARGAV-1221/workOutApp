package app.bhargav.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import app.bhargav.workoutapp.databinding.ActivityMainBinding


/*
Advatages of view binding
implemenatation is shorter and consize
its faster and effiecient , shorter time
specific asseccing the xml id's
findviewbyid will work for all xml file where view binding is for specific xml

 */

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding ?= null  // Declaring binding varible and importing ActivityMainBinding class
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)  //
        setContentView(binding?.root) // assiging the layout using bining and ActivityMainBinding class

    //    val flStartButton : FrameLayout = findViewById(R.id.flstart)   // no need to use findview by id if we use bidning feature
       // Using Binding Feature assign Frame layout variable suggested by google for further usage
        binding?.flstart?.setOnClickListener {
          val intent = Intent(this,ExerciseActivity::class.java)   // When click on start button navigate to Exercise activity
            startActivity(intent)
        }

    }


    // inbuild OnDestroy method
    override fun onDestroy() {
        super.onDestroy()

        binding = null // asigning null for bining after destroying
    }
}