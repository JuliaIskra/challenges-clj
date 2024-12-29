(ns advent-of-code-clj.y2024.day1
  (:require [clojure.string :as str]))

(defn separate-into-cols
  [coll]
  (let [f (map first coll) s (map second coll)] [f s]))

(defn to-int-each
  [coll]
  (map #(Integer/parseInt %) coll))

(defn zip
  [[a b]]
  (map vector a b))

(defn diff
  [[a b]]
  (abs (- a b)))

(defn part-1
  [filename]
  (->>
    (-> (slurp filename)
        (str/split #"\n"))
    (map #(str/split % #"\s+"))
    separate-into-cols
    (map to-int-each)
    (map sort)
    zip
    (map diff)
    (reduce +)))


(defn calc-similarity-score
  [[a b]]
  (let [fr (frequencies b)]
    (map (fn [x] (* x (get fr x 0))) a)))

(defn part-2
  [filename]
  (->>
    (-> (slurp filename)
        (str/split #"\n"))
    (map #(str/split % #"\s+"))
    separate-into-cols
    (map to-int-each)
    calc-similarity-score
    (reduce +)))


(defn run
  []
  [(part-1 "resources/y2024/day1/input.txt"),
   (part-2 "resources/y2024/day1/input.txt")])