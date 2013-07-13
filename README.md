# ml-jam

Lambda Jam - Day 2 Jam
Machine Learning exercise attempting to identify numbers from a black-and-white image.

Project includes a few implementation to test performance. In particular, beginning to experiment with hiphip (array)! https://github.com/prismatic/hiphip

Criterium performance comparison (nearly 15x speed-up on my machine):

  Average time to validate a single digit against 5000 training set values: 2781 ms.

  Average time to validate a single digit using hiphip: 185 ms.

I've also observed that map is slightly more performant than a for comprehension (185 vs. 196 ms).

Run serially (without pmap), it took about 1.5 minutes to run the entire validation set. With pmap, about 50 seconds (my laptop has only two cores).

## Usage

To test a single digit:

  (nearest-neighbor-map (first validation-set) training-set)

  or

  (nearest-neighbor (first validation-set) training-set)

Using hiphip:

  (hippest-neighbor-map (first hip-validation) hip-train)
 
  or
 
  (hippest-neighbor (first hip-validation) hip-train)

## License

Copyright © 2013 Michael S. Daines

Distributed under the Eclipse Public License, the same as Clojure.
