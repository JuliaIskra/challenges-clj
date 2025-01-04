(ns advent-of-code.y2024.day2-test
  (:require [advent-of-code.y2024.day2 :refer :all]
            [clojure.test :refer :all]))

(deftest test-part-1-ex
  (testing "part 1 example"
    (is (= 2 (part-1 "resources/y2024/day2/input-example.txt")))))

(deftest test-part-1
  (testing "part 1"
    (is (= 334 (part-1 "resources/y2024/day2/input.txt")))))

(deftest test-part-2-ex
  (testing "part 2 example"
    (is (= 4 (part-2 "resources/y2024/day2/input-example.txt")))))

(deftest test-part-2
  (testing "part 2"
    (is (= 400 (part-2 "resources/y2024/day2/input.txt")))))
