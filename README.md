# Restaurant-Simulation

 Restaurant 6431 is now open.  A restaurant requires careful coordination of resources, which are the tables in the restaurant, the cooks available to cook the order, and the machines available to cook the food (we assume that there is no contention on other possible resources, like servers to take orders and serve the orders). 
 
 In this project, you will write a simulation for a restaurant. The simulation has four sets of parameters - the number of  eaters (diners) that wish to enter the restaurant, the times they approach the restaurant and what they order, the  number  of  tables  in  the  restaurant  (there    can  only  be  as  many  eaters  as  there  are  tables  at  a  time; other eaters  must wait for a free table before placing their order), and the number of cooks in the kitchen that process orders.Eaters in the restaurant place their orders when they get seated. These orders are then handled by available cooks. Each cook handles one order at a time. A cook handles an order by using machines  to  cook  the  food  items.  To  simplify  the  simulation,  we  make  the  following assumptions. There are only three types of food served by the restaurant - a Buckeye Burger, Brutus Fries, and Coke. Each person entering the restaurant occupies one table and orders one or more burgers, zero or more orders of fries, and zero or one glass of coke. The cook needs to use the burger machine for 5 minutes to prepare each burger, fries machine for 3 minutes for one order of fries, and the soda machine for 1 minute to fill a glass with coke. The cook can use at most one of these three machines at any given time. There is only one machine of each type in the restaurant (so, for example, over a 5-minute duration, only one burger can be prepared).  Once the food (all items at the same time) are brought to a diner's table, they take exactly 30 minutes to finish eating them, and then leave the restaurant, making the table available for another diner (immediately).  
 
 Input:  Diners arrive at the restaurant over a 120 minute duration, which is taken as input by the simulation. If  there  are  N  diners  arriving,  the  input  has    N+3  lines,  specifying,  in  order:  number  of  diners  (N),    number  of  tables,  number  of  cooks,  and  N  other  lines  each  with  the  following  four  numbers:  a  number  between  0  and  120  stating  when  the  diner  arrived  (this  number  is  increasing  across  lines),  the  number  of  burgers  ordered  (1  or  higher),  number  of  order of fries  (0 or higher), and whether or not coke was ordered (0 or 1).
 
Steps to run the code:

1. Run 'make' to create the jar file. 
2. Run 'java -jar Restaurant.jar <input-file-name>' to check the output

Assumptions:

1. Every 100 milliseconds represents 1 minute in the simulation.
2. Inputs are assumed to be correct as given in the question, else error is thrown.
