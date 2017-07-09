(ns project-euler.problem10
  (:require [clojure.math.numeric-tower :as math]))

(defn prime?
  [n]
  (cond
    (= n 1) false
    (< n 4) true
    (= 0 (mod n 2)) false
    (= 0 (mod n 3)) false
    :else (loop [r (math/floor (math/sqrt n))
                 f 5]
            (if (> f r)
              true
              (cond
                (= 0 (mod n f)) false
                (= 0 (mod n (+ f 2))) false
                :else (recur r (+ f 6)))))))

(defn problem10
  "https://projecteuler.net/problem=10
  The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
  Find the sum of all the primes below two million."
  []
  (->> (range 1 2000000)
       (filter prime?)
       (reduce +)))

