(ns project-euler.core
  (:gen-class)
  (:require [clojure.math.numeric-tower :as math]))

(defn problem1
  "https://projecteuler.net/problem=1
  If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
  The sum of these multiples is 23.
  Find the sum of all the multiples of 3 or 5 below 1000."
  []
  (->> (range 1000)
       (filter (fn [n] (or (zero? (mod n 3)) (zero? (mod n 5)))))
       (reduce +)))


(defn calc-fib
  [limit]
  (loop [fib-seq [1 2]]
    (let [next (reduce + (take-last 2 fib-seq))]
      (if (> next limit)
        fib-seq
        (recur (concat fib-seq [next]))))))

(defn problem2
  "https://projecteuler.net/problem=2
  Each new term in the Fibonacci sequence is generated by adding the previous two terms.
  By starting with 1 and 2, the first 10 terms will be:
  1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
  By considering the terms in the Fibonacci sequence whose values do not exceed four million,
  find the sum of the even-valued terms."
  []
  (->> (calc-fib 4000000)
       (filter even?)
       (reduce +)))


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


(defn calc-prime-factors
  [num]
  (loop [n             num
         prime-factors []]
    (let [min-factor (calc-min-prime-factor n)]
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
  (- (exp (reduce + (range 1 101)) 2)
     (reduce + (map #(exp %1 2) (range 1 101)))))


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
        (do (recur (concat prime-numbers [next])
                   (inc count)
                   (inc next)))
        (do (recur prime-numbers
                   count
                   (inc next)))))))


(defn problem8
  "https://projecteuler.net/problem=8
  The four adjacent digits in the 1000-digit number that have the greatest product are 9 × 9 × 8 × 9 = 5832.
      73167176531330624919225119674426574742355349194934
      96983520312774506326239578318016984801869478851843
      85861560789112949495459501737958331952853208805511
      12540698747158523863050715693290963295227443043557
      66896648950445244523161731856403098711121722383113
      62229893423380308135336276614282806444486645238749
      30358907296290491560440772390713810515859307960866
      70172427121883998797908792274921901699720888093776
      65727333001053367881220235421809751254540594752243
      52584907711670556013604839586446706324415722155397
      53697817977846174064955149290862569321978468622482
      83972241375657056057490261407972968652414535100474
      82166370484403199890008895243450658541227588666881
      16427171479924442928230863465674813919123162824586
      17866458359124566529476545682848912883142607690042
      24219022671055626321111109370544217506941658960408
      07198403850962455444362981230987879927244284909188
      84580156166097919133875499200524063689912560717606
      05886116467109405077541002256983155200055935729725
      71636269561882670428252483600823257530420752963450
  Find the thirteen adjacent digits in the 1000-digit number that have the greatest product.
  What is the value of this product?"
  []
  (let [numbers (vec (map #(Integer/parseInt %)
                          (map str (str "73167176531330624919225119674426574742355349194934"
                                        "96983520312774506326239578318016984801869478851843"
                                        "85861560789112949495459501737958331952853208805511"
                                        "12540698747158523863050715693290963295227443043557"
                                        "66896648950445244523161731856403098711121722383113"
                                        "62229893423380308135336276614282806444486645238749"
                                        "30358907296290491560440772390713810515859307960866"
                                        "70172427121883998797908792274921901699720888093776"
                                        "65727333001053367881220235421809751254540594752243"
                                        "52584907711670556013604839586446706324415722155397"
                                        "53697817977846174064955149290862569321978468622482"
                                        "83972241375657056057490261407972968652414535100474"
                                        "82166370484403199890008895243450658541227588666881"
                                        "16427171479924442928230863465674813919123162824586"
                                        "17866458359124566529476545682848912883142607690042"
                                        "24219022671055626321111109370544217506941658960408"
                                        "07198403850962455444362981230987879927244284909188"
                                        "84580156166097919133875499200524063689912560717606"
                                        "05886116467109405077541002256983155200055935729725"
                                        "71636269561882670428252483600823257530420752963450"))))]
    (loop [start       0
           max-product 0]
      (if (>= start (- 1000 (- 13 1)))
        max-product
        (do (let [digits  (subvec numbers start (+ start 13))
                  product (reduce * digits)]
              (recur (inc start) (if (< max-product product) product max-product))))))))


(defn problem9
  "https://projecteuler.net/problem=9
  A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
      a^2 + b^2 = c^2
  For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
  There exists exactly one Pythagorean triplet for which a + b + c = 1000.
  Find the product abc."
  []
  (for [a (range 1 998)
        b (range (inc a) 999)
        :let [c-squared (+ (* a a) (* b b))
              [c rem] (math/exact-integer-sqrt c-squared)]
        :when (and (= rem 0)
                   (= (+ a b c) 1000))]
    (* a b c)))


(defn prime?
  [n]
  (cond
    (= n 1) false
    (< n 4) true
    (= 0 (mod n 2)) false
    (= 0 (mod n 3)) false
    :else (loop [r (math/floor (math/sqrt n))
                 f 5]
            (if (> f r)
              true
              (cond
                (= 0 (mod n f)) false
                (= 0 (mod n (+ f 2))) false
                :else (recur r (+ f 6)))))))

(defn problem10
  "https://projecteuler.net/problem=10
  The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
  Find the sum of all the primes below two million."
  []
  (->> (range 1 2000000)
       (filter prime?)
       (reduce +)))


(defn -main
  [number & args]
  (println (str "Running solution for problem #" number))
  (println (time ((resolve (symbol (str "project-euler.core/problem" number)))))))

