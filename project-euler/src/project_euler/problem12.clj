(ns project-euler.problem12)

(defn count-divisors
  [n]
  (count
    (remove
      nil?
      (for [d (range 1 (inc n))]
        (if (= (mod n d) 0) d nil)))))

(defn calc-triangle-number
  [n]
  (reduce + (range 1 (inc n))))

(defn problem12
  "https://projecteuler.net/problem=12
  The sequence of triangle numbers is generated by adding the natural numbers.
  So the 7th triangle number would be
    1 + 2 + 3 + 4 + 5 + 6 + 7 = 28.
  The first ten terms would be:
    1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
  Let us list the factors of the first seven triangle numbers:
     1: 1
     3: 1,3
     6: 1,2,3,6
    10: 1,2,5,10
    15: 1,3,5,15
    21: 1,3,7,21
    28: 1,2,4,7,14,28
  We can see that 28 is the first triangle number to have over five divisors.
  What is the value of the first triangle number to have over five hundred divisors?"
  []
  (->> (range)
       (map calc-triangle-number)
       (map count-divisors)
       (filter #(> %1 500))
       (first)))