#  ROBOT

 - You should implement a Robot class in Java
 - The robot should have the configurable number of legs
 - Each leg should make a step and print to the stdout something like “Robot moved with leg 1”
 - Every leg should move in a separate thread (should be associated with a separate thread)
 - The steps should be done in the same order, as legs are defined, e.g.

    OK:
    - Robot moved with leg 1
    - Robot moved with leg 2
    - Robot moved with leg 3

    NOK:
    - Robot moved with leg 1
    - Robot moved with leg 3
    - Robot moved with leg 2

 - The robot should have the distance counter. Each step should update the counter and once the distance
   is reached, the program should exit and print the number of steps done
 - Please implement a random step calculator as well (average step is one meter, but in should vary
   from 0,5 to 1,5 meters)
 - Implement an inflight configuration of robot legs as well (can be done with UI or configuration files,
   it is up to you. The user should be able to remove or add legs for a running robot.)
 - Program input parameters should be the initial quantity of legs and distance (in meters)