(ns advent-of-code-clj.y2024.day3-test
  (:require [advent-of-code-clj.y2024.day3 :refer :all]
            [clojure.test :refer :all]))

(deftest test-part-1
  (testing "part 1"
    (is (= 161 (part-1 "resources/y2024/day3/input-example-1.txt")))))

(deftest test-part-2
  (testing "part 2"
    (is (= 48 (part-2 "resources/y2024/day3/input-example-2.txt")))))

(deftest test-collect-numbers
  (testing "collect numbers when mul starts correctly but has incorrect format"
    (is (= [504 381 837 216] (collect-numbers "mul(504,381)>what()why()$%from(53,115)%mul(582where()%[select()<<mul(837,216)")))))
