(ns project-euler.problem6
  (:require [project-euler.problem5 :as p5]))

(defn problem6
  "https://projecteuler.net/problem=6
  The sum of the squares of the first ten natural numbers is,
      1^2 + 2^2 + ... + 10^2 = 385
  The square of the sum of the first ten natural numbers is,
      (1 + 2 + ... + 10)^2 = 552 = 3025
  Hence the difference between the sum of the squares of the first ten natural numbers
  and the square of the sum is 3025 − 385 = 2640.
  Find the difference between the sum of the squares of the first one hundred natural numbers
  and the square of the sum."
  []
  (- (p5/exp (reduce + (range 1 101)) 2)
     (reduce + (map #(p5/exp %1 2) (range 1 101)))))

