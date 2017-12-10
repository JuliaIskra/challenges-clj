(ns project-euler.problem16
  (:require [project-euler.problem5 :as p5]))

(defn problem16
  "https://projecteuler.net/problem=16
  2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
  What is the sum of the digits of the number 2^1000?"
  []
  (->> (p5/exp 2.0 1000)
       BigDecimal.
       .toPlainString
       (map (comp read-string str))
       (reduce +)))
