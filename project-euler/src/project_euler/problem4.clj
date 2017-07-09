(ns project-euler.problem4)

(defn palindrome?
  [n]
  (= (str n) (reduce str (reverse (str n)))))

(defn calc-palindrome-made-with-factor
  [n]
  (loop [f1 n
         f2 n]
    (let [product (* f1 f2)]
      (cond
        (= f2 99) nil
        (palindrome? product) product
        :else (recur f1 (dec f2))))))

(defn problem4
  "https://projecteuler.net/problem=4
  A palindromic number reads the same both ways.
  The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
  Find the largest palindrome made from the product of two 3-digit numbers."
  []
  (->> (range 999 99 -1)
       (map calc-palindrome-made-with-factor)
       (filter #(not (nil? %1)))
       (sort #(compare %2 %1))
       first))

