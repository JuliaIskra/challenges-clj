(ns advent-of-code.utils)

(defn to-int-each
  [coll]
  (map #(Integer/parseInt %) coll))
