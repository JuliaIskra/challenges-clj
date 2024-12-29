(ns advent-of-code-clj.utils)

(defn to-int-each
  [coll]
  (map #(Integer/parseInt %) coll))
