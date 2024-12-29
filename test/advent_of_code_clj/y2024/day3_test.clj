(ns advent-of-code-clj.y2024.day3-test
  (:require [advent-of-code-clj.y2024.day3 :refer :all]
            [clojure.test :refer :all]))

(deftest test-part-1
  (testing "part 1"
    (is (= 161 (part-1 "resources/y2024/day3/input-example.txt")))))

(deftest test-part-2
  (testing "part 2"
    (is (= 4 (part-2 "resources/y2024/day3/input-example.txt")))))
