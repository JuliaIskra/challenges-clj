(ns advent-of-code-clj.y2024.day3
  (:require [advent-of-code-clj.utils :refer :all]))


(defn part-1
  [filename]
  (->>
    (slurp filename)
    (re-seq #"mul\(\d{1,3},\d{1,3}\)")
    (map #(re-seq #"\d+" %))
    (map to-int-each)
    (map #(reduce * %))
    (reduce +)))


(defn part-2
  [filename]
  )


(defn run
  []
  [(part-1 "resources/y2024/day3/input.txt"),
   (part-2 "resources/y2024/day3/input.txt")])