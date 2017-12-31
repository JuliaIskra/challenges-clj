(ns project-euler.problem17)

(defn digit-to-string
  [d]
  (case d
    1 "one"
    2 "two"
    3 "three"
    4 "four"
    5 "five"
    6 "six"
    7 "seven"
    8 "eight"
    9 "nine"
    0 ""))

(defn teens-to-string
  [teens]
  (case teens
    10 "ten"
    11 "eleven"
    12 "twelve"
    13 "thirteen"
    14 "fourteen"
    15 "fifteen"
    16 "sixteen"
    17 "seventeen"
    18 "eighteen"
    19 "nineteen"))

(defn tens-to-string
  [tens]
  (case tens
    0 ""
    2 "twenty"
    3 "thirty"
    4 "forty"
    5 "fifty"
    6 "sixty"
    7 "seventy"
    8 "eighty"
    9 "ninety"))

(defn number-to-string
  [n]
  (let [thous (int (/ n 1000))
        hund  (int (/ (- n (* thous 1000)) 100))
        tens  (int (/ (- n (* thous 1000) (* hund 100)) 10))
        d     (int (/ (- n (* thous 1000) (* hund 100) (* tens 10)) 1))]
    (str
      (if (not= thous 0) (str (digit-to-string thous) "thousand"))
      (if (not= hund 0) (str (digit-to-string hund) "hundred"
                             (if (or (not= tens 0) (not= d 0)) "and")))
      (if (not= tens 0)
        (if (= tens 1) (teens-to-string (+ (* tens 10) d))
                       (str (tens-to-string tens) (digit-to-string d)))
        (if (not= d 0) (str (digit-to-string d)))))))

(defn problem17
  "https://projecteuler.net/problem=17
                             If the numbers 1 to 5 are written out in words: one, two, three, four, five,
  then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
  If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words,
  how many letters would be used?
  NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two)
  contains 23 letters and 115 (one hundred and fifteen) contains 20 letters.
  The use of \"and\" when writing out numbers is in compliance with British usage."
  []
  (->> (range 1 1001)
       (map number-to-string)
       (map count)
       (reduce +)))
