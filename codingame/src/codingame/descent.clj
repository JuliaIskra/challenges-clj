(ns codingame.descent
  (:gen-class))

; Task:
; At the start of each game turn, you are given the height of the 8 mountains from left to right.
; By the end of the game turn, you must fire on the highest mountain by outputting its index (from 0 to 7).

; Game Input:
; Within an infinite loop, read the heights of the mountains from the standard input and print to the standard output
; the index of the mountain to shoot.

; Input for one game turn:
; 8 lines: one integer mountainH per line. Each represents the height of one mountain given in the order of their index
; (from 0 to 7).

; Output for one game turn:
; A single line with one integer for the index of which mountain to shoot.

; Constraints:
; 0 ≤ mountainH ≤ 9
; Response time per turn ≤ 100ms

; The while loop represents the game.
; Each iteration represents a turn of the game
; where you are given inputs (the heights of the mountains)
; and where you have to print an output (the index of the mountain to fire on)
; The inputs you are given are automatically updated according to your last actions.

(defn -main [& args]
  (while true
    (->> (range 8)
         (map (fn [i] [i (read)]))
         (reduce (fn [[i-max h-max] [i-next h-next]]
                   (if (< h-max h-next) [i-next h-next] [i-max h-max]))
                 [0 0])
         first
         println)))
