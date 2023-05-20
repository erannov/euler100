(ns euler100.core
  (:gen-class))

(def sqrt2 (Math/sqrt 2))
(def sqrt2p1 (+ sqrt2 1))
(def start-num 1000000000000N)

(defn next-factor
  [factor]
  (let [duplicate (bigint (* sqrt2p1 factor))]
    (if (odd? duplicate)
      duplicate
      (inc duplicate))))

(def factors (iterate next-factor 3))
(def zero-ones (iterate #(if (= 0 %) 1 0) 0))
(def fact-builder (map #(cons %1 (list %2 %3)) factors (drop 1 factors) zero-ones))
(def totals (map #(+ (last %)(* (first %)(second %))) fact-builder))

(defn solve
  []
  (inc (bigint
         (/ (first
              (filter #(> % start-num) totals))
            sqrt2))))

(defn -main
  [& args]
  (println (time(solve))))
