(ns project-euler.problem18)

(defn build-tree
  [tree s]
  (let [numbers (map #(Integer/parseInt %) (re-seq #"\d" s))]
    (if (empty? tree)
      (map (fn [n] [n []]) numbers)
      (loop [counter 0
             result  []]
        (if (== counter (- (count tree) 1))
          result
          (recur
            (inc counter)
            (concat result [[(nth numbers counter)
                             [(nth tree counter)
                              (nth tree (inc counter))]]])))))))

(defn find-all-paths-recur
  [parent children prev-path]
  (prn "==================================")
  (prn "parent" parent)
  (prn "children" children)
  (prn "prev-path" prev-path)
  (let [value (if (empty? children)
                (concat prev-path [parent])
                (let [ch-left  (first (nth children 0))
                      ch-right (first (nth children 1))]
                  [(find-all-paths-recur ch-left (second (nth children 0)) (concat prev-path [parent]))
                   (find-all-paths-recur ch-right (second (nth children 1)) (concat prev-path [parent]))]))]
    (prn "value" value)
    value))

(defn find-all-paths
  [[tree]]
  (find-all-paths-recur (first tree) (second tree) []))

(defn max-children
  [[tree]]
  (loop [p      (first tree)
         ch     (second tree)
         result []]
    (if (empty? ch)
      (concat result [p])
      (let [ch-left  (first (nth ch 0))
            ch-right (first (nth ch 1))]
        (if (> ch-left ch-right)
          (recur ch-left (second (nth ch 0)) (concat result [p]))
          (recur ch-right (second (nth ch 1)) (concat result [p])))))))

(defn problem18
  "https://projecteuler.net/problem=18
  By starting at the top of the triangle below and moving to adjacent numbers
  on the row below, the maximum total from top to bottom is 23.

   3
  7 4
 2 4 6
8 5 9 3

  That is, 3 + 7 + 4 + 9 = 23.

  Find the maximum total from top to bottom of the triangle below:

              75
             95 64
            17 47 82
           18 35 87 10
          20 04 82 47 65
         19 01 23 75 03 34
        88 02 77 73 07 63 67
       99 65 04 28 06 16 70 92
      41 41 26 56 83 40 80 70 33
     41 48 72 33 47 32 37 16 94 29
    53 71 44 65 25 43 91 52 97 51 14
   70 11 33 28 77 73 17 78 39 68 17 57
  91 71 52 38 17 14 91 43 58 50 27 29 48
 63 66 04 68 89 53 67 30 73 16 69 87 40 31
04 62 98 27 23 09 70 98 73 93 38 53 60 04 23"
  []
  (->> (.split "   3\n  7 4\n 2 4 6\n8 5 9 3" "\n")
       ;(->> (.split "              75\n             95 64\n            17 47 82\n           18 35 87 10\n          20 04 82 47 65\n         19 01 23 75 03 34\n        88 02 77 73 07 63 67\n       99 65 04 28 06 16 70 92\n      41 41 26 56 83 40 80 70 33\n     41 48 72 33 47 32 37 16 94 29\n    53 71 44 65 25 43 91 52 97 51 14\n   70 11 33 28 77 73 17 78 39 68 17 57\n  91 71 52 38 17 14 91 43 58 50 27 29 48\n 63 66 04 68 89 53 67 30 73 16 69 87 40 31\n04 62 98 27 23 09 70 98 73 93 38 53 60 04 23" "\n")
       (map str)
       reverse
       (reduce build-tree [])
       ;max-children
       ;(reduce +)))
       find-all-paths))
;prn))