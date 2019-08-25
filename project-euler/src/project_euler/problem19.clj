(ns project-euler.problem19)

(def months
  ; month number maps to amount of days in it
  {1  31
   3  31
   4  30
   5  31
   6  30
   7  31
   8  31
   9  30
   10 31
   11 30
   12 31})

(defn leap-year?
  [year]
  (if (== 0 (mod year 100))
    (== 0 (mod year 400))
    (== 0 (mod year 4))))

(defn days-in-year
  [year]
  (flatten [
            (range 1 (inc (months 1))) ; Jan
            (range 1 (inc (if (leap-year? year) 29 28))) ; Feb
            (->> (range 3 13) ; Mar-Dec
                 (map months)
                 (map #(range 1 (inc %))))]))

(defn problem19
  "https://projecteuler.net/problem=19
  You are given the following information, but you may prefer to do some research for yourself.

  1 Jan 1900 was a Monday.

  Thirty days has September,
  April, June and November.
  All the rest have thirty-one,
  Saving February alone,
  Which has twenty-eight, rain or shine.
  And on leap years, twenty-nine.

  A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.

  How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?"
  []
  (let [last-week (->> (days-in-year 1900)
                       (partition 7 7 (repeat 6 0))
                       last)
        last-day (first (keep-indexed #(if (== 0 %2) %1) last-week))]
    (count
      (->> (range 1901 2001)
           (map days-in-year)
           flatten
           (partition 7 7 (repeat 6 0))
           (filter #(== 1 (nth % (- 7 (inc last-day)))))))))
