(ns advent-of-code.y2024.day3
  (:require [advent-of-code.utils :refer :all]))

(defn part-1
  [filename]
  (->>
    (slurp filename)
    (re-seq #"mul\(\d{1,3},\d{1,3}\)")
    (map #(re-seq #"\d+" %))
    (map to-int-each)
    (map #(reduce * %))
    (reduce +)))


(defn collect-numbers
  [input]
  (loop [i 0
         col []
         save true]
    (if (= i (count input))
      col
      (cond
        (= (nth input i) "do()") (recur (inc i) col true)
        (= (nth input i) "don't()") (recur (inc i) col false)
        (true? save) (recur (inc i) (conj col (nth input i)) save)
        :else (recur (inc i) col save)))))

(defn part-2
  [filename]
  (->>
    (slurp filename)
    (re-seq #"(mul\(\d{1,3},\d{1,3}\))|(do\(\))|(don't\(\))")
    (map #(% 0))
    (collect-numbers)
    (map #(re-seq #"\d+" %))
    (map to-int-each)
    (map #(reduce * %))
    (reduce +)))


(defn run
  []
  [(part-1 "resources/y2024/day3/input.txt"),
   (part-2 "resources/y2024/day3/input.txt")])