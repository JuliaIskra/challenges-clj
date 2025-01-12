(ns advent-of-code.y2024.day3
  (:require [advent-of-code.utils :refer :all]))

(defn part-1
  [filename]
  (->>
    (slurp filename)
    (re-seq #"mul\((\d{1,3}),(\d{1,3})\)")
    (map (fn [[_ a b]] (* (str->int a) (str->int b))))
    (reduce +)))


(defn save-muls
  [[save muls] current]
  (cond
    (= current "do()") [true muls]
    (= current "don't()") [false muls]
    (true? save) [save (conj muls current)]
    :else [save muls]))

(defn part-2
  [filename]
  (->>
    (slurp filename)
    (re-seq #"(mul\(\d{1,3},\d{1,3}\))|(do\(\))|(don't\(\))")
    (map #(% 0))
    (reduce save-muls [true []])
    (->>nth 1)
    (map #(re-seq #"\d+" %))
    (map (fn [[a b]] (* (str->int a) (str->int b))))
    (reduce +)))


(defn run
  []
  [(part-1 "resources/y2024/day3/input.txt"),
   (part-2 "resources/y2024/day3/input.txt")])