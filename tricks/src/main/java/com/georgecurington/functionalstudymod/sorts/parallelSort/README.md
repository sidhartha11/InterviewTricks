### Experimental Parallel Sort ###
This is an experimental implementation of sorting a big list in memory.
It is a very simple approach:
1. read in n records and submit them to a merge-sort. The merge-sort will place the sorted list in a queue.
2. A collector will collect items from the queue until it recieves a poison pill.
3. Every pair of items will be merged and re inserted into the queue. The merger process will also be a thread per merge.

So we end up with:
1. a thread per sort
2. a thread per merge
3. 1 collector process. 