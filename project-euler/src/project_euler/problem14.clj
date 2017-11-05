(ns project-euler.problem14)

(defn calc-seq-length
  [n]
  (loop [x      n
         length 1]
    (if (= x 1)
      [n length]
      (recur (if (even? x) (/ x 2) (+ (* 3 x) 1)) (inc length)))))

(defn problem14
  "https://projecteuler.net/problem=14
  The following iterative sequence is defined for the set of positive integers:
    n → n/2 (n is even)
    n → 3n + 1 (n is odd)
  Using the rule above and starting with 13, we generate the following sequence:
    13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
  It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms.
  Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
  Which starting number, under one million, produces the longest chain?
  NOTE: Once the chain starts the terms are allowed to go above one million."
  []
  (->> (range 1 1000000)
       (map calc-seq-length)
       (reduce (fn [[n-max l-max] [n l]] (if (> l l-max) [n l] [n-max l-max])))
       first))
