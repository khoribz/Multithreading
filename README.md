### Task description

There are transport ships that sail from the “generator” to the berths for loading various kinds of goods. They pass through a narrow tunnel where only 5 ships can be at the same time. Passage through the tunnel takes some time (let's say 1 second for each ship. Since there can be 5 ships in the tunnel at the same time, this means that when 5 ships are released from the generator at the same time, they will pass through the tunnel in one second).   
Each ship is a separate java thread. Accordingly, the ship generator generates objects that implement the Runnable interface, this can be done via ExecutorPool.
There are 3 types of ships (with bread, with bananas and with clothes) and three types of capacity of 10, 50, 100 pcs. goods. 3 types of ships * 3 types of spaciousness = 9 different types of ships. Then there are 3 types of berths for loading ships — Bread, Banana and Clothes. Each berth takes or calls to itself the ship it needs and begins to load it. In one second, the berth loads 10 units of goods onto the ship. That is, if the ship has a capacity of 50 pcs., then the berth will load it in 5 seconds of its operation. The berth can only load one ship at a time.

### Task requirements:  
-Correctly divide the task into parallelism.  
-Synchronize streams, maintain data integrity. After all, it is not difficult to restrict the access of threads to a shared resource, and it is much more difficult to make them work in concert.  Checking the synchronization of streams can be done with logging tools (optionally, you can use the Logger class from java.util, or the usual system.out)  
-The operation of the ship generator should not depend on the operation of berths and vice versa.  
-The shared resource must be Thread Safe.  
-Threads should not be active if there are no tasks.  
-Threads should not keep mutex if there are no tasks.  