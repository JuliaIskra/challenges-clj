(ns project-euler.core
  (:gen-class))

(defn problem1
  "If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
  The sum of these multiples is 23.
  Find the sum of all the multiples of 3 or 5 below 1000."
  []
  (->> (range 1000)
       (filter (fn [n] (or (zero? (mod n 3)) (zero? (mod n 5)))))
       (reduce +)))

(defn -main
  [number & args]
  (println (str "Running solution for problem #" number))
  (println ((resolve (symbol (str "project-euler.core/problem" number))))))

