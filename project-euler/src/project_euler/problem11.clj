(ns project-euler.problem11
  (:require [project-euler.problem8 :as p8]))

(defn parse-numbers-into-coords
  [row-number string]
  (loop [numbers (->> (map str string)
                      (partition-by #(= " " %1))
                      (filter #(= 2 (count %1)))
                      (map #(reduce str %1))
                      (map #(Integer/parseInt %1))
                      (map-indexed (fn [index number] {:x index :y row-number :n number})))]
    numbers))

(defn diag-lurd-coord
  "calculates coordinates of all diagonals that go from left up to right down"
  [size]
  (let [initial (loop [x 0 y 0 result []]
                  (if (= x size)
                    result
                    (recur (inc x) (inc y) (conj result [x y]))))]
    (loop [count  1
           result [initial]]
      (if (= count size)
        result
        (recur (inc count)
               (conj result
                     (filter (fn [[x y]] (> y -1))
                             (for [[x y] initial]
                               [x (- y count)]))
                     (filter (fn [[x y]] (< y size))
                             (for [[x y] initial]
                               [x (+ y count)]))))))))

(defn diag-ruld-coord
  "calculates coordinates of all diagonals that go from right up to left down"
  [size]
  (let [initial (loop [x (- size 1) y 0 result []]
                  (if (= x -1)
                    result
                    (recur (dec x) (inc y) (conj result [x y]))))]
    (loop [count  1
           result [initial]]
      (if (= count size)
        result
        (recur (inc count)
               (conj result
                     (filter (fn [[x y]] (> x -1))
                             (for [[x y] initial]
                               [(- x count) y]))
                     (filter (fn [[x y]] (< x size))
                             (for [[x y] initial]
                               [(+ x count) y]))))))))

(defn get-number-by-coord
  [x y numbers]
  (->> numbers
       (filter #(and (= x (%1 :x)) (= y (%1 :y))))
       first
       :n))

(defn get-numbers-in-coords
  [numbers coords]
  (map (fn [coords-coll]
         (map (fn [[x y]] (get-number-by-coord x y numbers))
              coords-coll))
       coords))

(defn problem11
  "https://projecteuler.net/problem=11
  In the 20×20 grid below, four numbers along a diagonal line have been marked in red.
    08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08
    49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00
    81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65
    52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91
    22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80
    24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50
    32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70
    67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21
    24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72
    21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95
    78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92
    16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57
    86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58
    19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40
    04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66
    88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69
    04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36
    20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16
    20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54
    01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48

  The product of these numbers is 26 × 63 × 78 × 14 = 1788696.
  What is the greatest product of four adjacent numbers in the same direction (up, down, left, right, or diagonally) in the 20×20 grid?"
  []
  (let [numbers           (->> ["08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08"
                                "49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00"
                                "81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65"
                                "52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91"
                                "22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80"
                                "24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50"
                                "32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70"
                                "67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21"
                                "24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72"
                                "21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95"
                                "78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92"
                                "16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57"
                                "86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58"
                                "19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40"
                                "04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66"
                                "88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69"
                                "04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36"
                                "20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16"
                                "20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54"
                                "01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48"]
                               (map-indexed parse-numbers-into-coords)
                               flatten)

        cols-coord        (partition-by (fn [[x y]] x)
                                        (for [x (range 20)
                                              y (range 20)]
                                          [x y]))
        rows-coord        (partition-by (fn [[x y]] y)
                                        (for [y (range 20)
                                              x (range 20)]
                                          [x y]))
        diag-lurd-coord   (diag-lurd-coord 20)
        diag-ruld-coord   (diag-ruld-coord 20)

        cols              (get-numbers-in-coords numbers cols-coord)
        rows              (get-numbers-in-coords numbers rows-coord)
        diag-lurd         (get-numbers-in-coords numbers diag-lurd-coord)
        diag-ruld         (get-numbers-in-coords numbers diag-ruld-coord)

        cols-product      (map #(p8/greatest-product-of-n-adjacent-numbers 4 (vec %1)) cols)
        rows-product      (map #(p8/greatest-product-of-n-adjacent-numbers 4 (vec %1)) rows)
        diag-lurd-product (map #(p8/greatest-product-of-n-adjacent-numbers 4 (vec %1)) diag-lurd)
        diag-ruld-product (map #(p8/greatest-product-of-n-adjacent-numbers 4 (vec %1)) diag-ruld)]
    (max
      (apply max cols-product)
      (apply max rows-product)
      (apply max diag-lurd-product)
      (apply max diag-ruld-product))))

