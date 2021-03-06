(ns ml-jam.core
  (:require [clojure.java.io :as io]
            [hiphip.int :as int]))

(defn get-lines 
  "Grab each line (excluding the header) from the file as a sequence of strings."
  [path]
  (with-open [r (io/reader path)]
    (line-seq r)  ; Drop the header
    (doall (line-seq r))))

(defn line->seq
  "Convert a comma-separate string into a sequence."
  [line]
  (map read-string (clojure.string/split line #",")))

(defn read-data
  "Pull the data from the file into a format for processing."
  [path]
  (map line->seq (get-lines path)))

; 5000 known values.
(def training-set (read-data "resources/digitssample.csv"))

; 500 examples to test against.
(def validation-set (read-data "resources/digitscheck.csv"))

(defn sqr-diff
  "Find the difference between numbers and square it."
  [n m]
  (* (- n m) (- n m)))

(defn euclidean-distance
  "Euclidean distance between two values."
  [left right]
  (reduce + (map sqr-diff left right)))

(defn nearest-neighbor
  [candidate known]
  (let [[expect & image] candidate]
    (first
      (sort-by second
               (for [[label & values] known]
                 [label (euclidean-distance image values)])))))

(defn nearest-neighbor-map
  [candidate known]
  (let [[expect & image] candidate]
    (first
      (sort-by second
               (map
                 #(list (first %) (euclidean-distance image (rest %)))
                 known)))))

;; hiphip Versions

(defn line->array
  "Convert a comma-separate string into a sequence."
  [line]
  (int-array (map read-string (clojure.string/split line #","))))

(defn hip-data
  [line]
  (let [[label & values] (map read-string (clojure.string/split line #","))]
    (list label (int-array values))))

(defn read-data-into-array
  "Pull the data from the file into a format for processing."
  [path]
  (map hip-data (get-lines path)))

(def hip-train (read-data-into-array "resources/digitssample.csv"))

(def hip-validation (read-data-into-array "resources/digitscheck.csv"))

(defn hip-distance
  [left right]
  (int/asum
    (int/amap [l left r right] (* (- l r) (- l r)))))

(defn hippest-neighbor
  [candidate known]
  (let [[expect image] candidate]
    (first
      (sort-by second
               (for [[label values] known]
                 [label (hip-distance image values)])))))

(defn hippest-neighbor-map
  [candidate known]
  (let [[expect image] candidate]
    (first
      (sort-by second
               (map
                 #(list (first %) (hip-distance image (second %)))
                 known)))))

(defn validate-set
  [to-validate training-data]
  (doall
    (pmap #(hippest-neighbor-map % training-data) to-validate)))

(defn pass?
  [expected results]
  (= expected (first results)))

(defn check-accuracy
  [to-validate training-data]
  (let [passed (atom 0)
        checked (count to-validate)]
    (doall
      (pmap #(if (pass? (first %) (hippest-neighbor-map % training-data)) (swap! passed inc)) to-validate))
    (/ @passed checked)))
