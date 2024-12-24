(ns advent-of-code-clj.y2024.day1-test
  (:require [clojure.test :refer :all]
            [advent-of-code-clj.y2024.day1 :refer :all]))

(deftest test-part-1
  (testing "part 1"
    (is (= 11 (part1 "resources/y2024/day1/part-1-example.txt")))))
