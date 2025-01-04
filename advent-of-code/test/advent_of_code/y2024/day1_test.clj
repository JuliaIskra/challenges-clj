(ns advent-of-code.y2024.day1-test
  (:require [advent-of-code.y2024.day1 :refer :all]
            [clojure.test :refer :all]))

(deftest test-part-1-ex
  (testing "part 1 example"
    (is (= 11 (part-1 "resources/y2024/day1/input-example.txt")))))

(deftest test-part-1
  (testing "part 1"
    (is (= 1388114 (part-1 "resources/y2024/day1/input.txt")))))

(deftest test-part-2-ex
  (testing "part 2 example"
    (is (= 31 (part-2 "resources/y2024/day1/input-example.txt")))))

(deftest test-part-2
  (testing "part 2"
    (is (= 23529853 (part-2 "resources/y2024/day1/input.txt")))))
