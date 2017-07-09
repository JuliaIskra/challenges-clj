(ns project-euler.problem5
  (:require [project-euler.problem3 :as p3]))

(defn calc-prime-factors
  [num]
  (loop [n             num
         prime-factors []]
    (let [min-factor (p3/calc-min-prime-factor n)]
      (if (nil? min-factor)
        (concat prime-factors [n])
        (recur (/ n min-factor) (concat prime-factors [min-factor]))))))

(defn exp
  [n exp]
  (reduce * (repeat exp n)))

(defn problem5
  "https://projecteuler.net/problem=5
  2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
  What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?"
  []
  (->> (range 1 21)
       (map calc-prime-factors)
       (map frequencies)
       (reduce #(merge-with max %1 %2) {})
       seq
       (reduce (fn [result [n mult]] (* result (exp n mult))) 1)))

