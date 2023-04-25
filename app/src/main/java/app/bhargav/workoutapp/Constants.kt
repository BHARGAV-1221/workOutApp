package app.bhargav.workoutapp
//
object Constants {

    fun defaultExerciseModel():ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()
        val runningExer = ExerciseModel(
            1,
            "Running",
            R.drawable.running,
            false,
            false
        )
        exerciseList.add(runningExer)

        val absExer = ExerciseModel(
            2,
            "abs",
            R.drawable.abs,
            false,
            false
        )
        exerciseList.add(absExer)

        val liftingExer = ExerciseModel(
            3,
            "Lifting",
            R.drawable.lifting,
            false,
            false
        )
        exerciseList.add(liftingExer)

        val pushUpExer = ExerciseModel(
            4,
            "Push-Up",
            R.drawable.pushup,
            false,
            false
        )
        exerciseList.add(pushUpExer)
        val squatExer = ExerciseModel(
            5,
            "Squats",
            R.drawable.squats,
            false,
            false
        )
        exerciseList.add(squatExer)

        val strechingExer = ExerciseModel(
            6,
            "Streching",
            R.drawable.streching,
            false,
            false
        )
        exerciseList.add(strechingExer)

        return exerciseList

    }
}