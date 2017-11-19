(ns project-euler.problem15)

(def grid 20)

(defn sum-pairs
  [coll]
  (loop [next-i 2
         prev   (nth coll 0)
         curr   (nth coll 1)
         result []]
    (if (= next-i (count coll))
      (conj result (+ prev curr))
      (recur (inc next-i)
             curr
             (nth coll next-i)
             (conj result (+ prev curr))))))

(defn problem15
  "https://projecteuler.net/problem=15
  Starting in the top left corner of a 2×2 grid, and only being able to move
  to the right and down, there are exactly 6 routes to the bottom right corner.
  How many such routes are there through a 20×20 grid?"
  []
  (loop [counter 1
         result  [1 1]]
    (if (= 1 (count result))
      (first result)
      (recur (inc counter)
             (if (< counter grid)
               (flatten [1 (sum-pairs result) 1])
               (sum-pairs result))))))
