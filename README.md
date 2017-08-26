# Circular auto scrolling RecyclerView

This is the implemention of a circular auto scrolling RecyclerView using Kotlin and RxJava 

![gif](https://github.com/tomoima525/InfiniteRotation/blob/master/art/result.gif)

# Circular RecyclerView 
A basic idea behind a circular view is to looping back to the same item in a different element of an array. The diagram below explains how.
Let's say there are 3 items(item1, item2, item3) in our array. Since the array only need the first and last elements of its content, the logic can be more efficient by appending the first element to the end of the array and adding the last element to the front of the array. 

![img2](https://github.com/tomoima525/InfiniteRotation/blob/master/art/img2.png)  

# Auto scrolling
To make RecyclerView auto scroll, we have to call smoothScrollToPosition every specified interval of time. We can use Flowable.interval to emit sequential Long value.
To stop auto scroll if a user swipes RecyclerView, we can listen to the scroll state change.
