package recommend;

import login.User;

import java.util.ArrayList;
import java.util.Random;

public class WorkoutPlanBuilder {
    private Random random;
    private String level; // Global variable for workout level

    public WorkoutPlanBuilder() {
        this.random = new Random();
    }

    // Method to create a workout plan based on user's preferences
    public WorkoutPlan createWorkoutPlan(User user) {
    	this.level = user.getLevel();
    	
        // Initialize the week array (7 days) and fill each day with an ArrayList for exercises
        @SuppressWarnings("unchecked")
        ArrayList<Exercise>[] week = new ArrayList[7];
        for (int i = 0; i < 7; i++) {
            week[i] = new ArrayList<>();
        }
        
        int duration = 4; // Default duration for beginners
        String level = user.getLevel(); // User's experience level
        String goal = user.getGoals();  // User's fitness goal
        int frequency = user.getFrequency(); // User's workout frequency
        
        switch (goal) {
            case "Strength training":
                if (frequency == 6) {  // Push, Pull, Legs (x2)
                    addPush(week[0]); addPull(week[1]); addLegs(week[2]);
                    addPush(week[3]); addPull(week[4]); addLegs(week[5]);
                } else if (frequency == 5) {  // Push, Pull, Legs, Upper Body, Lower Body
                    addPush(week[0]); addPull(week[1]); addLegs(week[2]);
                    addFront(week[4]); addBack(week[5]);
                } else if (frequency == 4) {  // Push, Lower Body, Pull, Lower Body
                    addPush(week[0]); addLegs(week[1]); addPull(week[3]);
                    addLegs(week[4]); addStrengthExercises(week[4], "Abdominals");
                } else if (frequency == 3) {  // Push, Pull, Legs
                    addPush(week[0]); addPull(week[2]); addLegs(week[4]);
                } else if (frequency == 2) {  // Front, Back
                    addFront(week[1]); addBack(week[4]);
                }
                break;

            case "Weight loss":
                if (frequency == 6) {  // Push, Pull, Legs + Cardio (x2)
                    addPush(week[0]); addPull(week[1]);
                    addLegs(week[2]); addCardioExercises(week[2]);
                    addPush(week[3]); addPull(week[4]);
                    addLegs(week[5]); addCardioExercises(week[5]);
                } else if (frequency == 5) {  // Similar to Strength with additional cardio
                    addPush(week[0]); addPull(week[1]);
                    addLegs(week[2]); addCardioExercises(week[1]);
                    addFront(week[4]); addBack(week[5]);
                } else if (frequency == 4) {  // Push, Pull, Legs + Cardio
                    addPush(week[0]); addPull(week[2]);
                    addLegs(week[3]); addCardioExercises(week[3]);
                } else if (frequency == 3) {  // Full Body + Cardio
                    addPush(week[0]); addCardioExercises(week[2]);
                    addPull(week[4]); addLegs(week[6]);
                } else if (frequency == 2) {  // Full Body with Cardio
                    addFront(week[1]); addBack(week[4]);
                    addCardioExercises(week[1]); addCardioExercises(week[4]);
                }
                break;

            case "Endurance":
                if (frequency == 6) {  // Full Body, Cardio, Lower, Upper, Cardio, Full Body
                    addCircuit(week[0]); addCardioExercises(week[1]);
                    addLegs(week[2]); addFront(week[3]);
                    addCardioExercises(week[4]); addCircuit(week[5]);
                } else if (frequency == 5) {  // Alternate cardio and strength
                    addCircuit(week[0]); addCardioExercises(week[2]);
                    addPull(week[4]); addFront(week[6]);
                } else if (frequency == 4) {  // Cardio and circuits
                    addCardioExercises(week[1]); addCardioExercises(week[3]);
                    addCircuit(week[4]); addCircuit(week[6]);
                } else if (frequency == 3) {  // Circuit + Cardio
                    addCircuit(week[1]); addCardioExercises(week[3]);
                    addCircuit(week[5]);
                } else if (frequency == 2) {  // Full body with cardio
                    addCircuit(week[1]); addCardioExercises(week[4]);
                }
                break;

            default:
                System.out.println("Invalid goal");
                return null;
        }
        
        for(int i = 0; i < 7; i++) {
        	while(week[i].remove(null));
        }
        
        return new WorkoutPlan(week, duration);
    }

    
    // Helper methods to add exercises based on type
    private void addPush(ArrayList<Exercise> selectedExercises) {
    	addStrengthExercises(selectedExercises, "Shoulders", "Chest", "Triceps");    	
    }
    private void addPull(ArrayList<Exercise> selectedExercises) {
    	addStrengthExercises(selectedExercises, "Traps", "Biceps", "Middle Back", "Lower Back");
    }
	private void addLegs(ArrayList<Exercise> selectedExercises) {
    	addStrengthExercises(selectedExercises, "Quadriceps", "Hamstrings", "Calves", "Glutes");
    }   
    private void addFront(ArrayList<Exercise> selectedExercises) {
    	addStrengthExercises(selectedExercises, "Chest", "Abdominals", "Quadriceps", "Triceps", "Glutes");
    }
    private void addBack(ArrayList<Exercise> selectedExercises) {
    	addStrengthExercises(selectedExercises, "Traps", "Hamstrings", "Lats", "Lower Back", "Middle Back");
    }
    private void addCircuit(ArrayList<Exercise> selectedExercises) {
    	addStrengthExercises(selectedExercises, "Abdominals", "Abdominals", "Abdominals");
    }
    
    
    private void addStrengthExercises(ArrayList<Exercise> selectedExercises, String... categories) {
        for (String category : categories) {
            ArrayList<Exercise> exercises = GetExercises.get_exercises("Strength", category, this.level);
            selectedExercises.add(selectRandomExercise(exercises));
        }
    }

    private void addCardioExercises(ArrayList<Exercise> selectedExercises) {
        ArrayList<Exercise> cardioExercises = GetExercises.get_exercises("Cardio", "Quadriceps", this.level);
        selectedExercises.add(selectRandomExercise(cardioExercises));
    }

    private Exercise selectRandomExercise(ArrayList<Exercise> exercises) {
    	if(exercises.size() > 0) {
    		int index = random.nextInt(exercises.size());
            return exercises.get(index);
    	} else {
    		return null;
    	}
        
    }
}
