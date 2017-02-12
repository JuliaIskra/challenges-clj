(ns project-euler.core
  (:gen-class))

(defn problem1
  []
  "hey!")

(defn -main
  [number & args]
  (println (str "Running solution for problem #" number))
  (println ((resolve (symbol (str "project-euler.core/problem" number))))))

