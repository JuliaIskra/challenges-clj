(ns project-euler.problem7)

(defn problem7
  "https://projecteuler.net/problem=7
  By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
  What is the 10001st prime number?"
  []
  (loop [prime-numbers []
         count         0
         next          2]
    (if (= 10001 count)
      (last prime-numbers)
      (if (->> prime-numbers
               (map #(mod next %1))
               (filter zero?)
               empty?)
        (recur (concat prime-numbers [next])
               (inc count)
               (inc next))
        (recur prime-numbers
               count
               (inc next))))))

