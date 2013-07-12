(ns ml-jam.core-test
  (:use clojure.test
        ml-jam.core))

(deftest seq-for-version
  (testing "Validation of version using seq and for comprehension."
    (is (= 8 (first (nearest-neighbor (first validation-set) training-set))))))

(deftest seq-map-version
  (testing "Validation of version using seq and map."
    (is (= 8 (first (nearest-neighbor-map (first validation-set) training-set))))))

(deftest hip-for-version
  (testing "Validation of version using hiphip and for comprehension."
    (is (= 8 (first (hippest-neighbor (first hip-validation) hip-train))))))

(deftest hip-map-version
  (testing "Validation of version using hiphip and map."
    (is (= 8 (first (hippest-neighbor-map (first hip-validation) hip-train))))))
