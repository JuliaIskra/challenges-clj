(ns advent-of-code-clj.y2024.day2-test
  (:require [clojure.test :refer :all]
            [advent-of-code-clj.y2024.day2 :refer :all]))

(deftest test-part-1
  (testing "part 1"
    (is (= 2 (part-1 "resources/y2024/day2/part-1-example.txt")))))
