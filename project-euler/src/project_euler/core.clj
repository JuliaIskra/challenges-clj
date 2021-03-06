(ns project-euler.core
  (:gen-class)
  (:require [project-euler.problem1]
            [project-euler.problem2]
            [project-euler.problem3]
            [project-euler.problem4]
            [project-euler.problem5]
            [project-euler.problem6]
            [project-euler.problem7]
            [project-euler.problem8]
            [project-euler.problem9]
            [project-euler.problem10]
            [project-euler.problem11]
            [project-euler.problem12]
            [project-euler.problem13]
            [project-euler.problem14]
            [project-euler.problem15]
            [project-euler.problem16]
            [project-euler.problem17]
            [project-euler.problem18]
            [project-euler.problem19]))

(defn -main
  [number & args]
  (println (str "Running solution for problem #" number))
  (let [result (time ((resolve (symbol (str "project-euler.problem" number "/problem" number)))))]
    (println "Result:" result)))

