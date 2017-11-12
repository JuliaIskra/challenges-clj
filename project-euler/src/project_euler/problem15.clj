(ns project-euler.problem15)

(def grid 20)

(defn can-go-right?
  [[x y]]
  (<= (inc x) grid))

(defn can-go-down?
  [[x y]]
  (<= (inc y) grid))

(defn go-right
  [[x y]]
  [(inc x) y])

(defn go-down
  [[x y]]
  [x (inc y)])

(defn next-steps
  [coord]
  (if-not (or (can-go-right? coord) (can-go-down? coord))
    [coord]
    [(if (can-go-right? coord) (go-right coord))
     (if (can-go-down? coord) (go-down coord))]))

(defn problem15
  "https://projecteuler.net/problem=15
  Starting in the top left corner of a 2×2 grid, and only being able to move
  to the right and down, there are exactly 6 routes to the bottom right corner.
  How many such routes are there through a 20×20 grid?"
  []
  (loop [routes    [[0 0]]
         prev-size 0]
    (if (= prev-size (count routes))
      prev-size
      (recur (->> routes
                  (mapcat next-steps)
                  (filter (comp not nil?)))
             (count routes)))))
