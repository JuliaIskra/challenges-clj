(ns advent-of-code.y2024.day3-test
  (:require [advent-of-code.y2024.day3 :refer :all]
            [clojure.test :refer :all]))

(deftest test-part-1-ex
  (testing "part 1 example"
    (is (= 161 (part-1 "resources/y2024/day3/input-example-1.txt")))))

(deftest test-part-1
  (testing "part 1"
    (is (= 188741603 (part-1 "resources/y2024/day3/input.txt")))))

(deftest test-part-2-ex
  (testing "part 2 example"
    (is (= 48 (part-2 "resources/y2024/day3/input-example-2.txt")))))

(deftest test-collect-numbers
  (testing "collect numbers when mul starts correctly but has incorrect format"
    (is (= [504 381 837 216] (collect-numbers "mul(504,381)>what()why()$%from(53,115)%mul(582where()%[select()<<mul(837,216)")))))

(deftest test-part-2
  (testing "part 2"
    (is (= 67269798 (part-2 "resources/y2024/day3/input.txt")))))
