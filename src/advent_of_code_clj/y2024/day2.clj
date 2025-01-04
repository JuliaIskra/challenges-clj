(ns advent-of-code-clj.y2024.day2
  (:require [advent-of-code-clj.utils :refer :all]
            [clojure.string :as str]))

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


(defn drop-nth [n coll]
  (keep-indexed #(if (not= %1 n) %2) coll))

(defn is-safe-dampener?
  [levels]
  (loop [i -1]
    (let [dampened-levels (drop-nth i levels)
          is-safe (->> dampened-levels
                       (partition 2 1)
                       (map (fn [[a b]] (- a b)))
                       in-range?)]
      (if (or is-safe (= i (count levels)))
        is-safe
        (recur (inc i))))))

(defn part-2
  [filename]
  (->>
    (-> (slurp filename)
        (str/split #"\n"))
    (map #(str/split % #" "))
    (map to-int-each)
    (map #(is-safe-dampener? %))
    (filter true?)
    count))


(defn run
  []
  [(part-1 "resources/y2024/day2/input.txt"),
   (part-2 "resources/y2024/day2/input.txt")])