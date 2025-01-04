(ns advent-of-code-clj.core
  (:gen-class)
  (:require [advent-of-code-clj.y2024.day1]
            [advent-of-code-clj.y2024.day2]
            [advent-of-code-clj.y2024.day3]))

(defn -main
  [year day & args]
  (let [result (time ((resolve (symbol (str "advent-of-code-clj.y" year ".day" day "/run")))))]
    (println "Result:" result)))