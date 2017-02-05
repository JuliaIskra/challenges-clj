(ns codingame.descent
  (:gen-class))

; The while loop represents the game.
; Each iteration represents a turn of the game
; where you are given inputs (the heights of the mountains)
; and where you have to print an output (the index of the mountain to fire on)
; The inputs you are given are automatically updated according to your last actions.

; (binding [*out* *err*]
;   (println "Debug messages..."))

(defn -main [& args]
  (while true
    (->> (range 8)
         (map (fn [i] [i (read)]))
         (reduce (fn [[i-max h-max] [i-next h-next]]
                   (if (< h-max h-next) [i-next h-next] [i-max h-max]))
                 [0 0])
         first
         println)))
