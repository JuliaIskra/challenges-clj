(ns project-euler.problem15)

; 20x20 grid coordinates: 0x0..19x19

(defn can-go-right?
  [[x y]]
  (< (inc x) 20))

(defn can-go-down?
  [[x y]]
  (< (inc y) 20))

(defn problem15
  "https://projecteuler.net/problem=15
  Starting in the top left corner of a 2×2 grid, and only being able to move
  to the right and down, there are exactly 6 routes to the bottom right corner.
  How many such routes are there through a 20×20 grid?"
  []
  )
