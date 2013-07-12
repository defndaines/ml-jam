# ml-jam

Lambda Jam - Day 2 Jam
Machine Learning exercise attempting to identify numbers from a black-and-white image.

Project includes a few implementation to test performance.
In particular, beginning to experiment with hiphip (array)!
  https://github.com/prismatic/hiphip

Initial (by-hand) performance comparison:
  Average time to validate a single digit against 5000 training set values.
    2300 msecs
  Average time to validate a single digit using hip hip:
    181 msecs

## Usage

To test a single digit:
 (nearest-neighbor-map (first validation-set) training-set)

Using hiphip:
 (hippest-neighbor-map (first hip-validation) hip-train)

## License

Copyright © 2013 Michael S. Daines

Distributed under the Eclipse Public License, the same as Clojure.
