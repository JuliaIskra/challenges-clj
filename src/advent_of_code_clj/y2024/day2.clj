(ns advent-of-code-clj.y2024.day2
  (:require [clojure.string :as str]))

(defn to-int-each
  [coll]
  (map #(Integer/parseInt %) coll))

(defn in-range?
  [diffs]
  (let [all-neg (every? neg? diffs)
        all-pos (every? pos? diffs)
        in-range (every? (fn [x] (and (>= (abs x) 1) (<= (abs x) 3))) diffs)]
    (and (or all-neg all-pos) in-range)))

(defn is-safe?
  [levels]
  (->> levels
       (partition 2 1)
       (map (fn [[a b]] (- a b)))
       in-range?))

(defn part-1
  [filename]
  (->>
    (-> (slurp filename)
        (str/split #"\n"))
    (map #(str/split % #" "))
    (map to-int-each)
    (map is-safe?)
    (filter true?)
    count))


(defn run
  []
  [(part-1 "resources/y2024/day2/part-1.txt")])