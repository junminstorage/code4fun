EntityTagCache and XEntityTagCache are implemented using Cuckoo hash algorithm. 

This decision is based on the requirement that worse case a getTagsById take a few
100 nano seconds, since hash chaining implemented in JDK's HashMap can only guarantee
amortized constant time for a GET. 

To save memory both EntityTagCache and XEntityTagCache support primitive int data type as hash key.

String interning or pooling is used so all of the tags, maximum 5k tags are pooled, the references to tags
in the cache.

EntityTagCache's Entry class is immutable, key is int, value is the set of tags which are wrapped by Collections.unmodifiableSet.

Space complexity is O(number_id_with_tags) + O(number_of_unique_tags).

Time complexity of GET operation is O(1) in worse case since there is no cache collision, so does update
operation.

To further speed up the GET, an extra id to index mapping table (keys) is maintained, in this 
case space is sacrificed for speed. 

The below table summarizes the measurement of GET speed using 10^6 ids and single word tags:

num_of_gets		java Hashmap	EntityTagCache	java ConcurrentHashMap	XEntityTagCache
1_000_000 		30817000 ns		19848000 ns		127135000 ns			60007000 ns
100_000			9526000	ns		7542000	ns		18568000 ns				10004000 ns
1000 			154000	ns		167000	ns		613000 ns				269000 ns
10  			2000	ns		2000	ns		43000 ns				11000 ns

Note ideally XEntityTagCache should subclassed from EntityTagCache, but based on the requirement that
I am asked to prepare them independently with corresponding test classed. So there are fairly amount
of duplicate code between these two classes. 

Future work is to convert EntityTagCache and XEntityTagCache to generic classes, also run experiments using 
different hash algorithms and load factors too. 

Known bug: getTags(int id) doesn't check input, so arrayOutOfBound exception may be thrown.
 
