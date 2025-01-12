(ns advent-of-code.utils)

(defn str->int
  [n]
  (Integer/parseInt n))

(defn to-int-each
  [coll]
  (map #(str->int %) coll))

(defn ->>nth
  [index col]
  (nth col index))
