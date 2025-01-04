(ns advent-of-code.core
  (:gen-class)
  (:require [advent-of-code.y2024.day1]
            [advent-of-code.y2024.day2]
            [advent-of-code.y2024.day3]))

(defn -main
  [year day & args]
  (let [result (time ((resolve (symbol (str "advent-of-code.y" year ".day" day "/run")))))]
    (println "Result:" result)))