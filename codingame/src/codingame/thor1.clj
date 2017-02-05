(ns codingame.thor1
  (:gen-class))

; Game Input:
; The program must first read the initialization data from the standard input, then, in an infinite loop, provides on
; the standard output the instructions to move Thor.

; Initialization input:
; Line 1: 4 integers lightX lightY initialTX initialTY.
; (lightX, lightY) indicates the position of the light.
; (initialTX, initialTY) indicates the initial position of Thor.

; Input for a game round:
; Line 1: the number of remaining moves for Thor to reach the light of power: remainingTurns. You can ignore this data
; but you must read it.

; Output for a game round:
; A single line providing the move to be made: N NE E SE S SW W ou NW

; Constraints:
; 0 ≤ lightX < 40
; 0 ≤ lightY < 18
; 0 ≤ initialTX < 40
; 0 ≤ initialTY < 18
; Response time for a game round ≤ 100ms

(defn calc-y-direction [ly ty]
  (cond
    (> ly ty) ["S" (inc ty)]
    (< ly ty) ["N" (dec ty)]
    (= ly ty) ["" ty]))

(defn calc-x-direction [lx tx]
  (cond
    (> lx tx) ["E" (inc tx)]
    (< lx tx) ["W" (dec tx)]
    (= lx tx) ["" tx]))

(defn -main [& args]
  (let [lightX (read) lightY (read) initialTX (read) initialTY (read)]
    (while true
      (read)
      (loop [yd (calc-y-direction lightY initialTY)
             xd (calc-x-direction lightX initialTX)]
        (println (str (first yd) (first xd)))
        (recur (calc-y-direction lightY (second yd))
               (calc-x-direction lightX (second xd)))))))

