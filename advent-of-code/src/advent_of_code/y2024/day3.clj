(ns advent-of-code.y2024.day3
  (:require [advent-of-code.utils :refer :all]
            [clojure.string :as str]))

(defn part-1
  [filename]
  (->>
    (slurp filename)
    (re-seq #"mul\(\d{1,3},\d{1,3}\)")
    (map #(re-seq #"\d+" %))
    (map to-int-each)
    (map #(reduce * %))
    (reduce +)))


(def do-str "do()")
(def dont-str "don't()")
(def mul-str "mul(")

(defn collect-numbers
  [input]
  (loop [start 0
         end 1
         col []
         save true]
    (if (or (= start (count input)) (= end (count input)))
      col
      (let [s (subs input start end)
            mul (re-find #"mul\(\d{1,3},\d{1,3}\)" (subs input start (min (count input) (+ start 12))))]
        (cond
          (and (= mul-str s) (not (nil? mul)))
          (let [nums (re-seq #"\d+" mul)
                num-len (reduce + (map count nums))
                new-start (+ start 6 num-len)
                new-col (if save (apply conj col (to-int-each nums)) col)]
            (recur new-start (inc new-start) new-col save))
          (= do-str s) (recur (- end start) end col true)
          (= dont-str s) (recur (- end start) end col false)
          (or (str/starts-with? mul-str s)
              (str/starts-with? do-str s)
              (str/starts-with? dont-str s)) (recur start (inc end) col save)
          :else (recur end (inc end) col save))))))

(defn part-2
  [filename]
  (->>
    (slurp filename)
    (collect-numbers)
    (partition 2)
    (map #(reduce * %))
    (reduce +)))


(defn run
  []
  [(part-1 "resources/y2024/day3/input.txt"),
   (part-2 "resources/y2024/day3/input.txt")])