## HEAP ##
Heap is a datastructure that performs optimally in 3 ways:
1. insertion
2. finding min/max
3. deleting min/max
Time Complexity:

for insert: O(log n)
for find min/max O(1)
for delete: O(log n)

Heap:
Is an almost complete binary tree:
 you must fill out all levels before going to the next level.
 filled in from left to right 
 max heap:
 Is a complete or almost complete binary tree in which the root is 
 always the maximum. ( recursively ) 
 mon heap:
 Is a complete or almost complete binary tree in which the root is 
 always the minumum of the tree. ( recursively )
 
 Easiest Way To Construct A Heap:
 Use an array to store the heap ( in java we can use an ArrayList ) 
 
 Formula for getting the Index Of The left and right child of a node using the Array approach:

left index:
index of parent-index * 2 + 1
right index
index of parent-index * 2 + 2




 
  