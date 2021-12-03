Ascential - Technical Test For Java Engineer

You have been provided with 3 classes as a basis for this technical test.
-	CustomNumberEntity.
        -	This object holds a number parsed from a Json model
-	FastestComparator
        -	A comparator used to sort CustomNumberEntity objects, which includes a random delay
-	NumberFinder
        -	An interface to be implemented

Objective: 
Write a class, which implements NumberFinder, to search a list of type CustomNumberEntity for a given int value. The search should be completed by the fastest means possible.

Constraints:
-	Your class MUST implement the provided NumberFinder interface.
-	The list of CustomNumberEntity values should be read from a Json file. An example Json structure is given on the next page.
-	The contains method of your implementation MUST use the provided FasterComparator.compare method to compare the int value with each CustomNumberEntity. How you do this in the fastest possible time is the key. FastestComparator.compare cannot be modified and no other comparison method should be used (hashing, indexes etc)
-	Do not cast or convert provided parameter types. The compare method from FastestComparator will handle this. e.g. do not cast from int to String, or CustomNumberEntity.number to int (even if is not used for comparison purpose)
-	You MUST include Junit tests for running your code.
-	Write your code using java 7 or 8

Solution

`NumberFinderImpl.java`
<br /><br /><br />
`public List<CustomNumberEntity> readFromFile(String filePath) {}`

To Read the JSON file using the `readFromFile()` method, the Example.json file is loaded from the resources folder and stored as a `String`.
The `String` is then converted to a `JSONArray` and each `Object` in the `JSONArray` is stored in an `CustomNumberEntity` added to a `List` and this `List` is what's returned.
<br /><br /><br />
`public boolean contains(int valueToFind, List<CustomNumberEntity> list) {}`

Two Ways were looked at to searching a `list` of type `CustomNumberEntity` for a given `int` value, A single Thread Approach and a MultiThreaded Approach

- Single Thread Approach
    - Create a class that extends Thread, in the `run()` method of this Thread call the `compare()` method for each `CustomNumberEntity` in the `list`.
    - In the `contains` method Create and Start the Thread Created.
    - While the Thread `isAlive()` call the `interrupt()` method, as the `compare()` method catches an `InterruptedException` the Thread will continue to execute, essentially ignoring the `sleep()`
    - `FastestComparator.compare` takes about 1 second using this approach.
    
- Multi Threaded Approach
    - Similar to the above approach, create a class that extends Thread, in the `run()` method of this Thread call the `compare()` method on only one `CustomNumberEntity` instead of each in a `list`.
    - In the `contains` method Create and Start a Thread for each `CustomNumberEntity`.
    - Give time for all threads to return the `int` after the compare is completed.
    - If any of the Threads return 0, number is in the list.
    - `FastestComparator.compare` takes about 10 seconds using this approach.
    
Conclusion

Although an important factor of how fast possible the compare could be done, I seen the Multi Threading approach as a better option.
In a program a sleep is generally called for a reason and so just calling an interrupt may cause conflict.
