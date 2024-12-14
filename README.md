<!--
This is the readme section.
Use this whenever possible to add or subtract
any information necessary for the full project,
such as project description. 

Here are some ideas to get you started and add to this comment section:

- 🔭 I’m currently working on ...
- 🌱 I’m currently learning ...
- 👯 I’m looking to collaborate on ...
- 🤔 I’m looking for help with ...
- 💬 Ask me about ...
-->

# JavaTrainer

JavaTrainer aims to empower individuals in their fitness journeys by providing a comprehensive and user-friendly tool for tracking workouts. Many individuals face challenges in maintaining a consistent workout routine.

The Frame class is the GUI interface.
## Group Members and Respective Roles

### Justin Eugene
1) Provided the data for all workouts: Compiled and organized comprehensive workout data, including exercise descriptions, required tools, and difficulty levels for various workout categories. This data was structured for easy integration with the
database to ensure accurate retrieval and user-specific recommendations.
2) Developed the WorkoutPlanBuilder class: Designed and implemented the algorithm within the WorkoutPlanBuilder class to generate personalized workout plans for each user dynamically. The class considers user preferences, fitness levels, goals, and available equipment to curate effective, well-balanced workout routines.
3) Created the WorkoutPlan class: Developed a class to hold and manage the workout plan objects. This class organizes the workout schedule, and exercise details, and tracks progress throughout the plan. It integrates seamlessly with the database and the WorkoutPlanBuilder class to ensure user-specific workout plans are updated and accessible.
### Jake Salter -  Database
1) Designed the schema for the database, as well as writing the SQL code for database creation. Also installed the JDBC driver for the app to interact with the database for user authentication, workout recommendation, and progress tracking.
2) Created a class for password hashing and salting. This allows JavaTrainer to securely store a hash instead of a literal text password.
3) Created CurrentUserSession class to instantiate a current user with database information in a single class which can be called anywhere.
4) Created the getExercise method, which returns an ArrayList of exercise objects from the database. Queried based on the level, body part, and type.
### Tony Nguyen - Connections
1) Completed Algorithm and Backend integrations
2) Debugged GUI for Start Up Screen
3) Debugged Connection Issues arising from GUI with WorkoutRecommendation Class
### Yamen Abogamiza
1) Created welcome page
2) Created login/register page
3) Created questionnaire
4) Created screens for all 7 days
5) Created back-and-forth buttons to traverse back and forth between the screens at the user’s discretion
