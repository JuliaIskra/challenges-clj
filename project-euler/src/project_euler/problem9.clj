(ns project-euler.problem9
  (:require [clojure.math.numeric-tower :as math]))

(defn problem9
  "https://projecteuler.net/problem=9
  A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
      a^2 + b^2 = c^2
  For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
  There exists exactly one Pythagorean triplet for which a + b + c = 1000.
  Find the product abc."
  []
  (first (for [a (range 1 998)
               b (range (inc a) 999)
               :let [c-squared (+ (* a a) (* b b))
                     [c rem] (math/exact-integer-sqrt c-squared)]
               :when (and (= rem 0)
                          (= (+ a b c) 1000))]
           (* a b c))))

