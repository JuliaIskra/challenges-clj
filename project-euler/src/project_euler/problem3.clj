(ns project-euler.problem3)

(defn calc-min-prime-factor
  [n]
  (->> (range 2 n)
       (map (fn [div] [div (mod n div)]))
       (filter (fn [[div rem]] (zero? rem)))
       first
       first))

(defn problem3
  "https://projecteuler.net/problem=3
  The prime factors of 13195 are 5, 7, 13 and 29.
  What is the largest prime factor of the number 600851475143 ?"
  []
  (loop [n 600851475143]
    (let [min-factor (calc-min-prime-factor n)]
      (if (nil? min-factor)
        n
        (recur (/ n min-factor))))))

