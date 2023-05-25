package app.bhargav.workoutapp
//
object Constants {

    fun defaultExerciseModel():ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()

        val strechingExer = ExerciseModel(
            1,
            "Streching",
            R.drawable.streching,
            false,
            false
        )
        exerciseList.add(strechingExer)

        val lungesExer = ExerciseModel(
            2,
            "lunges",
            R.drawable.lunges,
            false,
            false
        )
        exerciseList.add(lungesExer)

        val squatExer = ExerciseModel(
            3,
            "Squats",
            R.drawable.squats,
            false,
            false
        )
        exerciseList.add(squatExer)
        val pushUpExer = ExerciseModel(
            4,
            "Push-Up",
            R.drawable.pushup,
            false,
            false
        )
        exerciseList.add(pushUpExer)

        val pullUpExcer = ExerciseModel(
            5,
            "Pull up",
            R.drawable.pullup,
            false,
            false
        )
        exerciseList.add(pullUpExcer)

        val absExer = ExerciseModel(
            6,
            "abs",
            R.drawable.abs,
            false,
            false
        )
        exerciseList.add(absExer)

        val runningExer = ExerciseModel(
            7,
            "Running",
            R.drawable.running,
            false,
            false
        )
        exerciseList.add(runningExer)


        val mountExer = ExerciseModel(
            8,
            "Mountian CLimbing",
            R.drawable.mountainclimbing,
            false,
            false
        )
        exerciseList.add(mountExer)

        val liftingExer = ExerciseModel(
            9,
            "Lifting",
            R.drawable.lifting,
            false,
            false
        )
        exerciseList.add(liftingExer)

        val plankExercise = ExerciseModel(
            10,
            "Plank",
            R.drawable.plank,
            false,
            false
        )
        exerciseList.add(plankExercise)


        return exerciseList

    }
}